/*  Student information for assignment:
 *
 *  On MY honor, Hasher, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used) Hasher
 *  UTEID: hmh3233
 *  email address: hmh3233@eid.utexas.edu
 *  Grader name: Jason Oh
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Main driver for Huffman preprocessing, compression, and decompression
 */
public class SimpleHuffProcessor implements IHuffProcessor {

    // number of fixed-width integers before the serialized tree
    private static final int TREE_HEADER_INT_COUNT = 3;

    private IHuffViewer myViewer;

    // frequencies indexed by input value, extra entry for PSEUOD_EOF
    private int[] freqArray;

    private HuffmanTree huffTree;
    private int theHeaderFormat;
    private int bitsSaved;

    // excludes padding
    private int compressedBits;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * pre: headerFormat is STORE_COUNTS or STORE_TREE
     * post: consumes in without closing it, prepares compression, and displays
     * frequencies, codes, and size estimates when a viewer is attached
     * If headerFormat is unsupported, reports an error when a viewer is attached
     * and returns 0 without preprocessing
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        if (headerFormat != STORE_COUNTS && headerFormat != STORE_TREE) {
            if (myViewer != null) {
                myViewer.showError("Unsupported header format");
            }
            return 0;
        }
        freqArray = countFrequencies(new BitInputStream(in));
        huffTree = buildTree(freqArray);
        theHeaderFormat = headerFormat;
        compressedBits = countDataBits() + countHeaderBits();
        bitsSaved = countOriginalBits() - compressedBits;
        displayResults();
        return bitsSaved;
    }

    /**
     * pre: inp is at the beginning of the data
     * post: returns an array of the byte frequencies, with PSEUDO_EOF
     * assigned a frequency of 1, consumes inp and leaves it open
     * @throws IOException if reading fails
     */
    private int[] countFrequencies(BitInputStream inp) throws IOException {
        int[] frequencies = new int[ALPH_SIZE + 1];
        int value = inp.readBits(BITS_PER_WORD);
        while (value != -1) {
            frequencies[value]++;
            value = inp.readBits(BITS_PER_WORD);
        }
        frequencies[PSEUDO_EOF] = 1;
        return frequencies;
    }

    /**
     * pre: frequencies != null, frequencies.length == ALPH_SIZE + 1,
     * all frequencies are nonnegative and at least one is positive
     * post: returns a HuffmanTree built from the supplied frequencies;
     * frequencies is not changed
     */
    private HuffmanTree buildTree(int[] frequencies) {
        PriorityQueue<TreeNode> queue = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                TreeNode node = new TreeNode(i, frequencies[i]);
                queue.enqueue(node);
            }
        }
        return new HuffmanTree(queue);
    }

    /**
     * pre: freqArray contains the frequencies of the original input
     * post: returns the original input size in bits, excluding PSEUDO_EOF
     */
    private int countOriginalBits() {
        int total = 0;
        for (int i = 0; i < ALPH_SIZE; i++) {
            total += freqArray[i] * BITS_PER_WORD;
        }
        return total;
    }

    /**
     * pre: input frequencies and their Huffman codes have been generated
     * post: returns the encoded data size in bits, including PSEUDO_EOF and
     * excluding headers and padding
     */
    private int countDataBits() {
        int dataBits = 0;
        for (int i = 0; i < freqArray.length; i++) {
            if (freqArray[i] > 0) {
                String code = huffTree.getCode(i);
                dataBits += code.length() * freqArray[i];
            }
        }
        return dataBits;
    }

    /**
     * pre: the header format has been selected and tree has been built
     * post: returns the total header size in bits, including the
     * magic number, format identiifer, and format-specific header
     * @throws IllegalArgumentException if the selected format is not
     * supported
     */
    private int countHeaderBits() {
        if (theHeaderFormat == STORE_COUNTS) {
            return 2 * BITS_PER_INT + ALPH_SIZE * BITS_PER_INT;
        } else if (theHeaderFormat == STORE_TREE) {
            return TREE_HEADER_INT_COUNT * BITS_PER_INT + huffTree.numBits();
        }
        throw new IllegalArgumentException("Unsupported format");
    }

    /**
	 * Compresses input to output, where the same data has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * pre: preprocessing completed successfully for the same data, in is
     * positioned at the beginning of that data
     * post: writes compressed data, closes out on success, returns 0 without writing if compression
     * is refused and leaves out open, reports an error if a viewer is attached
     * @throws IOException if reading or writing fails
     * @throws IllegalArgumentException if no preprocessing tree is available
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        if (huffTree == null) {
            throw new IllegalArgumentException("Preprocessing has to be completed first");
        }
        if (!force && bitsSaved < 0) {
            if (myViewer != null) {
                myViewer.showMessage("Cannot compress since output would be larger.");
            }
            return 0;
        }
        BitOutputStream outStream = new BitOutputStream(out);
        writeHeader(outStream);
        writeData(new BitInputStream(in), outStream);
        writeCode(huffTree.getCode(PSEUDO_EOF), outStream);
        outStream.close();
        return compressedBits;
    }

    /**
     * pre: preprocessing has already been completed
     * successfully, and outStream is at the beginning of the output
     * post: writes the magic number, format identifier, and selected header
     * to outStream, outStream is not closed
     */
    private void writeHeader(BitOutputStream outStream) {
        outStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        outStream.writeBits(BITS_PER_INT, theHeaderFormat);
        if (theHeaderFormat == STORE_COUNTS) {
            for (int i = 0; i < ALPH_SIZE; i++) {
                outStream.writeBits(BITS_PER_INT, freqArray[i]);
            }
        } else if (theHeaderFormat == STORE_TREE) {
            outStream.writeBits(BITS_PER_INT, huffTree.numBits());
            huffTree.treeWrite(outStream);
        }
    }

    /**
     * pre: inp is at the beginning of the original data and matching Huffman codes
     * have been generated, outStream is ready for encoded data
     * post: consumes inp and writes its Huffman encoded data to outStream, without
     * PSEUDO_EOF; inp and outStream are not closed
     * @throws IOException if reading fails
     */
    private void writeData(BitInputStream inp, BitOutputStream outStream) throws IOException {
        int value = inp.readBits(BITS_PER_WORD);
        while (value != -1) {
            writeCode(huffTree.getCode(value), outStream);
            value = inp.readBits(BITS_PER_WORD);
        }
    }

    /**
     * pre: code != null and only has either '0's or '1's
     * post: writes one bit per character in code to outStream in order
     * outStream is not closed
     */
    private void writeCode(String code, BitOutputStream outStream) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                outStream.writeBits(1, 0);
            } else {
                outStream.writeBits(1, 1);
            }
        }
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * pre: none
     * post: restores valid compressed input and returns output bits written,
     * closes out on success, returns 0 for an invalid magic number without writing
     * or closing out
     * @throws IOException if reading or writing fails, a header is found as
     * invalid or incomplete, or input ends before PSEUDO_EOF
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        BitInputStream inp = new BitInputStream(in);
        int value = inp.readBits(BITS_PER_INT);
        if (value != MAGIC_NUMBER) {
            if (myViewer != null) {
                myViewer.showError("Invalid Huffman file");
            }
            return 0;
        }
        theHeaderFormat = inp.readBits(BITS_PER_INT);
        if (theHeaderFormat != STORE_COUNTS && theHeaderFormat != STORE_TREE) {
            if (myViewer != null) {
                myViewer.showError("Unsupported header format");
            }
            return 0;
        }
        HuffmanTree decodingTree = readTree(inp);
        BitOutputStream outStream = new BitOutputStream(out);
        int count = decodeData(inp, outStream, decodingTree);
        outStream.close();
        if (myViewer != null) {
            myViewer.showMessage("Decompression complete. Bits written: " + count);
        }
        return count;
    }

    /**
     * pre: inp is right after the header-format identifier, theHeaderFormat
     * correctly identifies the header being read
     * post: returns the reconstructed Huffman tree and leaves inp at the
     * encoded data for a valid header
     * @throws IOException for an unsupported format, an invalid/incomplete
     * header, or reading fails
     */
    private HuffmanTree readTree(BitInputStream inp) throws IOException {
        if (theHeaderFormat == STORE_COUNTS) {
            return buildTree(readCounts(inp));
        } else if (theHeaderFormat == STORE_TREE) {
            int treeBits = inp.readBits(BITS_PER_INT);
            if (treeBits < 0) {
                throw new IOException("Missing tree size");
            }
            return new HuffmanTree(inp);
        }
        throw new IOException("Unsupported format");
    }

    /**
     * pre: inp is at the first frequency count
     * post: reads ALPH_SIZE nonnegative counts and returns them
     * in an array with PSEUDO_EOF assigned a frequency of 1, leaves
     * inp open
     * @throws IOException if a count is negative, missing, or reading fails
     */
    private int[] readCounts(BitInputStream inp) throws IOException {
        int[] frequencies = new int[ALPH_SIZE + 1];
        for (int i = 0; i < ALPH_SIZE; i++) {
            int frequency = inp.readBits(BITS_PER_INT);
            if (frequency < 0) {
                throw new IOException("Missing or negative frequency count");
            }
            frequencies[i] = frequency;
        }
        frequencies[PSEUDO_EOF] = 1;
        return frequencies;
    }

    /**
     * pre: inp is at the encoded data, tree is a valid Huffman tree that has
     * PSEUDO_EOF and it corresponds to the encoded data
     * post: writes decoded values until PSEUO_EOF without writing the marker,
     * returns output bits written, leaves both streams open, doesn't decode
     * ending padding
     * @throws IOException if input ends before PSEUDO_EOF or reading fails
     */
    private int decodeData(BitInputStream inp, BitOutputStream outStream,
            HuffmanTree tree) throws IOException {
        TreeNode current = tree.getRoot();
        int count = 0;
        boolean done = false;
        while (!done) {
            if (current.isLeaf()) {
                if (current.getValue() == PSEUDO_EOF) {
                    done = true;
                } else {
                    outStream.writeBits(BITS_PER_WORD, current.getValue());
                    count += BITS_PER_WORD;
                    current = tree.getRoot();
                }
            } else {
                current = readNextNode(inp, current);
            }
        }
        return count;
    }

    /**
     * pre: inp != null and current is an internal node with 2 children
     * post: consumes one bit and returns the left child for 0 or the right
     * child for 1
     * @throws IOException if next bit is missing or reading fails
     */
    private TreeNode readNextNode(BitInputStream inp, TreeNode current) throws IOException {
        int bit = inp.readBits(1);
        if (bit == -1) {
            throw new IOException("Input ended before PSEUDO_EOF");
        }
        if (bit == 0) {
            return current.getLeft();
        }
        return current.getRight();
    }

    /**
     * pre: preprocessing has initialized the frequencies, codes, and size
     * variables
     * post: displays the frequencies, codes, and compression sizes if a
     * viewer is attached, otherwise does nothing
     */
    private void displayResults() {
        if (myViewer != null) {
            for (int i = 0; i < freqArray.length; i++) {
                if (freqArray[i] > 0) {
                    showString("Value: " + i + ", frequency: " + freqArray[i]
                        + ", code: " + huffTree.getCode(i));
                }
            }
            myViewer.showMessage("Original bits: " + countOriginalBits()
                + "\nCompressed bits: " + compressedBits + "\nBits saved: "
                + bitsSaved);
            showString("Header bits: " + countHeaderBits());
        }
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
