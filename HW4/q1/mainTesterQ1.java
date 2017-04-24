package tr.edu.gtu.devrimakinci.q1; /**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class mainTesterQ1 {
    public static void main(String args[]){
        String str;
        try{
            //Dosyalarin acilmasi
            Scanner input = new Scanner(new File("test.csv"));
            FileWriter append = new FileWriter("testResult_1.csv",true);
            Formatter output = new Formatter(append);
            //Stack'lerin olusturulmasi
            StackA<Integer> myStackIntA = new StackA<>();
            StackB<Integer> myStackIntB = new StackB<>();
            StackC<Integer> myStackIntC = new StackC<>();
            StackD<Integer> myStackIntD = new StackD<>();
            StackA<Double> myStackDoubleA = new StackA<>();
            StackB<Double> myStackDoubleB = new StackB<>();
            StackC<Double> myStackDoubleC = new StackC<>();
            StackD<Double> myStackDoubleD = new StackD<>();
            StackA<Character> myStackCharA = new StackA<>();
            StackB<Character> myStackCharB = new StackB<>();
            StackC<Character> myStackCharC = new StackC<>();
            StackD<Character> myStackCharD = new StackD<>();
            StackA<String> myStackStrA = new StackA<>();
            StackB<String> myStackStrB = new StackB<>();
            StackC<String> myStackStrC = new StackC<>();
            StackD<String> myStackStrD = new StackD<>();
            while (input.hasNextLine()) {
                str = input.nextLine(); // Satir satir okunmasi
                for (String str2 : str.split(",")) {
                    if (str2.contains(".")){ // Okunan deger Double ise
                        myStackDoubleA.push(Double.parseDouble(str2));
                        myStackDoubleB.push(Double.parseDouble(str2));
                        myStackDoubleC.push(Double.parseDouble(str2));
                        myStackDoubleD.push(Double.parseDouble(str2));
                    }
                    else if ('0' <= str2.charAt(0) && str2.charAt(0) <= '9'){ // Okunan deger integer ise
                        myStackIntA.push(Integer.parseInt(str2));
                        myStackIntB.push(Integer.parseInt(str2));
                        myStackIntC.push(Integer.parseInt(str2));
                        myStackIntD.push(Integer.parseInt(str2));
                    }
                    else if(str2.length() == 1){ // Okunan deger karakter ise
                        myStackCharA.push(str2.charAt(0));
                        myStackCharB.push(str2.charAt(0));
                        myStackCharC.push(str2.charAt(0));
                        myStackCharD.push(str2.charAt(0));
                    }
                    else{ // Okunan deger string ise
                        myStackStrA.push(str2);
                        myStackStrB.push(str2);
                        myStackStrC.push(str2);
                        myStackStrD.push(str2);
                    }
                }
            }
            //Dosyaya yazma islemi
            output.format("%d",myStackIntA.size());
            while (!(myStackIntA.isEmpty())) {
                output.format(" %d",myStackIntA.pop());
            }
            output.format("\n");
            output.format("%d",myStackIntB.size());
            while (!(myStackIntB.isEmpty())) {
                output.format(" %d",myStackIntB.pop());
            }
            output.format("\n");
            output.format("%d",myStackIntC.size());
            while (!(myStackIntC.isEmpty())) {
                output.format(" %d",myStackIntC.pop());
            }
            output.format("\n");
            output.format("%d",myStackIntD.size());
            while (!(myStackIntD.isEmpty())) {
                output.format(" %d",myStackIntD.pop());
            }
            output.format("\n");
            output.format("%d",myStackDoubleA.size());
            while (!(myStackDoubleA.isEmpty())){
                output.format(" %.1f",myStackDoubleA.pop());
            }
            output.format("\n");
            output.format("%d",myStackDoubleB.size());
            while (!(myStackDoubleB.isEmpty())){
                output.format(" %.1f",myStackDoubleB.pop());
            }
            output.format("\n");
            output.format("%d",myStackDoubleC.size());
            while (!(myStackDoubleC.isEmpty())){
                output.format(" %.1f",myStackDoubleC.pop());
            }
            output.format("\n");
            output.format("%d",myStackDoubleD.size());
            while (!(myStackDoubleD.isEmpty())){
                output.format(" %.1f",myStackDoubleD.pop());
            }
            output.format("\n");
            output.format("%d",myStackCharA.size());
            while (!(myStackCharA.isEmpty())){
                output.format(" %c",myStackCharA.pop());
            }
            output.format("\n");
            output.format("%d",myStackCharB.size());
            while (!(myStackCharB.isEmpty())){
                output.format(" %c",myStackCharB.pop());
            }
            output.format("\n");
            output.format("%d",myStackCharC.size());
            while (!(myStackCharC.isEmpty())){
                output.format(" %c",myStackCharC.pop());
            }
            output.format("\n");
            output.format("%d",myStackCharD.size());
            while (!(myStackCharD.isEmpty())){
                output.format(" %c",myStackCharD.pop());
            }
            output.format("\n");
            output.format("%d",myStackStrA.size());
            while (!(myStackStrA.isEmpty())){
                output.format(" %s",myStackStrA.pop());
            }
            output.format("\n");
            output.format("%d",myStackStrB.size());
            while (!(myStackStrB.isEmpty())){
                output.format(" %s",myStackStrB.pop());
            }
            output.format("\n");
            output.format("%d",myStackStrC.size());
            while (!(myStackStrC.isEmpty())){
                output.format(" %s",myStackStrC.pop());
            }
            output.format("\n");
            output.format("%d",myStackStrD.size());
            while (!(myStackStrD.isEmpty())){
                output.format(" %s",myStackStrD.pop());
            }
            output.format("\n");
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
