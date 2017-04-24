package tr.edu.gtu.devrimakinci.q2;


import java.util.Iterator;

/**
 * @author Devrim Akıncı
 */


public class FamilyTree<E> extends BinaryTree {

    //Data Fields

    private boolean isAdd; // Ekleme isleminin basarili olup olmadigini kontrol eden flag

    //Methods

    /**
     * No parameter Constructor
     */

    public  FamilyTree(){
        super();
    }

    /**
     * Constructor
     * @param root Agacin root node'u
     */

    public FamilyTree(Node<E> root) {
        super(root);
    }

    /**
     * Bu method FamilyTree'ye kisi ekler
     * @param name Eklenecek kisi
     * @param parentName Eklenecek kisinin annesinin veya babasinin adi
     * @param nickname Eklenecek kisinin annesinin veya babasinin nickname
     * @return Basarili ise true basarili degilse false
     */

    public boolean add(String name, String parentName,String nickname){
        try{
            root = add(root,name,parentName,nickname); //Recursive olarak ekleme islemi yapilacak
            return isAdd;
        }
        catch (myException e){ // Eger basarisiz bir islem olmussa myException'i yakalayacak
            e.errorMessage(); // Hata mesaji
        }
        return isAdd;
    }

    /**
     * Bu method recursive olarak FamilyTree'ye eleman ekler.
     * @param node Verilen node
     * @param name Eklenecek kisi
     * @param parentName Eklenecek kisinin annesinin veya babasinin adi
     * @param nickname Eklenecek kisinin annesinin veya babasinin nickname
     * @return Node
     * @throws myException
     */

    private Node<E> add(Node<E> node,String name, String parentName, String nickname) throws myException {
        try{
            /* Eger birden fazla baba veya anne varsa */
            if (nickname.contains(name)){
                int count=0;
                Iterator myIter = this.iterator();
                // Agacin iterator ile traverse edilmesi
                while (!(myIter.hasNext())){
                    if (parentName.contains((String)myIter.next())) {
                        count++;
                        if (count == 2) // Birden fazla baba veya anne varsa exception firlatilmasi
                            throw new myException(name,parentName);
                    }
                }
            }
            if (node == null){ // Verilen node ise agaca ekler
                isAdd = true;
                return new Node<E>((E)name);
            }
            else if(node.data.equals(parentName)) { // Verilen node anne veya baba ise agacin sol tarafina gecer
                isAdd = true;
                node.left = add(node.left, name, parentName, nickname);
                return node;
            }
            else if (node.left == null){ // Verilen node'un sol cocugu null ise agacin sag tarafina gecer
                isAdd = true;
                node.right = add(node.right,name,parentName,nickname);
                return node;
            }
            else if (node.right == null){ // Verilen node'un sag cocugu null ise agacin sol tarafina gecer
                isAdd = true;
                node.left = add(node.left,name,parentName,nickname);
                return node;
            }
            else if (node.right.data.equals(parentName)){ //Verilen node'un sag cocugu anne veya baba ise agacin sag tarafina gecer
                isAdd = true;
                node.right = add(node.right,name,parentName,nickname);
                return node;
            }
            // Eger nickname "ibn" iceriyorsa ve nickname verilen node'u iceriyorsa agacin sol tarafina gecer
            else if (nickname.contains("ibn") && nickname.contains((String)node.data)){
                isAdd = true;
                node.left = add(node.left,name,parentName,nickname);
                return node;
            }
            // Eger nickname verilen node'u iceriyorsa veya nickname eklenecek kisinin adini iceriyorsa agacin sag tarafina gecer
            else if (nickname.contains((String)node.data) || nickname.contains(name)){
                isAdd = true;
                node.right = add(node.right,name,parentName,nickname);
                return node;
            }
            // Eger nickname verilen node'un sag cocugunu iceriyorsa agacin sag tarafina gecer
            else if (nickname.contains((String)node.right.data)){
                isAdd = true;
                node.right = add(node.right,name,parentName,nickname);
                return node;
            }
            // Diger durumlarda agacin sag tarafina gecer
            else{
                isAdd = true;
                node.right = add(node.right,name,parentName,nickname);
                return node;
            }
        }
        catch(NullPointerException e){
            System.err.printf("Can not insertion!\nDaughter/Son Name:%s Father/Mother Name:%s\n",name,parentName);
            System.exit(1);
        }
        return node;
    }
}
