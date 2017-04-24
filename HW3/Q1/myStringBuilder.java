/**
 * @author Devrim Akıncı
 */

import java.util.Objects;
import java.util.Iterator;

public class myStringBuilder<E> {
    //Data fields
    private String str; //Cevirilecek string
    private SingleLinkedList<E> myList; // Verileri tutan SingleLinkedList

    //Methods

    /**
     * No parameter constructor
     */

    public myStringBuilder(){
        myList = new SingleLinkedList<E>();
        this.str = "";
    }

    /**
     * Constructor
     * @param str - Verilen string
     */

    public myStringBuilder(String str){
        this.str = str;
        myList = new SingleLinkedList<E>();
    }

    /**
     *
     * @return String'i alir.
     */

    public String getString() {
        return str;
    }

    /**
     *
     * @return SingleLinkedList'i alir.
     */

    public SingleLinkedList getLinkedList(){
        return myList;
    }

    /**
     * Verilen integer bir degiskeni stringin sonuna ekler.
     * @param i - Integer bir degisken
     */

    public void append(int i){
        this.str = getString().concat(Integer.toString(i));
    }

    /**
     * Verilen string tipinde bir degiskeni stringin sonuna ekler.
     * @param str - String tipinde bir degisken
     */

    public void append(String str){
        this.str = getString().concat(str);
    }

    /**
     * Verilen double tipinde bir degiskeni stringin sonuna ekler.
     * @param d - Double tipinde bir degisken
     */

    public void append(double d){
        this.str = getString().concat(Double.toString(d));
    }

    /**
     * Verilen double tipinde bir degiskeni stringin sonuna ekler.
     * @param f - Float tipinde bir degisken
     */

    public void append(float f){
        this.str = getString().concat(Float.toString(f));
    }

    /**
     * Verilen long tipinde bir degiskeni stringin sonuna ekler.
     * @param l - Long tipinde bir degisken
     */

    public void append(long l){
        this.str = getString().concat(Long.toString(l));
    }

    /**
     * Verilen char tipinde bir degiskeni stringin sonuna ekler.
     * @param c - Char tipinde bir degisken
     */

    public void append(char c){
        this.str = getString().concat(Character.toString(c));
    }

    /**
     * Verilen boolean tipinde bir degiskeni stringin sonuna ekler.
     * @param bool - Boolean tipinde bir degisken
     */

    public void append (boolean bool){
        this.str = getString().concat(Boolean.toString(bool));
    }

    /**
     * Verilen object tipinde bir degiskeni stringin sonuna ekler.
     * @param obj - Object tipinde bir degisken
     */

    public void append (Object obj){
        this.str = getString().concat(Objects.toString(obj));
    }

    /**
     *
     * @return Indeksleri ve get methodunu kullanarak SingleLinkedList'deki veriyi stringe cevirir.
     */

    public String toString1(){
        this.str = "";
        for(int i=0; i<getLinkedList().getSize(); i++){
            append(getLinkedList().get(i));
            append(" ");
        }
        return this.str;
    }

    /**
     *
     * @return SingleLinkedList'in toString methodunu kullanarak SingleLinkedList'deki veriyi stringe cevirir.
     */

    public String toString2(){
        return getLinkedList().toString();
    }

    /**
     *
     * @return Iterasyonu kullanarak SingleLinkedList'deki veriyi stringe cevirir.
     */

    public String toString3(){
        this.str = "";
        Iterator<E> iter = myList.iterator();
        while(iter.hasNext()){
            append(iter.next());
            append (" ");
        }
        return this.str;
    }
}
