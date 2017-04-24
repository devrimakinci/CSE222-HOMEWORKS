package tr.edu.gtu.devrimakinci.q2; /**
 * @author Devrim Akıncı
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class myQueue<E> extends KWLinkedList<E> {

    // Methods

    /**
     * No parameter constructor
     */
    public myQueue(){
        super();
    }

    /**
     * Bu method Queue'ya eleman ekler
     * @param item Queue'ya eklenecek eleman
     */

    public void offer(E item){
        if(size() == 0){
            add(0,item);
        }
        else
            add(size(),item);
    }

    /**
     * Bu method Queue'dan eleman siler.
     * @return Queue'dan silinen elemani dondurur.
     * @throws Queue bos ise NoSuchElementException firlatir.
     */

    public E remove(){
        if (size() == 0)
            throw new NoSuchElementException();
        else
            return removeFirst();
    }

    /**
     * Bu method Queue'dan eleman siler.
     * @return Queue bos ise null, bos degilse silinen elemani dondurur.
     */

    public E poll(){
        if (size() == 0)
            return null;
        else
            return removeFirst();
    }

    /**
     * Bu method Queue'dan eleman silmeden elemanin degerine bakmamizi saglar
     * @return Queue bos ise null, bos degilse elemani dondurur
     */

    public E peek(){
        if (size() == 0)
            return null;
        return get(0);
    }

    /**
     * Bu method Queue'dan eleman silmeden elemanin degerine bakmamizi saglar
     * @return Queue bos degilse elemani dondurur
     * @throws Queue bos ise NoSuchElementException firlatir
     */

    public E element(){
        if(size() == 0)
            throw new NoSuchElementException();
        else
            return get(0);
    }

    /**
     * Bu method verilen Queue'yu recursive olarak ters cevirir.
     * @param obj Verilen Queue objesi
     */

    public void reverseQueue(Queue<E> obj){
        ArrayList<E> myList = new ArrayList<>();
        while(!(obj.isEmpty()))
            myList.add(obj.poll());
        recursiveHelper(obj,myList,myList.size() - 1);
    }

    /**
     * Bu method recursive islemini yapan yardimci methoddur.
     * @param myObj Verilen Queue objesi
     * @param list Queue'nun elemanlarini tutan ArrayList
     * @param size ArrayList'in boyutu
     */

    private void recursiveHelper(Queue<E> myObj,ArrayList<E> list, int size){
        if (size < 0) {
            return;
        }
        myObj.offer(list.remove(size));
        recursiveHelper(myObj,list,size - 1);
    }

    /**
     * Bu method Queue'nun elemanlarinin ters cevirir.
     */

    public void reverse(){
        ArrayList<E> myList = new ArrayList<E>();
        while(!(isEmpty()))
            myList.add(poll());
        for (int i=myList.size()-1; i>-1; i--){
            offer(myList.get(i));
        }
    }
}
