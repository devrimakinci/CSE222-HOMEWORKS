package tr.edu.gtu.devrimakinci.q1;

import java.io.*;
import java.util.Scanner;

/**
 * @author Devrim Akıncı
 */
public class mainTesterq1 {

    public static void main(String args[]){
        try{

            //Dosyanin acilmasi

            Scanner input = new Scanner(new File("test.txt"));

            //Agaclarin olusturulmasi

            PreOrderTraverse<Integer> treeInteger = new PreOrderTraverse<>();
            BinarySearchTree<Integer> bstInt = new BinarySearchTree<>();

            //Dosyanin okunmasi

            while (input.hasNextInt()){
                int data = input.nextInt();
                treeInteger.add(data); // Verinin agaca eklenmesi
                bstInt.add(data); // Verinin agaca eklenmesi
            }

            //PreOrder Traverse Testi

            System.out.printf("Pre Order Traverse: ");
            treeInteger.preOrderTraverse();
            System.out.println();

            //LevelOrder Traverse Testi
            System.out.printf("Level Order Traverse: ");
            bstInt.traverseLevelOrderIterator();

            //Dosyanin kapatilmasi
            input.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error: Failed to open file!");
        }
    }
}
