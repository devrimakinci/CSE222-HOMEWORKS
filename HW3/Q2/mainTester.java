/**
 * @author Devrim Akıncı
 */

public class mainTester {

    public static void main(String []args){

        SingleLinkedList<String> myList1 = new SingleLinkedList();
        SingleLinkedList<Integer> myList2 = new SingleLinkedList();
        SingleLinkedList<Double> myList3 = new SingleLinkedList();

        // SingleLinkedList'e string ekleme ve test islemi
        myList1.addFirst("GTU");
        myList1.addFirst("Bilgisayar Muh.");
        myList1.addFirst("141044052");
        myList1.addFirst("Akinci");
        myList1.addFirst("Devrim");

        System.out.println("TEST-1(String)");
        System.out.println("Regular Order:" + myList1.toString());
        System.out.println("Reverse Order:" + myList1.toReverseString());

        //SingleLinkedList'e integer ekleme ve test islemi
        myList2.addFirst(7);
        myList2.addFirst(35);
        myList2.addFirst(-26);
        myList2.addFirst(45);
        myList2.addFirst(10);
        myList2.addFirst(1);
        myList2.addFirst(123);
        myList2.addFirst(-24);

        System.out.println("TEST-2(Integer)");
        System.out.println("Regular Order:" + myList2.toString());
        System.out.println("Reverse Order:" + myList2.toReverseString());

        //SingleLinkedList'e double ekleme ve test islemi
        myList3.addFirst(-7.56);
        myList3.addFirst(0.35);
        myList3.addFirst(2.5);
        myList3.addFirst(3.36);
        myList3.addFirst(10.04);
        myList3.addFirst(1.1982);
        myList3.addFirst(123.123);
        myList3.addFirst(-24.48);

        System.out.println("TEST-3(Double)");
        System.out.println("Regular Order:" + myList3.toString());
        System.out.println("Reverse Order:" + myList3.toReverseString());
    }
}