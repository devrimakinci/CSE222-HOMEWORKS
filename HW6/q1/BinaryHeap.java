package tr.edu.gtu.devrimakinci.q1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author Devrim Akıncı
 */

public class BinaryHeap<E extends Comparable <E> > extends BinaryTree<E> implements Queue {

    //Methods

    /**
     * No parameters Constructor
     */

    public BinaryHeap(){}

    /**
     * Verilen node'larin datalarini swap eder.
     * @param node1 Swap edilecek birinci node
     * @param node2 Swap edilecek ikinci node
     */

    private void swap (Node<E> node1, Node<E> node2){
        E temp;
        temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    /**
     * Bu method Heap'e eleman ekler.
     * @param data Eklenecek eleman
     * @return Dogru sonuc ise true, yanlis sonuc ise false return eder.
     */

    @Override
    public boolean add(Object data) {
        root = add(root,(E) data);
        return true;
    }

    /**
     * Bu method Heap'e eleman ekler.
     * @param data Eklenecek eleman
     * @return Dogru sonuc ise true, yanlis sonuc ise false return eder.
     */

    @Override
    public boolean offer(Object data) {
        root = add(root,(E) data);
        return true;
    }

    /**
     * Bu method heap'e elemanlari heap'in kuralina gore recursive bir sekilde ekler.
     * @param localRoot Verilen baslangic root'u
     * @param data Eklenecek eleman
     * @return Node
     */

    private Node<E> add(Node<E> localRoot, E data){
        if(localRoot == null)
            return new Node < E > (data);
        else if (localRoot.left == null){
            localRoot.left = add(localRoot.left, data);
            if (compare(localRoot.data,localRoot.left.data) > 0)
                swap(localRoot,localRoot.left);
            return localRoot;
        }
        else if (localRoot.right == null){
            localRoot.right = add(localRoot.right, data);
            if (compare(localRoot.data,localRoot.right.data) > 0)
                swap(localRoot,localRoot.right);
            return localRoot;
        }
        else if (localRoot.left.isLeafLeft() && localRoot.right.isLeafLeft()){
            add(localRoot.left, data);
            if (compare(localRoot.data,localRoot.left.data) > 0)
                swap(localRoot,localRoot.left);
        }
        else if (localRoot.left.isLeafRight() && localRoot.right.isLeafRight()){
            add(localRoot.left, data);
            if (compare(localRoot.data,localRoot.left.data) > 0)
                swap(localRoot,localRoot.left);
        }
        else if (!(localRoot.left.isLeaf()) && (localRoot.right.isLeaf())){
            add(localRoot.right, data);
            if (compare(localRoot.data,localRoot.right.data) > 0)
                swap(localRoot,localRoot.right);
        }
        else if (!(localRoot.left.isLeafRight()) && (localRoot.right.isLeafRight())){
            add(localRoot.right, data);
            if (compare(localRoot.data,localRoot.right.data) > 0)
                swap(localRoot,localRoot.right);
        }
        return localRoot;
    }

    /**
     * Bu method heap'den eleman siler.
     * @return Silinen elemani dondurur.
     */

    @Override
    public Object remove() {
        E returnedItem = root.data;
        E lastItem = null;
        Iterator myIter = levelOrderIterator();
        while (!myIter.hasNext()){
            lastItem = (E)myIter.next();
        }
        root.data = lastItem;
        remove(root);
        return returnedItem;
    }

    /**
     * Bu method heap'den eleman siler.
     * @return Silinen elemani dondurur.
     */

    @Override
    public Object poll() {
        E returnedItem = root.data;
        E lastItem = null;
        Iterator myIter = levelOrderIterator();
        while (!myIter.hasNext()){
            lastItem = (E)myIter.next();
        }
        preOrderTraverse(root,lastItem);
        root.data = lastItem;
        remove(root);
        return returnedItem;
    }

    /**
     * Bu method heap'den elemani heap'in kuralina uygun bir sekilde siler
     * @param localRoot Node
     */

    private void remove(Node<E>localRoot){
        if (localRoot.left == null && localRoot.right == null)
            return;
        else if ((localRoot.right == null && localRoot.left != null) && (compare(localRoot.data,localRoot.left.data) > 0)){
            swap(localRoot,localRoot.left);
            remove(localRoot.left);
        }
        else if ((localRoot.left == null && localRoot.right != null) && (compare(localRoot.data,localRoot.right.data) > 0)){
            swap(localRoot,localRoot.right);
            remove(localRoot.right);
        }
        else if ((localRoot.right == null && localRoot.left != null) && (compare(localRoot.data,localRoot.left.data) < 0)){
            return;
        }
        else if ((localRoot.left == null && localRoot.right != null) && (compare(localRoot.data,localRoot.right.data) < 0)){
            return;
        }
        else if(compare(localRoot.left.data,localRoot.right.data) > 0){
            swap(localRoot,localRoot.right);
            remove(localRoot.right);
        }
        else if(compare(localRoot.right.data,localRoot.left.data) > 0){
            swap(localRoot,localRoot.left);
            remove(localRoot.left);
        }
    }

    /**
     * Bu method Heap'i pre order bir sekilde traverse ederek aranacak datanin bulundugu node'u siler.
     * @param localNode Aramaya baslanack node
     * @param searchData Aranacak eleman
     * @return Node
     */

    private Node<E> preOrderTraverse (Node<E> localNode,E searchData){
        if (localNode == null)
            return null;
        else if (localNode.data.equals(searchData)) {
            localNode = null;
            return localNode;
        }
        localNode.left = preOrderTraverse(localNode.left,searchData);
        localNode.right = preOrderTraverse(localNode.right,searchData);
        return localNode;
    }

    /**
     * Bu method heap'in ilk elemanini gosterir.
     * @return Heap'in ilk elemani
     */

    @Override
    public Object peek() {
        return root.data;
    }

    /**
     * Bu method heap'in ilk elemanini gosterir.
     * @return Heap'in ilk elemani
     */

    @Override
    public Object element() {
        return root.data;
    }

    /**
     * Bu method iki veriyi birbiri ile karsilastirir
     * @param data1 Birinci veri
     * @param data2 Ikinci veri
     * @return Karsilastirma sonucu olusan integer degeri return eder.
     */

    private int compare (E data1, E data2){
        return data1.compareTo(data2);
    }

    /**
     * Bu method heap'in level order traverse olarak gosterimini string'e cevirir.
     * @return Heap'in gosterim sekli
     */

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator myIter = levelOrderIterator();
        while(!myIter.hasNext()){
            sb.append(myIter.next() + " ");
        }
        return sb.toString();
    }

    // UnsupportedOperationException firlatacak methodlar

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E[] toArray(Object[] objects) {
        throw new UnsupportedOperationException();
    }


}
