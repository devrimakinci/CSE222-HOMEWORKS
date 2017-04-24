package tr.edu.gtu.devrimakinci.q1;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Devrim Akıncı
 */


public class PreOrderTraverse<E> extends BinaryTree implements Iterable {

    /**
     * No parameter constructor
     */

    public PreOrderTraverse(){
        super();
    }

    /**
     * Constructor
     * @param root Verilen root node
     */

    public PreOrderTraverse(Node<E> root){
        super(root);
    }

    /**
     * Bu method agaci preorder olarak iterator kullanarak traverse eder
     */

    @Override
    public void preOrderTraverse() {
        Iterator<E> myIter = this.iterator();
        while(!(myIter.hasNext()))
            System.out.printf("%s ",myIter.next().toString());
    }

    public Iterator<E> iterator(){
        return new myIterator<>();
    }

    private class myIterator<E> implements Iterator<E> {

        // Data Fields

        private Stack<Node<E>> myStack; //Stack which keeps tree nodes

        //Methods

        /**
         * No parameter constructor
         */

        public myIterator(){
            myStack = new Stack<>();
            myStack.push((Node<E>)root); // Adding root node
        }

        /**
         * This methods traverses a binary tree with preorder
         * @return Data
         */

        @Override
        public E next() {
            Node current = myStack.peek(); // First element in stack
            if (current.left != null){ // If left child of root node is null
                myStack.push(current.left); // Adding left child of root node to stack
            }
            else{
                Node del = myStack.pop(); // Removing first element in stack
                while(del.right == null){ // If right child of root node is null
                    if(myStack.isEmpty()) // If stack is empty
                        return (E)current.data; // Returns data of current node
                    del = myStack.pop(); // Removing first element in stack
                }
                myStack.push(del.right); // Adding right child of root node to stack
            }
            return (E)current.data;
        }

        /**
         * This methods controls whether stack is empty
         * @return If stack is empty returns true, otherwise returns false
         */

        @Override
        public boolean hasNext() {
            if(myStack.isEmpty())
                return true;
            else
                return false;
        }
    }

}
