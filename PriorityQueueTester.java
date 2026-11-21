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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PriorityQueueTester {
    public static void main(String[] args) throws IOException {
        PriorityQueue<TreeNode> q = new PriorityQueue<>();
        System.out.println(q.size() == 0);
        System.out.println(q.isEmpty());
        System.out.println(q.dequeue() == null);
        TreeNode n1 = new TreeNode(1, 5);
        TreeNode n2 = new TreeNode(2, 2);
        TreeNode n3 = new TreeNode(3, 5);
        TreeNode n4 = new TreeNode(4, 1);
        q.enqueue(n1);
        q.enqueue(n2);
        q.enqueue(n3);
        q.enqueue(n4);
        System.out.println(q.size() == 4);
        System.out.println(!q.isEmpty());
        TreeNode ne1 = q.dequeue();
        System.out.println(ne1.getValue() == 4 && ne1.getFrequency() == 1);
        TreeNode ne2 = q.dequeue();
        System.out.println(ne2.getValue() == 2 && ne2.getFrequency() == 2);
        TreeNode ne3 = q.dequeue();
        System.out.println(ne3.getValue() == 1 && ne3.getFrequency() == 5);
        TreeNode ne4 = q.dequeue();
        System.out.println(ne4.getValue() == 3 && ne4.getFrequency() == 5);
        System.out.println(q.isEmpty());
        PriorityQueue<TreeNode> q2 = new PriorityQueue<>();
        q2.enqueue(new TreeNode(1,1));
        q2.enqueue(new TreeNode(2,2));
        q2.enqueue(new TreeNode(4,4));
        HuffmanTree t = new HuffmanTree(q2);
        System.out.println(t.getRoot().getFrequency() == 7);
        System.out.println(t.getRoot().getLeft().getFrequency() == 3);
        System.out.println(t.getRoot().getRight().getFrequency() == 4);
        System.out.println(t.getRoot().getLeft().getLeft().getFrequency() == 1);
        System.out.println(t.getRoot().getLeft().getRight().getFrequency() == 2);
        System.out.println(t.getCode(1).equals("00"));
        System.out.println(t.getCode(2).equals("01"));
        System.out.println(t.getCode(4).equals("1"));
        SimpleHuffProcessor p = new SimpleHuffProcessor();
        FileInputStream s = new FileInputStream("smallTxt.txt");
        System.out.println(p.preprocessCompress(s, IHuffConstants.STORE_COUNTS) == -8138);
        s.close();
        FileInputStream s2 = new FileInputStream("smallTxt.txt");
        System.out.println(p.preprocessCompress(s2, IHuffConstants.STORE_TREE) == -120);
        s2.close();
        SimpleHuffProcessor p1 = new SimpleHuffProcessor();
        FileInputStream s1 = new FileInputStream("smallTxt.txt");
        p1.preprocessCompress(s1, IHuffConstants.STORE_COUNTS);
        s1.close();
        FileInputStream compInput = new FileInputStream("smallTxt.txt");
        FileOutputStream o = new FileOutputStream("small-count.hf");
        System.out.println(p1.compress(compInput, o, true) == 8346);
        compInput.close();
        FileInputStream s3 = new FileInputStream("smallTxt.txt");
        p1.preprocessCompress(s3, IHuffConstants.STORE_TREE);
        s3.close();
        FileInputStream s4 = new FileInputStream("smallTxt.txt");
        FileOutputStream o1 = new FileOutputStream("small-tree.hf");
        System.out.println(p1.compress(s4, o1, true) == 328);
        FileInputStream ns = new FileInputStream("small-count.hf");
        FileOutputStream no = new FileOutputStream("restored-count.txt");
        System.out.println(p1.uncompress(ns, no) == 208);
        ns.close();
        ns = new FileInputStream("small-tree.hf");
        no = new FileOutputStream("restored-tree.txt");
        System.out.println(p1.uncompress(ns, no) == 208);
        ns.close();
        s4.close();
        FileInputStream tP = new FileInputStream("ciaFactBook2008.txt");
        p1.preprocessCompress(tP, IHuffConstants.STORE_TREE);
        tP.close();
        FileInputStream tI = new FileInputStream("ciaFactBook2008.txt");
        FileOutputStream tO = new FileOutputStream("cia-tree.hf");
        System.out.println(p1.compress(tI, tO, true) == 48223298);
        tI.close();
        FileInputStream tI1 = new FileInputStream("cia-tree.hf");
        FileOutputStream tO1 = new FileOutputStream("cia-tree-restored.txt");
        System.out.println(p1.uncompress(tI1, tO1) == 77097824);
        tI1.close();

    }
}