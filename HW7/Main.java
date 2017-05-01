import java.util.NavigableMap;
import java.util.SortedMap;


public class Main
{
    public static void main(String args[]){
        final Boolean q1 = Q1Test();
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            System.out.println("Your tests is done. Make sure that you test all methods of class!! " );
            return;
        }
    }
    public static Boolean Q1Test(){

        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        NavigableMap<String,String> descendingTurkey;
        turkey.put("uskudar","istanbul");
        turkey.put("kadıkoy","istanbul");
        turkey.put("cekirge","bursa");
        turkey.put("gebze","kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecıoren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca","izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta","adıyaman");
        turkey.put("biga","canakkale");

        //Test entrySet method
        System.out.printf("Entry set is %s.\n",turkey.entrySet());

        // Test firstKey method
        System.out.printf("First key is %s.\n",turkey.firstKey());

        // Test lastKey method
        System.out.printf("Last key is %s.\n",turkey.lastKey());

        //Test firstEntry method
        System.out.printf("First entry is %s.\n",turkey.firstEntry());

        //Test lastEntry method
        System.out.printf("Last entry is %s.\n",turkey.lastEntry());

        //Test lowerEntry method
        System.out.printf("Lower entry is %s.\n",turkey.lowerEntry("gebze"));

        //Test lowerKey method
        System.out.printf("Lower key is %s.\n",turkey.lowerKey("gebze"));

        //Test floorEntry method
        System.out.printf("Floor entry is %s.\n",turkey.floorEntry("gelibolu"));

        //Test floorKey method
        System.out.printf("Floor key is %s.\n",turkey.floorKey("foca"));

        //Test ceilingEntry method
        System.out.printf("Ceiling entry is %s.\n",turkey.ceilingEntry("kadıkoy"));

        //Test ceilingKey method
        System.out.printf("Ceiling key is %s.\n",turkey.ceilingKey("kartal"));

        //Test higherEntry method
        System.out.printf("Higher entry is %s.\n",turkey.higherEntry("aksaray"));

        //Test higherKey method
        System.out.printf("Higher key is %s.\n",turkey.higherKey("cekirge"));

        //Test pollFirstEntry and checking whehter it is true
        System.out.printf("Deleting entry is %s.\n",turkey.pollFirstEntry());
        System.out.printf("First entry is %s.\n",turkey.firstEntry());

        //Test pollLastEntry and checking whehter it is true
        System.out.printf("Deleting entry is %s.\n",turkey.pollLastEntry());
        System.out.printf("Last entry is %s.\n",turkey.lastEntry());

        //Test descendingMap method
        descendingTurkey = turkey.descendingMap();
        System.out.printf("Descending first entry is %s.\n",descendingTurkey.firstEntry());
        System.out.printf("Descending last entry is %s.\n",descendingTurkey.lastEntry());

        //Test navigableKeySet method and descendingKeySet
        System.out.printf("Key set is %s.\n",turkey.navigableKeySet());
        System.out.printf("Descending key set is %s.\n",turkey.descendingKeySet());

        //Test subMap method
        NavigableMap<String,String> subMap = turkey.subMap("manavgat",true,"gebze",false);
        System.out.println("SubMap is " + subMap);

        //Test headMap method
        NavigableMap<String,String> headMap = turkey.headMap("cekirge",true);
        System.out.println("HeadMap is " + headMap);

        //Test tailMap method
        NavigableMap<String,String> tailMap = turkey.tailMap("foca",false);
        System.out.println("TailMap is " + tailMap);

        //Test sorted subMap
        SortedMap<String,String> sortSubMap = turkey.subMap("biga","gebze");
        System.out.println("SortSubMap is " + sortSubMap);

        //Test sorted headMap
        SortedMap<String,String> sortHeadMap = turkey.headMap("gebze");
        System.out.println("SortHeadMap is " + sortHeadMap);

        //Test sorted tailMap
        SortedMap<String,String> sortTailMap = turkey.tailMap("kecioren");
        System.out.println("SortTailMap is " + sortTailMap);


        //you should write more test function to show your solution
        //your test must contain all methods to get full points!!!
        //you also may need to owerwrite some methods to provide BST RULES

        /* *some links to help you

           https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
           https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html


     * */
        return Boolean.TRUE;

    }
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<String,String>();
        turkey.put("edremit","balikesir");
        turkey.put("edremit","van");
        turkey.put("kemalpasa","bursa");
        turkey.put("kemalpasa","izmir");
        turkey.put("ortakoy","istanbul");//we assume a district
        turkey.put("ortakoy","aksaray");
        turkey.put("ortakoy","corum");
        turkey.put("kecıoren","ankara");
        turkey.put("pinarbasi","kastamonu");
        turkey.put("pinarbasi","kayseri");
        turkey.put("eregli","konya");
        turkey.put("eregli","tekirdag");
        turkey.put("eregli","zonguldak");
        turkey.put("golbasi","adıyaman");
        turkey.put("golbasi","ankara");
        turkey.put("biga","canakkale");

        //Test Table Size

        System.out.printf("Table size is %d.\n",turkey.size());

        //Test get method

        System.out.printf("City is %s.\n",turkey.get("ortakoy"));

        //Test put method

        turkey.put("goztepe","izmir");
        turkey.put("goztepe","istanbul");

        // Checking put method

        System.out.printf("Table size is %d.\n",turkey.size());

        //Remove method

        System.out.printf("Removed city is %s.\n",turkey.remove("biga"));
        System.out.printf("Removed city is %s.\n",turkey.remove("ortakoy"));

        // Checking remove method

        System.out.printf("Table size is %d.\n",turkey.size());

        System.out.printf("City is %s.\n",turkey.get("goztepe"));

        //Test isEmpty method

        if(turkey.isEmpty())
            System.out.println("Table is empty.");
        else
            System.out.println("Table is not empty.");

        return Boolean.TRUE;
    }


}
