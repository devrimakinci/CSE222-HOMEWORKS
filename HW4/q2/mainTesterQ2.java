package tr.edu.gtu.devrimakinci.q2; /**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class mainTesterQ2 {
    public static void main(String args[]) {
        String str="";
        try {
            //Dosyalarin acilmasi
            Scanner input = new Scanner(new File("test.csv"));
            FileWriter append = new FileWriter("testResult_2.csv", true);
            Formatter output = new Formatter(append);
            // Queue'larin olusturulmasi
            myQueue<Integer> q1 = new myQueue<>();
            myQueue<Double> q2 = new myQueue<>();
            myQueue<Character> q3 = new myQueue<>();
            myQueue<String> q4 = new myQueue<>();
            Queue<Integer> testQueue1 = new LinkedList<>();
            Queue<Double> testQueue2 = new LinkedList<>();
            Queue<Character> testQueue3 = new LinkedList<>();
            Queue<String> testQueue4 = new LinkedList<>();
            while (input.hasNextLine()) {
                str = input.nextLine(); // Satir satir okunmasi
                for (String str2 : str.split(",")) {
                    if (str2.contains(".")) { // Okunan deger Double ise
                        q2.offer(Double.parseDouble(str2));
                        testQueue2.offer(Double.parseDouble(str2));
                    }
                    else if ('0' <= str2.charAt(0) && str2.charAt(0) <= '9') { // Okunan deger integer ise
                        q1.offer(Integer.parseInt(str2));
                        testQueue1.offer(Integer.parseInt(str2));
                    }
                    else if (str2.length() == 1) { // Okunan deger karakter ise
                        q3.offer(str2.charAt(0));
                        testQueue3.offer(str2.charAt(0));
                    }
                    else { // Okunan deger string ise
                        q4.offer(str2);
                        testQueue4.offer(str2);
                    }
                }
            }
            // Reverse methodunun testi
            q1.reverse();
            q2.reverse();
            q3.reverse();
            q4.reverse();

            // ReverseQueue methodunun testi
            q1.reverseQueue(testQueue1);
            q2.reverseQueue(testQueue2);
            q3.reverseQueue(testQueue3);
            q4.reverseQueue(testQueue4);
            //Dosyaya yazma islemi
            while(!(q1.isEmpty())) {
                output.format("%d ",q1.poll());
            }
            output.format("\n");
            while(!(q2.isEmpty())) {
                output.format("%.1f ",q2.poll());
            }
            output.format("\n");
            while(!(q3.isEmpty())) {
                output.format("%c ",q3.poll());
            }
            output.format("\n");
            while(!(q4.isEmpty())) {
                output.format("%s ",q4.poll());
            }
            output.format("\n");
            while(!(testQueue1.isEmpty())) {
                output.format("%d ",testQueue1.poll());
            }
            output.format("\n");
            while(!(testQueue2.isEmpty())) {
                output.format("%.1f ",testQueue2.poll());
            }
            output.format("\n");
            while(!(testQueue3.isEmpty())) {
                output.format("%c ",testQueue3.poll());
            }
            output.format("\n");
            while(!(testQueue4.isEmpty())) {
                output.format("%s ",testQueue4.poll());
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
