package tr.edu.gtu.devrimakinci.q1;

/**
 * @author Devrim Akıncı
 */

public interface StackInterface<E> {

    /**
     * Bu method stack'den eleman cikartir.
     * @return Cikarilan elemani dondurur.
     */
    public E pop();

    /**
     * Bu method stack'e yeni bir eleman ekler.
     * @param obj Eklenecek eleman
     * @return Eklenen elemani dondurur.
     */

    public E push(E obj);

    /**
     * Bu method stack'in bos olup olmadigina bakar.
     * @return Stack bos ise true, dolu ise false return eder.
     */

    public boolean isEmpty();

    /**
     * Bu method stack'deki toplam eleman sayisini bulur.
     * @return Toplam eleman sayisini return eder.
     */

    public int size();
}
