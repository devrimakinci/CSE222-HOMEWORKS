/**
 * @author Devrim Akıncı
 */

import java.util.AbstractCollection;

abstract public class myAbstractCollection<E> extends AbstractCollection{

    /**
     * Bu method kendisini cagiran objeye verilen bir AbstractCollection objesini ekler.
     * @param abstractObj - AbstractCollection'dan turettigimiz obje
     */

    public void appendAnything(myAbstractCollection<E> abstractObj){
        this.addAll(abstractObj);
    }
}