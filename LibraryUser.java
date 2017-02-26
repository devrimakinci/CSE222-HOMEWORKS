
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author Devrim Akıncı
 */
public class LibraryUser extends Users{
    //Data fields
    private ArrayList<Books>takenBooks;

    /**
     * Constructor
     * @param id Kullanici id
     * @param username Kullanici ismi
     * @param password Kullanici sifresi
     * @param takenBooks Odunc alinan kitap listesi
     */
    public LibraryUser(String id,String username,String password,ArrayList<Books>takenBooks){
        super(id,username,password);
        this.takenBooks = takenBooks;
    }
    /**
     * Constructor
     * @param id Kullanici id
     * @param username Kullanici ismi
     * @param password Kullanici sifresi
     */
    public LibraryUser(String id,String username,String password){
        super(id,username,password);
        takenBooks = new ArrayList<Books>();
    }

    /**
     *
     * @return Odunc alinan kitap listesini dondurur
     */
    public ArrayList<Books> getTakenBooks(){
        return takenBooks;
    }

    /**
     * Odunc alinan kitap listesini set eder.
     * @param takenBooks Odunc alinan kitap listesi
     */
    public void setTakenBooks(ArrayList<Books>takenBooks){
        this.takenBooks = takenBooks;
    }

    /**
     * Kutuphaneden kitabi odunc alir.
     * @param book Odunc alinacak kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */
    public void removeBook (Books book,ArrayList<Books>booksList,String filename){
        //Kitabin sistemde olup olmadiginin kontrolu
        if (isAvailable(book.getBookID(), booksList)){
            for (int i=0; i<booksList.size(); i++){
                if (booksList.get(i).getBookID() == book.getBookID())
                    booksList.remove(i);//Kitabin sistemden silinmesi
            }
            for (int i=0; i<booksList.size(); i++){
                try{
                    Formatter output = new Formatter(filename);
                    output.format("%s,%s,%s,%s,%s\n",booksList.get(i).getBookID(),
                            booksList.get(i).getAuthor(),booksList.get(i).getBookName(),
                            booksList.get(i).getTopic(),booksList.get(i).getPage());
                    output.close();
                }
                catch(FileNotFoundException fileNotFound){
                    System.err.println("Error:This file could not be opened!");
                }
            }
            getTakenBooks().add(book);//Odunc alinan kitabin listeye eklenmesi
        }
        else
            System.err.println("This book is not available in system!");
    }
    /**
     * Kutuphaneye kitabi iade eder.
     * @param returnBook Iade edilecek kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */
    public void addBook (Books returnBook,ArrayList<Books>booksList,String filename){
        try{
            FileWriter append = new FileWriter (filename,true);
            Formatter output = new Formatter(append);//Dosyanin append modda acilmasi
            output.format("%s,%s,%s,%s,%s\n",returnBook.getBookID(),returnBook.getAuthor(),
                    returnBook.getBookName(),returnBook.getTopic(),returnBook.getPage());
            output.close();//Dosyanin kapatilmasi
            booksList.add(returnBook);//Iade edilen kitabin listeye eklenmesi
        }
        catch(FileNotFoundException fileNotFound){
            System.err.println("Error:This file could not be opened!");
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
        }
        //Iade edilen kitabin odunc alinan kitap listesinden cikarilmasi
        getTakenBooks().remove(returnBook);
    }
}