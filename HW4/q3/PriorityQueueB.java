package tr.edu.gtu.devrimakinci.q3; /**
 * @author Devrim Akıncı
 */

import java.util.LinkedList;

public class PriorityQueueB<E> {

    LinkedList<E> list = new LinkedList<>();

    //Methods

    /**
     * No parameter constructor
     */

    public PriorityQueueB(){
    }

    /**
     * Bu method Queue'nun bos olup olmadigini kontrol eder.
     * @return Queue bos ise true, Queue dolu ise false return eder.
     */

    public boolean isEmpty(){
        if(list.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Bu method Queue'nun boyutunu dondurur.
     * @return Queue'nun boyutu
     */

    public int size(){
        return list.size();
    }

    /**
     * Bu method Queue'nnu sonuna eleman ekler.
     * @param data Queue'ya eklenencek eleman
     */

    public void add(E data){
        list.addLast(data);
    }

    /**
     * Bu method Queue'nun basinda elemani siler.
     * @return Silinen eleman
     */

    public E poll(){
        return list.poll();
    }

    /**
     * Bu method Queue'nun icindeki en kucuk elemani siler ve onu dondurur.
     * @return Qeueu'nun icindeki en kucuk eleman
     */

    public E deletedMin(){
        E data = null;
        int index = findMin();
        if (index == size() - 1){
            for(int i=0; i<index; i++){
                add(list.removeFirst());
            }
            data = list.removeFirst();
        }
        else{
            for (int i = 0; i < size(); i++) {
                if (i == index) {
                    data = list.removeFirst();
                }
                add(list.removeFirst());
            }
        }
        return data;
    }

    /**
     * Bu method Queue'nun icindeki en kucuk elemanin indeksini bulur ve onu dondurur.
     * @return Queue'nun icindeki en kucuk elemanin indeksi
     */

    private int findMin(){
        E result = list.get(0);
        int found = 0;
        for(int i=1; i<size(); i++){
            if(result.getClass().getName().contains("Integer") && list.get(i).getClass().getName().contains("Integer")){
                if((Integer)result > (Integer)list.get(i)){
                    E data = list.get(i);
                    result = data;
                    found = i;
                }
            }
            else if(result.getClass().getName().contains("Double") &&
                    list.get(i).getClass().getName().contains("Double")){
                if((Double)result > (Double)list.get(i)){
                    E data = list.get(i);
                    result = data;
                    found = i;
                }
            }
            else if(result.getClass().getName().contains("Character") &&
                    list.get(i).getClass().getName().contains("Character")){
                if ((Character)result > (Character)list.get(i)){
                    E data = list.get(i);
                    result = data;
                    found = i;
                }
            }
            else if (result.getClass().getName().contains("String") &&
                    list.get(i).getClass().getName().contains("String")){
                if(compare((String)result, (String)list.get(i)) == 1){
                    E data = list.get(i);
                    result = data;
                    found = i;
                }
            }
        }
        return found;
    }

    /**
     * Bu method iki stringi karsilastirir.
     * @param str1 Karsilastirilacak birinci String
     * @param str2 Karsilastirilacak ikinci String
     * @return Iki string birbirine esit ise 0, birinci string ikinci string'den buyuk ise 0'dan buyuk bir deger
     * birinci string ikinci string'den kucuk ise 0'dan kucuk bir deger dondurur.
     */

    private int compare(String str1, String str2){
        if (str1.compareTo(str2) == 0) // Esit oldugu durumda
            return 0;
        else if (str1.compareTo(str2) < 0) // Kucuk oldugu durumda
            return -1;
        else // Buyuk oldugu durumda
            return 1;
    }

}
