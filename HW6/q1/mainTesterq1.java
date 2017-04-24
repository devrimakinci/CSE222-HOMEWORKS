package tr.edu.gtu.devrimakinci.q1;

/**
 * @author Devrim Akıncı
 */
public class mainTesterq1 {

    public static void main(String args[]){

        //Binary bir heap olusturma

        BinaryHeap<Integer> myHeap = new BinaryHeap<>();


        //Heap'e eleman ekleme

        myHeap.offer(5);
        myHeap.offer(3);
        myHeap.offer(7);
        myHeap.offer(4);
        myHeap.offer(2);
        myHeap.offer(1);
        myHeap.offer(8);


        System.out.println(myHeap.toString());

        //Peek testi

        System.out.println("FIRST ELEMENT OF HEAP:" + myHeap.peek());

        //Heap'den eleman cikarma(pool testi)

        myHeap.poll();

        System.out.println(myHeap.toString());

        //Peek testi

        System.out.println("FIRST ELEMENT OF HEAP:" + myHeap.peek());

        myHeap.poll();

        System.out.println(myHeap.toString());

        //Peek testi

        System.out.println("FIRST ELEMENT OF HEAP:" + myHeap.peek());


    }
}
