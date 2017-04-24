package tr.edu.gtu.devrimakinci.q3; /**
 * @author Devrim Akıncı
*/

import java.util.LinkedList;

public class PriorityQueueA<E> extends LinkedList<E> {

    //Methods

    /**
     * No Parameter Constructor
     */

    public PriorityQueueA(){
        super();
    }

    /**
     * Bu method Queue'nun bos olup olmadigini kontrol eder.
     * @return Queue bos ise true, Queue dolu ise false return eder.
     */

    @Override
    public boolean isEmpty() {
        if(size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Bu method Queue'nun boyutunu dondurur.
     * @return Queue'nun boyutu
     */

    @Override
    public int size() {
        return super.size();
    }

    /**
     * Bu method Queue'nnu sonuna eleman ekler.
     * @param data Queue'ya eklenencek eleman
     * @return Basarili ise true, degilse false return eder.
     */

    @Override
    public boolean add(E data) {
        addLast(data);
        return true;
    }

    /**
     * Bu method Queue'nun icindeki en kucuk elemani siler ve onu dondurur.
     * @return Qeueu'nun icindeki en kucuk eleman
     */

    public E deletedMin(){
        E data = null;
        int index = findMin(); // Silinecek elemanin Queue'nun hangi indekste oldugunun bulunmasi
        if (index == size() - 1){ // Eger silinecek eleman Queue'nun sonunda ise
            for(int i=0; i<index; i++){
                add(removeFirst());
            }
            data = removeFirst();
        }
        else{
            for (int i = 0; i < size(); i++) {
                if (i == index) {
                    data = removeFirst();
                }
                add(removeFirst());
            }
        }
        return data;
    }

    /**
     * Bu method Queue'nun icindeki en kucuk elemanin indeksini bulur ve onu dondurur.
     * @return Queue'nun icindeki en kucuk elemanin indeksi
     */

    private int findMin(){
        E result = get(0);
        int found = 0;
        for(int i=1; i<size(); i++){
            // Eleman tipi Integer ise
            if(result.getClass().getName().contains("Integer") &&
                    get(i).getClass().getName().contains("Integer")){
                if((Integer)result > (Integer)get(i)){
                    E data = get(i);
                    result = data;
                    found = i;
                }
            }
            // Eleman tipi Double ise
            else if(result.getClass().getName().contains("Double") &&
                    get(i).getClass().getName().contains("Double")){
                if((Double)result > (Double)get(i)){
                    E data = get(i);
                    result = data;
                    found = i;
                }
            }
            // Eleman tipi Character ise
            else if(result.getClass().getName().contains("Character") &&
                    get(i).getClass().getName().contains("Character")){
                if ((Character)result > (Character)get(i)){
                    E data = get(i);
                    result = data;
                    found = i;
                }
            }
            // Eleman tipi String ise
            else if (result.getClass().getName().contains("String") &&
                    get(i).getClass().getName().contains("String")){
                if(compare((String)result, (String)get(i)) == 1){
                    E data = get(i);
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