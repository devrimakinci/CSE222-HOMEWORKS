/**
 *
 * @author Devrim Akıncı
 */

import java.util.Formatter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class LibraryStaff extends Users {

    /**
     * Constructor
     * @param id Kullanici id
     * @param username Kullanici sifresi
     * @param password Kullanici sifresi
     */
    public LibraryStaff (String id,String username,String password){
        super(id,username,password);
    }
    /**
     * Kutuphaneye kitap ekler.
     * @param newBook Eklenecek kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */
    public void addBook (Books newBook, LinkedList<Books> booksList, String filename){
        try{
            FileWriter append = new FileWriter (filename,true);
            Formatter output = new Formatter(append);//Dosyanin append modda acilmasi
            output.format("%s,%s,%s,%s,%s\n",newBook.getBookID(),newBook.getAuthor(),
                    newBook.getBookName(),newBook.getTopic(),newBook.getPage());
            output.close();
            booksList.add(newBook);//Kitabin kitap listesine eklenmesi
        }
        catch(FileNotFoundException fileNotFound){
            System.err.println("Error:This file could not be opened!");
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
        }

    }
    /**
     * Kutuphaneden kitabi siler.
     * @param delBook Silinecek kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */
    public void removeBook (Books delBook,LinkedList<Books>booksList,String filename){
        //Kitabin sistemde olup olmadiginin kontrolu
        if (isAvailable(delBook.getBookID(), booksList)){
            for (int i=0; i<booksList.size(); i++){
                if (booksList.get(i).getBookID() == delBook.getBookID())
                    booksList.remove(i);//Kitabin kitap listesinden silinmesi
            }
            for (int i=0; i<booksList.size(); i++){
                try{
                    Formatter output = new Formatter(filename);
                    output.format("%s,%s,%s,%s,%s\n",booksList.get(i).getBookID(),
                            booksList.get(i).getAuthor(),booksList.get(i).getBookName(),
                            booksList.get(i).getTopic(),booksList.get(i).getPage());
                    output.close();//Dosyanin kapatilmasi
                }
                catch(FileNotFoundException fileNotFound){
                    System.err.println("Error:This file could not be opened!");
                }
            }
        }
        else
            System.err.println("This book is not available in system!");
    }
    /**
     * Yeni kullanici kayit eder.
     * @param newUser Kaydedilecek kullanici
     * @param userList Kullanici listesi
     * @param filename Dosya ismi
     */
    public void registerNewUser (LibraryUser newUser,LinkedList<Users>userList,String filename){
        try{
            FileWriter append = new FileWriter (filename,true);
            Formatter output = new Formatter(append);//Dosyanin append modda acilmasi
            output.format("%s,%s,%s\n",newUser.getID(),newUser.getUsername(),newUser.getPassword());
            output.close();//Dosyanin kapatilmasi
            userList.add(newUser);//Yeni kullanicinin kullanici listesine eklenmesi
        }
        catch(FileNotFoundException fileNotFound){
            System.err.println("Error:This file could not be opened!");
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
        }
    }
}