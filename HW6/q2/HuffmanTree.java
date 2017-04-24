package tr.edu.gtu.devrimakinci.q2;


import java.util.*;
import java.io.*;

/** Class to represent and build a Huffman tree.
 *   @author Koffman and Wolfgang
 * */

public class HuffmanTree implements Serializable{

    // Nested Classes
    /** A datum in the Huffman tree. */
    public static class HuffData implements Comparable<HuffData> {
        // Data Fields
        /** The weight or probability assigned to this HuffData. */
        private double weight;

        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        public double getWeight(){
            return weight;
        }

        public Character getSymbol(){
            return symbol;
        }

        public void setSymbol(Character ch){
            symbol = ch;
        }

        public void setWeight(double wh){
            weight = wh;
        }

        @Override
        public int compareTo(HuffData huffData) {
            return 0;
        }


    }

    public class Entry<E,T>{

        //Data Fields
        private E val1;
        private T val2;

        //Methods

        public Entry(E val1, T val2){
            this.val1 = val1;
            this.val2 = val2;
        }

        public E getVal1(){
            return  val1;
        }

        public T getVal2(){
            return val2;
        }

        public void setVal1(E val1){
            this.val1 = val1;
        }

        public void setVal2(T val2){
            this.val2 = val2;
        }

    }

    // Data Fields
    /** A reference to the completed Huffman tree. */
    private BinaryTree < HuffData > huffTree;

    private ArrayList<Entry<Character,String>> table = new ArrayList<>();

    /** A Comparator for Huffman trees; nested class. */
    private static class CompareHuffmanTrees
            implements Comparator < BinaryTree < HuffData >> {
        /** Compare two objects.
         @param treeLeft The left-hand object
         @param treeRight The right-hand object
         @return -1 if left less than right,
         0 if left equals right,
         and +1 if left greater than right
         */
        public int compare(BinaryTree < HuffData > treeLeft,
                           BinaryTree < HuffData > treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /** Builds the Huffman tree using the given alphabet and weights.
     post:  huffTree contains a reference to the Huffman tree.
     @param symbols An array of HuffData objects
     */
    public void buildTree(ArrayList<HuffData> symbols) {
        Queue < BinaryTree < HuffData >> theQueue
                = new PriorityQueue < BinaryTree < HuffData >>
                (symbols.size(), new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree < HuffData > aBinaryTree =
                    new BinaryTree < HuffData > (nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree < HuffData > left = theQueue.poll();
            BinaryTree < HuffData > right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree < HuffData > newTree =
                    new BinaryTree < HuffData > (sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
        generateTable(table,huffTree,"");
    }

    /**
     * Bu method huffman tree'yi alir.
     * @return Huffman tree
     */

    public BinaryTree<HuffData> getHuffTree(){
        return huffTree;
    }

    /**
     * Bu method huffman tree'nin huffman tablosunu olusturur.
     * @param table Huffman tablosu
     * @param tree Huffman tree
     * @param str Huffman tree'deki herbir deger icin ayri ayri binary olarak kod
     */

    private void generateTable(ArrayList<Entry<Character,String>> table, BinaryTree<HuffData> tree ,String str){
        HuffData theData = tree.getData();
        if(!tree.isLeaf()){
            generateTable(table,tree.getLeftSubtree(), str + '0');
            generateTable(table,tree.getRightSubtree(), str + '1');
        }
        else{
            Entry<Character,String> myPair = new Entry<>(theData.symbol,str);
            table.add(myPair);
        }
    }

    public void printCoded(){
        for(int i=0; i<table.size(); i++){
            System.out.printf("%c:%s\n",table.get(i).val1,table.get(i).val2);
        }
    }

    /**
     * Bu method verilen mesajin huffman tree'ye gore code'unu bulur.
     * @param message Verilen mesaj
     * @return Binary olarak verilen mesajin code'unu dondurur.
     */

    public String encode(String message){
        String result = "";
        for(int i=0; i<message.length(); i++){
            for (int j=0; j<table.size(); j++)
                if(table.get(j).val1.equals(message.charAt(i))){
                    result = result + table.get(j).val2;
                }
        }
        return result;
    }

    /** Method to decode a message that is input as a string of
     digit characters '0' and '1'.
     @param codedMessage The input message as a String of
     zeros and ones.
     @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree < HuffData > currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            }
            else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }

}