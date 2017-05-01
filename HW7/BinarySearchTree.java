/**
 * @author Devrim Akıncı
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E> > extends BinaryTree {

    protected E deleteReturn;

    //Methods

    /**
     * No parameter constructor
     */

    public BinarySearchTree(){
        super();
    }

    /**
     * Verilen root node'a gore tree'yi olusturur
     * @param root Verilen root node
     */

    public BinarySearchTree(Node<E> root){
        super(root);
    }

    /**
     * Verilen data,sol alt agac ve sag alt agac ile tree'yi olusturur
     * @param data Verilen data
     * @param leftTree Sol alt agac
     * @param rightTree Sag alt agac
     */

    public BinarySearchTree(E data, BinarySearchTree<E> leftTree, BinarySearchTree<E> rightTree){
        super(data,leftTree,rightTree);
    }

    /**
     * Bu method LevelOrder traver eden iterator dondurur
     * @return LevelOrderIterator
     */

    public Iterator<E> levelOrderIterator(){
        return new myLevelOrderIterator();
    }

    /**
     * Bu method InOrder traverse eden iterator dondurur
     * @return InOrderIterator
     */

    public Iterator<E> InOrderIterator(){
        return new InOrderIterator();
    }

    //Methods

    /**
     * Tree'nin verilerini ekrana levelorder traverse ederek ekrana basar
     */

    public void traverseLevelOrderIterator(){
        Iterator<E> iter = this.levelOrderIterator();
        while(!(iter.hasNext()))
            System.out.printf("%s ",iter.next().toString());
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

    /** Starter method delete.
     post: The object is not in the tree.
     @param target The object to be deleted
     @return The object deleted from the tree
     or null if the object was not in the tree
     @throws ClassCastException if target does not implement
     Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /** Recursive delete method.
     post: The item is not in the tree;
     deleteReturn is equal to the deleted item
     as it was stored in the tree or null
     if the item was not found.
     @param localRoot The root of the current subtree
     @param item The item to be deleted
     @return The modified local root that does not contain
     the item
     */
    private Node < E > delete(Node < E > localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            }
            else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            }
            else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted nodes data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node < E > parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else {
            return findLargestChild(parent.right);
        }
    }

    private class InOrderIterator implements Iterator {

        // Data Fields
        private Stack<Node<E>> myStack; // Tree'deki node'lari tutacak Stack yapisi

        //Methods

        /**
         * No parameter constructor
         */

        public InOrderIterator() {
            myStack = new Stack<>();
            pushToStack(root);
        }

        /**
         * Stack'in bos olup olmadigini kontrol eder
         *
         * @return Stack bos ise true, degilse false return eder
         */

        @Override
        public boolean hasNext() {
            if (myStack.isEmpty())
                return true;
            else
                return false;
        }

        /**
         * Bu method agaci inorder yontemi ile gezerek tum verileri dondurur
         *
         * @return Agactaki her bir node'un datasini inorder traverse yaparak dondurur
         */

        @Override
        public E next() {
            Node temp = myStack.pop();
            pushToStack(temp.right);
            return (E)temp.data;
        }

        private void pushToStack(Node node){
            while(node != null){
                myStack.push(node);
                node = node.left;
            }
        }
    }

    private class myLevelOrderIterator implements Iterator{
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