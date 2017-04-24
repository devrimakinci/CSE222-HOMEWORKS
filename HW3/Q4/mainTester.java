/**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class mainTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();//SingleLinkedList olusturulmasi
        String filename = "input.txt"; // Verilerin okunacagi dosya ismi
        try{
            Scanner input = new Scanner(new File(filename)); // Dosyanin acilmasi
            while(input.hasNext()){
                list.addFirst(input.nextInt()); //Dosyadan verilerin okunup SingleLinkedList'e eklenmesi
            }
            System.out.printf("Size:%d\n",list.getSize());//LinkedList'in eleman sayisinin ekrana basilmasi
            System.out.println(list.toString()); //LinkedList'in butun elemanlarinin ekrana basilmasi
            for(int i=0; i<50; i++){
                list.removeFirst(); //SingleLinkedList'den 50 tane verinin silinmesi
            }
            System.out.printf("Size:%d\n",list.getSize());//LinkedList'in eleman sayisinin ekrana basilmasi
            System.out.println(list.deletedToString());//LinkedList'den silinen node'larin hepsinin ekrana basilmasi
            input.close(); // Dosyanin kapatilmasi
            Scanner scan = new Scanner(new File(filename)); // Dosyanin acilmasi
            while(scan.hasNext()){
                list.addFirst(scan.nextInt()); // Dosyadan verilerin okunup SingleLinkedList'e eklenmesi
            }
            System.out.printf("Size:%d\n",list.getSize());//SingleLinkedList'in eleman sayisinin ekrana basilmasi
            System.out.println(list.toString());//SingleLinkedList'in butun elemanlarinin ekrana basilmasi
            scan.close();// Dosyanin kapatilmasi
        }
        catch(FileNotFoundException e){
            System.err.println("Error: Failed to open file!");
        }
    }
}
