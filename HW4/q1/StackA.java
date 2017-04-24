package tr.edu.gtu.devrimakinci.q1; /**
 * @author Devrim Akıncı
 */

import java.util.ArrayList;

public class StackA<E>extends ArrayList implements StackInterface<E>{

    //Methods

    /**
     * No parameter Constructor
     */

    public StackA(){
        super();
    }

    /**
     * Bu method stack'e yeni bir eleman ekler.
     * @param obj Eklenecek eleman
     * @return Eklenen elemani dondurur.
     */

    @Override
    public E push(E obj){
        add(obj);
        return obj;
    }

    /**
     * Bu method stack'den eleman cikartir.
     * @return Cikarilan elemani dondurur.
     */

    @Override
    public E pop(){
        if (isEmpty()){
            System.err.println("The stack is empty!\n");
            System.exit(1);
        }
        return (E)remove(size() - 1);
    }

    /**
     * Bu method stack'deki toplam eleman sayisini bulur.
     * @return Toplam eleman sayisini return eder.
     */

    @Override
    public int size(){
        return super.size();
    }

    /**
     * Bu method stack'in bos olup olmadigina bakar.
     * @return Stack bos ise true, dolu ise false return eder.
     */

    @Override
    public boolean isEmpty(){
        if (super.size() == 0)
            return true;
        else
            return false;
    }
}
