package tr.edu.gtu.devrimakinci.q1;

/**
 *  @author Devrim Akıncı
 */

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree < E extends Comparable < E >> implements Serializable{

    /** Class to encapsulate a tree node. */
    protected static class Node < E > implements Serializable {

        // Data Fields
        /** The information stored in this node. */
        protected E data;

        /** Reference to the left child. */
        protected Node < E > left;

        /** Reference to the right child. */
        protected Node < E > right;

        // Constructors

        /** Construct a node with given data and no children.
         @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean isLeafLeft(){
            return (left == null);
        }

        public boolean isLeafRight(){
            return (right == null);
        }

        // Methods
        /** Return a string representation of the node.
         @return A string representation of the data fields
         */
        public String toString() {
            return data.toString();
        }

    }

    // Data Fields
    protected boolean addReturn;

    protected Node< E > root;

    //Methods

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node< E > root) {
        this.root = root;
    }

    /** Constructs a new binary tree with data in its root,leftTree
     as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree < E > leftTree, BinaryTree < E > rightTree) {
        root = new Node< E >(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        }
        else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        }
        else {
            root.right = null;
        }
    }

    /** Return the left subtree.
     @return The left subtree or null if either the root or
     the left subtree is null
     */
    public BinaryTree < E > getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree < E > (root.left);
        }
        else {
            return null;
        }
    }

    /** Return the right sub-tree
     @return the right sub-tree or
     null if either the root or the
     right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /** Return the data field of the root
     @return the data field of the root
     or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /** Determine whether this tree is a leaf.
     @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }


    /** Perform a preorder traversal.
     @param node The local root
     @param depth The depth
     @param sb The string buffer to save the output
     */
    private void preOrderTraverse(BinaryTree.Node< E > node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Agaci preorder olarak traverse eder
     */

    public void preOrderTraverse(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root,sb);
        System.out.println(sb.toString());
    }

    /**
     * Bu method recursive olarak agaci dolasir ve elemanlarini StringBuild'ira ekler.
     * @param localNode Verilen Node
     * @param sb StringBuilder
     */

    private void preOrderTraverse (Node<E> localNode, StringBuilder sb){
        if (localNode == null)
            return;
        sb.append(localNode.data + " ");
        preOrderTraverse(localNode.left,sb);
        preOrderTraverse(localNode.right,sb);

    }

    /** Starter method add.
     pre: The object to insert must implement the
     Comparable interface.
     @param data The object being inserted
     @return true if the object is inserted, false
     if the object already exists in the tree
     */
    public boolean add(E data) {
        root = add(root, data);
        return addReturn;
    }

    /** Recursive add method.
     post: The data field addReturn is set true if the data is added to
     the tree, false if the data is already in the tree.
     @param localRoot The local root of the subtree
     @param data The object to be inserted
     @return The new local root that now contains the
     inserted data
     */
    private Node < E > add(Node < E > localRoot, E data) {
        if (localRoot == null) {
            // data is not in the tree insert it.
            addReturn = true;
            return new Node < E > (data);
        }
        else if (data.compareTo(localRoot.data) == 0) {
            // data is equal to localRoot.data
            addReturn = false;
            return localRoot;
        }
        else if (data.compareTo(localRoot.data) < 0) {
            // data is less than localRoot.data
            localRoot.left = add(localRoot.left, data);
            return localRoot;
        }
        else {
            // data is greater than localRoot.data
            localRoot.right = add(localRoot.right, data);
            return localRoot;
        }
    }

    /**
     * Bu method LevelOrder traver eden iterator dondurur
     * @return LevelOrderIterator
     */

    public Iterator<E> levelOrderIterator(){
        return new myLevelOrderIterator();
    }

    /**
     * Tree'nin verilerini ekrana levelorder traverse ederek ekrana basar
     */

    public void traverseLevelOrderIterator(){
        Iterator<E> iter = this.levelOrderIterator();
        while(!(iter.hasNext()))
            System.out.printf("%s ",iter.next().toString());
    }


    private class myLevelOrderIterator implements Iterator {

        // Data Fields
        private Queue<Node<E>> myQueue; // Tree'deki node'lari tutacak Queue yapisi

        //Methods

        /**
         * No parameter constructor
         */

        public myLevelOrderIterator(){
            myQueue = new LinkedList<>();
            myQueue.offer(root);
        }

        /**
         * Queue'nun bos olup olmadigini kontrol eder
         * @return Queue bos ise true, degilse false return eder
         */

        @Override
        public boolean hasNext() {
            if (myQueue.isEmpty())
                return true;
            else
                return false;
        }

        /**
         * Bu method agaci levelorder yontemi ile gezerek tum verileri dondurur
         * @return Agactaki her bir node'un datasini levelorder traverse yaparak dondurur
         */

        @Override
        public E next() {
            Node<E> current = myQueue.poll(); //Queue'nun ilk elemanin cikarilmasi
            if (current.left != null && current.right != null){ //Eger o anki node'un sol ve sag cocugu varsa
                myQueue.offer(current.left); //Sol cocugu Queue'ya ekleme
                myQueue.offer(current.right); //Sag cocugu Queue'ya ekleme
            }
            else if (current.left != null) //Eger o anki node'un sadece sol cocugu varsa
                myQueue.offer(current.left); //Sol cocugu Queue'ya ekleme
            else if (current.right != null) //Eger o anki node'un sadece sag cocugu varsa
                myQueue.offer(current.right); //Sag cocugu Queue'ya ekleme
            return current.data; //O anki node'un datasinin dondurulmesi
        }
    }
}