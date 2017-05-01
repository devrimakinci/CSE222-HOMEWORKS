/**
 *  @author Devrim Akıncı
 */

import java.io.*;

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

    public void reverse(Node root){
        Node temp = root.right;
        root.right = root.left;
        root.left = temp;

        if (root.left != null) {
            reverse(root.left);
        }

        if (root.right != null) {
            reverse(root.right);
        }
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

}