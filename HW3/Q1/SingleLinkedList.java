/**
 * @author Devrim Akıncı
 */

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.NullPointerException;
import java.util.Iterator;

public class SingleLinkedList<E> implements Iterable<E>{

    private class myIterator implements  Iterator<E>{
        //Data Fields
        private Node<E> nextItem;
        private Node<E> lastItemReturn;
        private int index;

        //Methods

        /**
         * Constructor
         * @param i - Iteratorun hangi yerden baslayacagini karar veren degisken
         */

        public myIterator(int i){
            if(i < 0 || i > getSize()){
                System.err.println("You exceed index out of bounds!");
                System.exit(1);
            }
            lastItemReturn = null;
            if (i == getSize()){
                i = getSize();
                nextItem = null;
            }
            else{
                nextItem = head;
                for(i=0; i<index; i++){
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         *
         * @return Eger bir sonraki item null ise false, degilse true dondurur.
         */

        public boolean hasNext(){
            return nextItem != null;
        }

        /**
         *
         * @return Bir sonraki elemani dondurur.
         */

        public E next(){
            if(!hasNext()){
                System.err.println("Linked List is empty!");
                System.exit(1);
            }
            lastItemReturn = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturn.data;
        }
    }

    //Data Fields
    private Node<E> head;
    private int size; //Dizinin eleman sayisi
    private ArrayList <Node<E>> deletedNodes; //Diziden silinen node'lari tutan ArrayList

    //Methods

    /**
     * No parameter constructor
     */
    public SingleLinkedList (){
        head = null;
        size = 0;
        deletedNodes = new ArrayList<>();
    }

    public Iterator<E> iterator(){
        return new myIterator(0);
    }

    /**
     * Single Linked List'in basina yeni eleman ekler.
     * @param newData - Eklenecek eleman
     */

    public void addFirst(E newData){
        if (deletedNodes.size() != 0){ // Eger silinen node varsa yeni bir node olusturmaya gerek kalmaması durumunda
            deletedNodes.get(0).data = newData; //Silinen node'un eski datasina yeni datanin atanmasi
            deletedNodes.get(0).next = head; // Silinen node'un nextinin head'e baglanmasi
            head = deletedNodes.get(0); // Head'in silinen node'a baglanarak elemanin basa eklenmesi
            deletedNodes.remove(0); // Silinen node'un ArrayList'den kaldirilmasi
            Iterator iter = deletedNodes.iterator();
            size++;
        }
        else{// Eger silinen node yoksa
            head = new Node<E>(newData, head); // Yeni bir node olusturulup dizinin basina eklenmesi
            size++;
        }
    }

    /**
     * Bu method verilen node'dan sonra elemani diziye ekler.
     * @param newData - Eklenecek eleman
     * @param afterNode - Hangi node'dan sonra elemanin eklenecegini karar veren degisken
     */

    public void addAfter(E newData, Node<E> afterNode){
        try{
            if (deletedNodes.size() != 0){// Eger silinen node varsa yeni bir node olusturmaya gerek kalmaması durumunda
                deletedNodes.get(0).data = newData;//Silinen node'un eski datasina yeni datanin atanmasi
                deletedNodes.get(0).next = afterNode.next;// Silinen node'un nextinin afterNode'un nextine baglanmasi
                afterNode.next = deletedNodes.get(0);// After node'un nextinin silinen node'a baglanarak elemanin diziye eklenmesi
                deletedNodes.remove(0);// Silinen node'un ArrayList'den kaldirilmasi
                size++;
            }
            else{// Eger silinen node yoksa
                afterNode.next = new Node<E>(newData,afterNode.next);// Yeni bir node olusturulup elemanin diziye eklenmesi
                size++;
            }
        }
        catch (NullPointerException e){
            System.err.println("You exceed index out of bounds!");
            System.exit(1);
        }
    }

    /**
     *
     * @return Dizinin eleman sayisini alir.
     */

    public int getSize(){
        return size;
    }

    /**
     *
     * @param index - Indeks degeri
     * @return Verilen indekse gore dizideki node'u dondurur.
     */

    public Node<E> getNode(int index){
        Node<E> node = head;
        for(int i=0; (i<index && node != null); i++){
            node = node.next;
        }
        return node;
    }

    /**
     *
     * @param index - Verilen indeks
     * @return Verilen indeksteki node'u alır ve o node'un datasini dondurur.
     */

    public E get(int index){
        if (index < 0 || index>= getSize()){
            System.err.println("You exceed index out of bounds!");
            System.exit(1);
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Bu fonksiyon dizinin basindan eleman siler.
     */

    public void removeFirst(){
        Node<E> temp = head; // Silinen node'u tutar.
        if (head != null){
            head = head.next;
        }
        if (temp != null){
            size--;
            deletedNodes.add(temp); //Silinen node'u ArrayList'e ekler.
        }
    }

    /**
     * Bu method verilen node'dan sonraki eleman diziden siler.
     * @param afterNode - Hangi node'dan sonra elemanin silinecegini karar veren degisken
     */

    public void removeAfter(Node<E> afterNode){
        try{
            Node<E> temp = afterNode.next; //Silinen node'u tutar.
            if (temp != null){
                afterNode.next = temp.next;
                size--;
                deletedNodes.add(temp); // Silinen node'u ArrayList'e ekler.
            }
        }
        catch (NullPointerException e){
            System.err.println("You exceed index out of bounds!");
            System.exit(1);
        }
    }

    /**
     * Bu method silinen node'larin ekrana gosterimi icin verileri stringe cevirir.
     *
     */

    public String deletedToString(){
        System.out.println("Deleting Nodes");
        StringBuilder str = new StringBuilder();
        for (int i=0; i<deletedNodes.size()-1; i++){
            str.append(deletedNodes.get(i).data + "->");
        }
        str.append(deletedNodes.get(deletedNodes.size()-1).data);
        return str.toString();
    }

    /**
     * Bu method SingleLinkedList'in ekrana gosterimi icin verileri stringe cevirir.
     *
     */

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Node<E> node = head;
        while (node != null){
            result.append(node.data);
            if (node.next != null){
                result.append("->");
            }
            node = node.next;
        }
        return result.toString();
    }

    private static class Node<E>{
        //Data Fields
        private E data;
        private Node<E> next;

        /**
         * Constructor
         * @param data - Veri
         */
        public Node(E data){
            this.data = data;
            next = null;
        }

        /**
         * Constructor
         * @param data - Veri
         * @param nodeRef - Node
         */
        public Node(E data, Node<E> nodeRef){
            this.data = data;
            next = nodeRef;
        }
    }
}