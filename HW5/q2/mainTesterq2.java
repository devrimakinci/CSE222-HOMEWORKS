package tr.edu.gtu.devrimakinci.q2;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Devrim Akıncı
 */
public class mainTesterq2 {

    public static void main(String args[]){

        try{
            String []famData = new String[3];
            // Dosyanin acilmasi

            Scanner input = new Scanner(new File("familyTest.txt"));

            // Ilk satirin okunmasi
            String data = input.nextLine();

            // Agacin root node'unu olusturulmasi icin node'un olusturulmasi
            BinaryTree.Node<String> root = new BinaryTree.Node<>(data);

            //FamilyTree'nin olusturulmasi
            FamilyTree<String> famTree = new FamilyTree<>(root);
            while(input.hasNext()){ // Dosyanin okunmasi
                String familyData = input.nextLine(); // Satir satir okunmasi
                famData = familyData.split(", "); // ", " ile verilerin ayrilmasi
                famTree.add(famData[0],famData[1],famData[2]); // Verilerin agaca eklenmesi
            }

            // Recursive PreOrder Traverse Test
            System.out.println("Recursive PreOrder Traverse:");
            famTree.preOrderTraverse();

            //Iterator PreOrder Traverse Test
            System.out.println("Iterator PreOrder Traverse:");
            famTree.preOrderTraverseIterator();

        }
        catch(FileNotFoundException e){
            System.err.println("Error:File could not open");
        }

    }
}
