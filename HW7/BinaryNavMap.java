/**
 * Created by syucer on 4/24/2017.
 */
import java.util.*;

public class BinaryNavMap<K extends Comparable<K>,V extends Comparable<V>> extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable {

    private static class myEntry<K extends Comparable,V extends Comparable> implements Comparable<myEntry<K,V>>,Entry<K,V> {
        //Data Fields
        private K key;
        private V value;

        //Methods

        public myEntry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public int compareTo(myEntry<K, V> comparedEntry) {
            if(key.compareTo(comparedEntry.key) > 0)
                return 1;
            else if (key.compareTo(comparedEntry.key) == 0)
                return 0;
            else
                return -1;
        }

        @Override
        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    //Data Fields

    private BinarySearchTree<myEntry<K,V>> myNavMap = new BinarySearchTree<>();

    public BinaryNavMap(){}

    /**
     * Copy Constructor
     * @param copy Kopyasi alinacak obje
     */

    BinaryNavMap (BinaryNavMap copy){
        Iterator<myEntry<K,V>> myIter = copy.myNavMap.InOrderIterator();
        myEntry<K,V> nextItem;
        while(!myIter.hasNext()){
            nextItem = myIter.next();
            myNavMap.add(nextItem);
        }
    }

    @Override
    public V put(K k, V v) {
        myEntry<K,V> newItem = new myEntry<K, V>(k,v);
        myNavMap.add(newItem);
        return newItem.getValue();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<myEntry<K,V>> set = new HashSet<>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        myEntry<K,V> nextItem;
        while(!myIter.hasNext()){
            nextItem = myIter.next();
            set.add(nextItem);
        }
        return (java.util.Set)set;

    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or {@code null} if there is
     * no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> lowerEntry(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp,previous;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        previous = myIter.next();
        if(previous.getKey().equals(key))
            return null;
        while(!(myIter.hasNext())){
            temp = myIter.next();
            if(temp.getKey().equals(key))
                return previous;
            previous = temp;
        }
        return null;
    }


    /**
     * Returns the greatest key strictly less than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K lowerKey(K key) {
        if(key == null){
            throw new NullPointerException();
        }
        Entry<K,V> temp,previous;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        previous = myIter.next();
        if(previous.getKey().equals(key))
            return null;
        while(!(myIter.hasNext())){
            temp = myIter.next();
            if(temp.getKey().equals(key))
                return previous.getKey();
            previous = temp;
        }
        return null;

    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> floorEntry(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp,previous;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        previous = myIter.next();
        if(previous.getKey().equals(key))
            return previous;
        else if (previous.getKey().compareTo(key) > 0)
            return previous;
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) < 0) {
                    previous = temp;
                }
                else if (temp.getKey().compareTo(key) == 0)
                    return temp;
                else{
                    return previous;
                }
            }
        }
        return previous;

    }

    /**
     * Returns the greatest key less than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K floorKey(K key) {
        if(key == null)
            throw  new NullPointerException();
        Entry<K,V> temp,previous;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        previous = myIter.next();
        if(previous.getKey().equals(key))
            return previous.getKey();
        else if (previous.getKey().compareTo(key) > 0)
            return previous.getKey();
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) < 0) {
                    previous = temp;
                }
                else if (temp.getKey().compareTo(key) == 0)
                    return temp.getKey();
                else{
                    return previous.getKey();
                }
            }
        }
        return previous.getKey();
    }

    /**
     * Returns a key-value mapping associated with the least key
     * greater than or equal to the given key, or {@code null} if
     * there is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        temp = myIter.next();
        if(temp.getKey().equals(key))
            return temp;
        else if (temp.getKey().compareTo(key) > 0)
            return temp;
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) > 0) {
                    return temp;
                }
                else if (temp.getKey().compareTo(key) == 0)
                    return temp;
            }
        }
        return temp;

    }

    /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K ceilingKey(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        temp = myIter.next();
        if(temp.getKey().equals(key))
            return temp.getKey();
        else if (temp.getKey().compareTo(key) > 0)
            return temp.getKey();
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) > 0) {
                    return temp.getKey();
                }
                else if (temp.getKey().compareTo(key) == 0)
                    return temp.getKey();
            }
        }
        return temp.getKey();

    }

    /**
     * Returns a key-value mapping associated with the least key
     * strictly greater than the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> higherEntry(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        temp = myIter.next();
        if (temp.getKey().compareTo(key) > 0)
            return temp;
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) > 0) {
                    return temp;
                }
            }
        }
        return null;

    }

    /**
     * Returns the least key strictly greater than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K higherKey(K key) {
        if(key == null)
            throw new NullPointerException();
        Entry<K,V> temp;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        temp = myIter.next();
        if (temp.getKey().compareTo(key) > 0)
            return temp.getKey();
        else{
            while(!(myIter.hasNext())){
                temp = myIter.next();
                if(temp.getKey().compareTo(key) > 0) {
                    return temp.getKey();
                }
            }
        }
        return null;
    }

    /**
     * Returns a key-value mapping associated with the least
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the least key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> firstEntry() {
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            return null;
        return myIter.next();
    }

    /**
     * Returns a key-value mapping associated with the greatest
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the greatest key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> lastEntry() {
        Entry<K,V> returnItem = new myEntry<>(null,null);
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            return null;
        while (!(myIter.hasNext())){
            returnItem = myIter.next();
        }
        return returnItem;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the least key in this map, or {@code null} if the map is empty.
     *
     * @return the removed first entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollFirstEntry() {
        myEntry<K,V> deleteItem;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            return null;
        deleteItem = myIter.next();
        myNavMap.delete(deleteItem);
        return deleteItem;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the greatest key in this map, or {@code null} if the map is empty.
     *
     * @return the removed last entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollLastEntry() {
        myEntry<K,V> deleteItem = new myEntry<K, V>(null,null);
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            return null;
        while (!myIter.hasNext()){
            deleteItem = myIter.next();
        }
        myNavMap.delete(deleteItem);
        return deleteItem;
    }

    /**
     * Returns a reverse order view of the mappings contained in this map.
     * The descending map is backed by this map, so changes to the map are
     * reflected in the descending map, and vice-versa.  If either map is
     * modified while an iteration over a collection view of either map
     * is in progress (except through the iterator's own {@code remove}
     * operation), the results of the iteration are undefined.
     * <p>
     * <p>The returned map has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code m.descendingMap().descendingMap()} returns a
     * view of {@code m} essentially equivalent to {@code m}.
     *
     * @return a reverse order view of this map
     */
    @Override
    public NavigableMap<K, V> descendingMap() {
        BinaryNavMap<K,V> binaryMap = new BinaryNavMap<>(this);
        binaryMap.myNavMap.reverse(binaryMap.myNavMap.root);
        return binaryMap;
    }

    /**
     * Returns a {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in ascending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> navigableKeySet() {
        NavigableSet<K> set = new TreeSet<K>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        while (!myIter.hasNext()){
            myEntry<K,V> item = myIter.next();
            set.add(item.getKey());
        }
        return set;
    }

    /**
     * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in descending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a reverse order navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> descendingKeySet() {
        NavigableSet<K> set = new TreeSet<>();
        ArrayList<myEntry<K,V>> list = new ArrayList<>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        while (!myIter.hasNext()){
            myEntry<K,V> item = myIter.next();
            list.add(item);
        }
        for(int i=0; i<list.size(); i++){
            set.add(list.get(i).getKey());
        }
        return set.descendingSet();
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
     * {@code toKey} are equal, the returned map is empty unless
     * {@code fromInclusive} and {@code toInclusive} are both true.  The
     * returned map is backed by this map, so changes in the returned map are
     * reflected in this map, and vice-versa.  The returned map supports all
     * optional map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside of its range, or to construct a
     * submap either of whose endpoints lie outside its range.
     *
     * @param fromKey       low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toKey         high endpoint of the keys in the returned map
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}
     * @throws ClassCastException       if {@code fromKey} and {@code toKey}
     *                                  cannot be compared to one another using this map's comparator
     *                                  (or, if the map has no comparator, using natural ordering).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} or {@code toKey}
     *                                  cannot be compared to keys currently in the map.
     * @throws NullPointerException     if {@code fromKey} or {@code toKey}
     *                                  is null and this map does not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than
     *                                  {@code toKey}; or if this map itself has a restricted
     *                                  range, and {@code fromKey} or {@code toKey} lies
     *                                  outside the bounds of the range
     */
    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        NavigableMap<K,V> navMap = new BinaryNavMap<>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        myEntry<K,V> nextItem;
        if(fromKey.compareTo(toKey) < 0) {
            if (fromInclusive && toInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().equals(fromKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    else if (nextItem.getKey().compareTo(fromKey) > 0 && nextItem.getKey().compareTo(toKey) < 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    } else if (nextItem.getKey().equals(toKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                }
            } else if (fromInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().equals(fromKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    else if (nextItem.getKey().compareTo(fromKey) > 0 && nextItem.getKey().compareTo(toKey) < 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    }
                }
            } else if (toInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().compareTo(fromKey) > 0 && nextItem.getKey().compareTo(toKey) < 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    } else if (nextItem.getKey().equals(toKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                }
            }
            else{
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().compareTo(fromKey) > 0 && nextItem.getKey().compareTo(toKey) < 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    }
                }
            }
        }
        else if (fromKey.compareTo(toKey) > 0){
            if (fromInclusive && toInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().equals(fromKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    else if (nextItem.getKey().compareTo(fromKey) < 0 && nextItem.getKey().compareTo(toKey) > 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    } else if (nextItem.getKey().equals(toKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                }
            } else if (fromInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().equals(fromKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    else if (nextItem.getKey().compareTo(fromKey) < 0 && nextItem.getKey().compareTo(toKey) > 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    }
                }
            } else if (toInclusive) {
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().compareTo(fromKey) < 0 && nextItem.getKey().compareTo(toKey) > 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    }
                    else if (nextItem.getKey().equals(toKey))
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                }
            }
            else{
                while (!myIter.hasNext()) {
                    nextItem = myIter.next();
                    if (nextItem.getKey().compareTo(fromKey) < 0 && nextItem.getKey().compareTo(toKey) > 0) {
                        navMap.put(nextItem.getKey(), nextItem.getValue());
                    }
                }
            }
        }
        return navMap;
    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey     high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than
     * (or equal to, if {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException       if {@code toKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code toKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code toKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code toKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        NavigableMap<K,V> navMap = new BinaryNavMap<>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        myEntry<K,V> nextItem;
        if(toKey == null){
            throw new NullPointerException();
        }
        if(inclusive){
            while(!myIter.hasNext()){
                nextItem = myIter.next();
                if(nextItem.getKey().compareTo(toKey) < 0){
                    navMap.put(nextItem.getKey(),nextItem.getValue());
                }
                else if(nextItem.getKey().equals(toKey)){
                    navMap.put(nextItem.getKey(),nextItem.getValue());
                }
            }
        }
        else{
            while(!myIter.hasNext()) {
                nextItem = myIter.next();
                if (nextItem.getKey().compareTo(toKey) < 0) {
                    navMap.put(nextItem.getKey(), nextItem.getValue());
                }
            }
        }
        return navMap;
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param fromKey   low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     * (or equal to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException       if {@code fromKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code fromKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code fromKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code fromKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        NavigableMap<K,V> navMap = new BinaryNavMap<>();
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        myEntry<K,V> nextItem;
        if(fromKey == null){
            throw new NullPointerException();
        }
        if(inclusive){
            while(!myIter.hasNext()){
                nextItem = myIter.next();
                if(nextItem.getKey().compareTo(fromKey) > 0){
                    navMap.put(nextItem.getKey(),nextItem.getValue());
                }
                else if(nextItem.getKey().equals(fromKey)){
                    navMap.put(nextItem.getKey(),nextItem.getValue());
                }
            }
        }
        else{
            while(!myIter.hasNext()){
                nextItem = myIter.next();
                if(nextItem.getKey().compareTo(fromKey) > 0){
                    navMap.put(nextItem.getKey(),nextItem.getValue());
                }
            }
        }
        return navMap;
    }

    /**
     * Returns the comparator used to order the keys in this map, or
     * {@code null} if this map uses the {@linkplain Comparable
     * natural ordering} of its keys.
     *
     * @return the comparator used to order the keys in this map,
     * or {@code null} if this map uses the natural ordering
     * of its keys
     */
    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @param fromKey
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        SortedMap<K,V> sort;
        sort = subMap(fromKey,true,toKey,true);
        return sort;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code headMap(toKey, false)}.
     *
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap<K,V> sort;
        sort = headMap(toKey,true);
        return sort;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @param fromKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap<K,V> sort;
        sort = tailMap(fromKey,true);
        return sort;
    }

    /**
     * Returns the first (lowest) key currently in this map.
     *
     * @return the first (lowest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K firstKey() {
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            throw new NoSuchElementException();
        K key = myIter.next().getKey();
        return key;
    }

    /**
     * Returns the last (highest) key currently in this map.
     *
     * @return the last (highest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K lastKey() {
        K key = null;
        Iterator<myEntry<K,V>> myIter = myNavMap.InOrderIterator();
        if(myIter.hasNext())
            throw new NoSuchElementException();
        while(!myIter.hasNext()){
            key = myIter.next().getKey();
        }
        return key;
    }
}