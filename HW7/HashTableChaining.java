import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by syucer on 4/24/2017.
 */
public class HashTableChaining<K, V> implements HashMap<K, V> {
    /** The table */
    //private HashTableOpen<Entry<K, V>>[] table;
    //Do not forget you can use more class and methods to do this homework,
    // this project gives you an initial classes an methods to see easily
    //....
    //.... other class members


    private class Entry<K,V>{
        //Data Fields
        private K key;
        private V value;

        //Methods

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

    // Data Fields

    private LinkedList<Entry<K,V>> []table;
    private int numberKeys;
    private final int CAP = 101;
    private final double LOADFACTOR = 3.0;

    //Methods

    public HashTableChaining(){
        table = new LinkedList[CAP];
    }

    @Override
    public V get(Object key) {
        int hash = key.hashCode();
        int tableIndex = hash % table.length;
        if (tableIndex < 0){
            tableIndex = tableIndex + table.length;
        }
        if (table[tableIndex] == null)
            return null;
        for(Entry<K, V> nextItem : table[tableIndex]){
            if(nextItem.key.equals(key))
                return nextItem.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = key.hashCode();
        int tableIndex = hash % table.length;
        if (tableIndex < 0){
            tableIndex = tableIndex + table.length;
        }
        if(table[tableIndex] == null){
            LinkedList<Entry<K,V>> newItem = new LinkedList<>();
            table[tableIndex] = newItem;
        }
        for(Entry<K,V> nextItem : table[tableIndex]){
            if(table[tableIndex] != null && !(nextItem.key.equals(key))){
                tableIndex = hash % ((table.length * 2) + 1); // Double Hashing
                if(tableIndex < 0){
                    tableIndex = tableIndex + ((table.length * 2) + 1);
                }
                if(table[tableIndex] == null) {
                    LinkedList<Entry<K, V>> newItem = new LinkedList<>();
                    table[tableIndex] = newItem;
                }
            }
            if(nextItem.key.equals(key)){
                table[tableIndex].addFirst(new Entry<K, V>(key, value));
                return nextItem.value;
            }
        }
        table[tableIndex].addFirst(new Entry<K, V>(key, value));
        numberKeys++;
        if(numberKeys > LOADFACTOR * table.length)
            rehash();
        return null;
    }

    @Override
    public V remove(Object key) {
        int tableIndex = key.hashCode() % table.length;
        if (tableIndex < 0) {
            tableIndex = tableIndex + table.length;
        }
        if (table[tableIndex] == null) {
            return null;
        }
        Iterator<Entry<K, V>> myIter = table[tableIndex].iterator();
        while (myIter.hasNext()) {
            Entry<K, V> item = myIter.next();
            if (item.key.equals(key)) {
                V returnedValue = item.value;
                myIter.remove();
                if(table[tableIndex].size() == 0)
                    numberKeys--;
                return returnedValue;
            }
        }
        tableIndex = key.hashCode() % ((table.length * 2) + 1); // Double Hashing
        if(tableIndex < 0){
            tableIndex = tableIndex + ((table.length * 2) + 1);
        }
        if (table[tableIndex] == null) {
            return null;
        }
        Iterator<Entry<K, V>> iter = table[tableIndex].iterator();
        while (iter.hasNext()) {
            Entry<K, V> item = iter.next();
            if (item.key.equals(key)) {
                V returnedValue = item.value;
                iter.remove();
                if(table[tableIndex].size() == 0)
                    numberKeys--;
                return returnedValue;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return numberKeys;
    }

    @Override
    public boolean isEmpty() {
        if(numberKeys == 0)
            return true;
        else
            return false;
    }

    public void rehash(){
        LinkedList<Entry<K,V>> [] oldTable = table;
        table = new LinkedList[(2 * oldTable.length) + 1];
        numberKeys = 0;
        for(int i=0; i < oldTable.length; i++){
            if(oldTable[i] != null){
                for (Entry<K,V> nextItem : oldTable[i]){
                    put(nextItem.key,nextItem.value);
                }
            }
        }
    }
}
