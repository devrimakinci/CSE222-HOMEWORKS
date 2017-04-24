package tr.edu.gtu.devrimakinci.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Devrim Akıncı
 */
public class mainTesterq2 {

    public static void main(String args[]) {

        try {

            String line;
            String [] data;

            HuffmanTree huffTree = new HuffmanTree();

            //Dosyanin acilmasi

            Scanner scan = new Scanner(new File("test.txt"));

            ArrayList<HuffmanTree.HuffData> huffArray = new ArrayList<>();

            //Dosyanin okunmasi

            while (scan.hasNext()) {
                line = scan.nextLine(); // Satir satir okunmasi
                if(line.charAt(0) == ' '){
                    data = line.split(" "); // Bosluklarin ayrilmasi
                    HuffmanTree.HuffData huffData = new HuffmanTree.HuffData(Double.parseDouble(data[2]),line.charAt(0));
                    huffArray.add(huffData);
                }
                else {
                    data = line.split(" ");
                    HuffmanTree.HuffData huffData = new HuffmanTree.HuffData(Double.parseDouble(data[1]), data[0].charAt(0));
                    huffArray.add(huffData);
                }
            }

            scan.close(); // Dosyanin kapatilmasi

            huffTree.buildTree(huffArray); // Huffman Tree'nin olusturulmasi

            System.out.println("CODE");

            huffTree.printCoded();

            // Encode Testi

            System.out.println("ENCODE('acaba'):" + huffTree.encode("acaba"));

            System.out.println("ENCODE('efe'):" + huffTree.encode("efe"));

            System.out.println("ENCODE('feda'):" + huffTree.encode("feda"));

        } catch (FileNotFoundException e) {
            System.err.println("Error: Failed to open file!");
        }
    }
}
