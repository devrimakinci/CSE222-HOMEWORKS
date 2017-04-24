package tr.edu.gtu.devrimakinci.q3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Devrim Akıncı
 */
public class mainTesterq3 {

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

            //Iterator LevelOrder Traverse Test
            System.out.println("Iterator LevelOrder Traverse:");
            famTree.traverseLevelOrderIterator();

        }
        catch(FileNotFoundException e){
            System.err.println("Error:File could not open");
        }

    }
}
