/**
 * @author Devrim Akıncı
 */

import java.util.Iterator;

public class mainTester {
    public static void main(String args[]){

        //AVL Tree olusturulmasi
        AVLTree<String> avlTree = new AVLTree<>();

        //AVL Tree Build
        avlTree.add("nush");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("ile");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("uslanmayani");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("etmeli");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("tekdir");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("uslanmayanin");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("hakki");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("kotektir");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("edille");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("dakik");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.add("ferc");
        System.out.println(avlTree.toString());
        System.out.println("***************************");

        //Delete first three words from tree
        avlTree.delete("hakki");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.delete("ferc");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
        avlTree.delete("etmeli");
        System.out.println(avlTree.toString());
        System.out.println("***************************");
    }
}
