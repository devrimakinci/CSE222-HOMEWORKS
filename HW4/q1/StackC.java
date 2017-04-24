package tr.edu.gtu.devrimakinci.q1;

/**
 * @author Devrim Akıncı
 */

public class StackC<E> implements StackInterface<E> {

    //Data Fields
    private int size=0; //Stack'in boyutu
    Node<E> topStack = new Node<E>();

    //Methods

    public StackC(){}

    /**
     * Bu method stack'e yeni bir eleman ekler.
     * @param obj Eklenecek eleman
     * @return Eklenen elemani dondurur.
     */

    @Override
    public E push(E obj) {
        topStack = new Node<E>(obj,topStack);
        size++;
        return obj;
    }

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
        Node<E> temp = topStack;
        topStack = topStack.next;
        size--;
        return temp.data;
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

    private static class Node<E>{
        //Data Fields
        private Node<E> next;
        private E data;

        //Methods

        /**
         * No parameter Constructor
         */

        public Node(){
            next = null;
        }

        public Node(E data){
            this.data = data;
            next = null;
        }

        /**
         * Constructor
         * @param data Saklanacak veri
         * @param node Node
         */

        public Node(E data, Node<E> node){
            this.data = data;
            next = node;
        }
    }
}
