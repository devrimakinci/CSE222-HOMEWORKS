package tr.edu.gtu.devrimakinci.q1; /**
 * @authot Devrim Akıncı
 */

import java.util.LinkedList;
import java.util.Queue;

public class StackD<E> implements StackInterface<E> {

    //Data Fields
    private int size=0; //Stack'in boyutu
    Queue<E> myQueue = new LinkedList<E>();

    //Methods

    /**
     * Bu method stack'e yeni bir eleman ekler.
     * @param obj Eklenecek eleman
     * @return Eklenen elemani dondurur.
     */

    @Override
    public E push(E obj) {
        myQueue.offer(obj);
        size++;
        return obj;
    }

    /**
     * Bu method stack'den eleman cikartir.
     * @return Cikarilan elemani dondurur.
     */

    @Override
    public E pop() {
        if(isEmpty()){
            System.err.println("The stack is empty!\n");
            System.exit(1);
        }
        reverseQueue(size()-1);
        size--;
        return myQueue.remove();
    }

    /**
     * Bu method Queue'u recursive olarak ters dondurur.
     * @param cond Belirlenen sart
     */

    private void reverseQueue(int cond){
        if (cond == 0) //Base case
            return;
        //Silinen elemanin Queue'nun son sirasina eklenmesi
        myQueue.offer(myQueue.remove());
        reverseQueue(cond - 1);
    }

    /**
     * Bu method stack'in bos olup olmadigina bakar.
     * @return Stack bos ise true, dolu ise false return eder.
     */

    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Bu method stack'deki toplam eleman sayisini bulur.
     * @return Toplam eleman sayisini return eder.
     */

    @Override
    public int size() {
        return size;
    }
}
