package tr.edu.gtu.devrimakinci.q1; /**
 * @author Devrim Akıncı
 */

import java.util.ArrayList;

public class StackB<E>implements StackInterface<E> {

    ArrayList<E> list = new ArrayList<E>();
    //Methods

    /**
     * No parameter Constructor
     */

    public StackB(){}

    /**
     * Bu method stack'den eleman cikartir.
     * @return Cikarilan elemani dondurur.
     */

    @Override
    public E pop() {
        if (isEmpty()){
            System.err.println("The stack is empty!\n");
            System.exit(1);
        }
        return list.remove(list.size() - 1);
    }

    /**
     * Bu method stack'e yeni bir eleman ekler.
     * @param obj Eklenecek eleman
     * @return Eklenen elemani dondurur.
     */

    @Override
    public E push(E obj) {
        list.add(obj);
        return obj;
    }

    /**
     * Bu method stack'in bos olup olmadigina bakar.
     * @return Stack bos ise true, dolu ise false return eder.
     */

    @Override
    public boolean isEmpty() {
        if (list.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Bu method stack'deki toplam eleman sayisini bulur.
     * @return Toplam eleman sayisini return eder.
     */

    @Override
    public int size(){
        return list.size();
    }
}
