Hasher Hassan - hmh3233
Huffman Compression - Empirical analysis

Experiment Setup

I used the provided HuffMark class to test my Huffman compressor on the Calgary,
BooksAndHTML, and Waterloo files. All benchmark results below use Standard Count
Format heders. Compression was forced by the code, which menas that output files
would be produced even if they became larger than the original.

Results

Collection: Calgary
Original Size: 3,251,493 bytes
Compressed Size: 1,845,571 bytes
Space Saved: 43.239%
Compression Time: 7.965 seconds

Collection: BooksAndHTML
Original Size: 9,788,581 bytes
Compressed Size: 5,828,112 bytes
Space Saved: 40.460%
Compression Time: 23.267 seconds

Collection: Waterloo
Original Size: 12,466,304 bytes
Compressed Size: 10,205,282 bytes
Space Saved: 18.137%
Compression Time: 45.841 seconds

Calgary had the greatest overall percentage reduction in bit size,
with BookAndHTML close behind it. The text files generally compressed well.
For example, melville.txt decreased from 82,140 to 47,364 bytes, saving about 42%
Huffman coding assigns shorter codes to frequenctly occurring byte values. Text
often has uneven frequencies because some letter or spaces or even punctation occur
more often than other values. THese shorter codes can reduce the total number of bits
needed. The Calgary file pic decreased from 513,216 to 107,586 bytes, saving about 79%
of the bits. This shows that strong compression is also there for some non-text data.
However, the distribtuion of byte values matters, rather than just whether a file contains text.

Waterloo had the smallest overall reduction at 18.137%. Some of its TIFF images had
suprisingly small savings. For example, lena.tif decreased from 786,568 to 766,146 bytes,
saving about 2.6%, a minor amount. However, not all images were like that. frymire.tif
decreased from 3,706,306 to 2,188,593 bytes, saving about 41%. Thus, file type alone does
not seem to determine how well Huffman coding will work. Small savings are more consistent with
byte frequencies that have fewer opportunities for short codes. This implementation encodes
individual byte values, instead of directly taking advantage of repeated sequences of bytes or
larger image patterns.

I tested recompression using the previously compressed A7_Recursion.html file. The input was
26,189 bytes, and the new output was 26,340 bytes. It actually grew by 151 bytes, which is pretty
crazy, by approximately 0.577%. The recompression took 0.208 seconds. The second compression did not
save enough data bits to offset its additional header. The first pass had already removed most of
the opportunity that was originally available. This experiment shows that repeatedly using Huffman
compression does not necessarily keep making a file smaller and can actually cause it to become larger.

Standard Count Format stores 256 frequency counts, each using 32 bits. With the magic number
and format identifier, its header occupies 1,032 bytes. This overhead has a greater proportional
effect on small files compared to larger files. A file can become larger even when its encoded data
uses fewer bits, because the header has to also be stored.

In conclusion, my results show that compression effectiveness depends on the byte frequencies and
the header overhead size. Text and some other files compressed a lot, while several images had pretty
limited savings. The already compressed file actually became slightly larger. Waterloo also took the
longest in the above runs even though it saved the samllest percentage of bits, showing how a longer
compression time does not necessarily result in greater space savings. The timings are my own measurements
and can differ when run again.
