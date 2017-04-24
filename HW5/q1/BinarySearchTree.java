package tr.edu.gtu.devrimakinci.q1;

/**
 * @author Devrim Akıncı
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E> > extends BinaryTree {

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
     * Tree'nin verilerini ekrana levelorder traverse ederek ekrana basar
     */

    public void traverseLevelOrderIterator(){
        Iterator<E> iter = this.levelOrderIterator();
        while(!(iter.hasNext()))
            System.out.printf("%s ",iter.next().toString());
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
