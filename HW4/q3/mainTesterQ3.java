package tr.edu.gtu.devrimakinci.q3; /**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class mainTesterQ3 {
    public static void main(String args[]) {
        String str="";
        try {
            //Dosyalarin acilmasi
            Scanner input = new Scanner(new File("test.csv"));
            FileWriter append = new FileWriter("testResult_3.csv", true);
            Formatter output = new Formatter(append);
            // Queue'larin olusturulmasi
            PriorityQueueA<Integer> pQueueAInt = new PriorityQueueA<>();
            PriorityQueueA<Double> pQueueADouble = new PriorityQueueA<>();
            PriorityQueueA<Character> pQueueAChar = new PriorityQueueA<>();
            PriorityQueueA<String> pQueueAStr = new PriorityQueueA<>();
            PriorityQueueB<Integer> pQueueBInt = new PriorityQueueB<>();
            PriorityQueueB<Double> pQueueBDouble = new PriorityQueueB<>();
            PriorityQueueB<Character> pQueueBChar = new PriorityQueueB<>();
            PriorityQueueB<String> pQueueBStr = new PriorityQueueB<>();
            while (input.hasNextLine()) {
                str = input.nextLine(); // Satir satir okunmasi
                for (String str2 : str.split(",")) {
                    if (str2.contains(".")) { // Okunan deger Double ise
                        pQueueADouble.add(Double.parseDouble(str2));
                        pQueueBDouble.add(Double.parseDouble(str2));
                    }
                    else if ('0' <= str2.charAt(0) && str2.charAt(0) <= '9') { // Okunan deger integer ise
                        pQueueAInt.add(Integer.parseInt(str2));
                        pQueueBInt.add(Integer.parseInt(str2));
                    }
                    else if (str2.length() == 1) { // Okunan deger karakter ise
                        pQueueAChar.add(str2.charAt(0));
                        pQueueBChar.add(str2.charAt(0));
                    }
                    else { // Okunan deger string ise
                        pQueueAStr.add(str2);
                        pQueueBStr.add(str2);
                    }
                }
            }

            //Deletedmin Methodunun Testi
            pQueueAInt.deletedMin();
            pQueueADouble.deletedMin();
            pQueueAChar.deletedMin();
            pQueueAStr.deletedMin();

            pQueueBInt.deletedMin();
            pQueueBDouble.deletedMin();
            pQueueBChar.deletedMin();
            pQueueBStr.deletedMin();

            //Dosyaya yazma islemi
            while (!(pQueueAInt.isEmpty())){
                output.format("%d ",pQueueAInt.poll());
            }
            output.format("\n");
            while (!(pQueueADouble.isEmpty())){
                output.format("%.1f ",pQueueADouble.poll());
            }
            output.format("\n");
            while (!(pQueueAChar.isEmpty())){
                output.format("%c ",pQueueAChar.poll());
            }
            output.format("\n");
            while (!(pQueueAStr.isEmpty())){
                output.format("%s ",pQueueAStr.poll());
            }
            output.format("\n");
            while (!(pQueueBInt.isEmpty())){
                output.format("%d ",pQueueBInt.poll());
            }
            output.format("\n");
            while (!(pQueueBDouble.isEmpty())){
                output.format("%.1f ",pQueueBDouble.poll());
            }
            output.format("\n");
            while (!(pQueueBChar.isEmpty())){
                output.format("%c ",pQueueBChar.poll());
            }
            output.format("\n");
            while (!(pQueueBStr.isEmpty())) {
                output.format("%s ", pQueueBStr.poll());
            }
            output.close(); // Dosyanin kapatilmasi
        }
        catch(FileNotFoundException e){
            System.err.println("Failed to open file!\n");
            System.exit(1);
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
            System.exit(1);
        }
    }
}