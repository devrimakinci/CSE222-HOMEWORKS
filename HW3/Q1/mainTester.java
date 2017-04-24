/**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.io.IOException;

public class mainTester {

    public static void main(String []args){
        myStringBuilder data = new myStringBuilder();
        String filename1 = "numbers.txt";
        String filename2 = "result1.txt";
        String filename3 = "result2.txt";
        String filename4 = "result3.txt";
        try {
            Scanner input = new Scanner(new File(filename1));
            FileWriter append1 = new FileWriter (filename2,true);
            FileWriter append2 = new FileWriter (filename3,true);
            FileWriter append3 = new FileWriter (filename4,true);
            Formatter output1 = new Formatter(append1);//Dosyanin append modda acilmasi
            Formatter output2 = new Formatter(append2);//Dosyanin append modda acilmasi
            Formatter output3 = new Formatter(append3);//Dosyanin append modda acilmasi
            while(input.hasNext()){
                data.getLinkedList().addFirst(input.nextInt());
            }
            //Dosyaya yazma islemleri
            output1.format("toString1 Method\n%s",data.toString1());
            output2.format("toString2 Method\n%s",data.toString2());
            output3.format("toString3 Method\n%s",data.toString3());
            output1.close();//Dosyanin kapatilmasi
            output2.close();//Dosyanin kapatilmasi
            output3.close();//Dosyanin kapatilmasi
            input.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error: Failed to open file!");
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
        }
    }
}
