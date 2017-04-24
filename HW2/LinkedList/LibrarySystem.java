
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Devrim Akıncı
 */

public class LibrarySystem {
    //Data fields
    private LinkedList <Users> usersList;
    private LinkedList<Books> booksList;

    //Methods
    /**
     * No parameter constructor
     */
    public LibrarySystem (){
        usersList = new LinkedList<Users>();
        booksList = new LinkedList<Books>();
    }
    /**
     * Constructor
     * @param userList Kullanici listesi
     * @param bookList Kitap listesi
     */
    public LibrarySystem (LinkedList <Users> userList, LinkedList <Books> bookList){
        usersList = userList;
        booksList = bookList;
    }
    /**
     *
     * @return Kullanici listesini alir
     */

    public LinkedList <Users> getUserList(){
        return usersList;
    }
    /**
     *
     * @return Kitap listesini alir
     */
    public LinkedList <Books> getBookList(){
        return booksList;
    }

    /**
     * Sisteme kullanici ekler.
     * @param newUser Eklenecek kullanici
     * @param filename Dosya ismi
     */
    public void addUsers(Users newUser, String filename){
        try{
            FileWriter append = new FileWriter (filename,true);
            Formatter output = new Formatter(append);//Dosyanin append modda acilmasi
            //Dosyaya yazma islemi
            output.format("%s,%s,%s\n",newUser.getID(),newUser.getUsername(),newUser.getPassword());
            output.close();//Dosyanin kapatilmasi
            getUserList().add(newUser);//Kullanicinin kullanici listesine eklenmesi
        }
        catch(FileNotFoundException fileNotFound){
            System.err.println("Error:This file could not be opened!");
        }
        catch(IOException ioe){
            System.err.println("Exception->" + ioe.getMessage());
        }
    }
    /**
     * Sistemdeki kitaplari ekrana basar.
     * @param books Kitap listesi
     */
    public void printBooks(LinkedList<Books>books){
        if (books.size() == 0)
            System.out.println("There are no books in the library system!");
        else{
            System.out.println("Printing books list...\n");
            System.out.println("BookID   Author   Book Name   Topic   Page");
            for(int i=0; i<books.size(); i++){
                System.out.println(i+1 + "-" + books.get(i).toString());
            }
        }
    }
    /**
     * Sistemdeki kullanicilari ekrana basar.
     * @param users Kullanici listesi
     */
    public void printUsers(LinkedList<Users>users){
        System.out.println("Printing users list...\n");
        System.out.println("UserID   Username   Password");
        for(int i=0; i<users.size(); i++){
            System.out.println(i+1 + "-" + users.get(i).toString());
        }
    }
    /**
     * Sistemden odunc alinan kitaplarin ekrana basilmasi
     * @param user Kitabi odunc alan kullanici
     */
    public void printTakenBooks(LibraryUser user){
        if (user.getTakenBooks().size() == 0)
            System.out.println(user.getUsername() + " has not taken books!");
        else{
            System.out.println("Username->" + user.getUsername() + "\nTotal number of taken books->" +
                    user.getTakenBooks().size());
            System.out.println("Printing taken books list...");
            System.out.println("BookID   Author   Book Name   Topic   Page");
            for (int i=0; i<user.getTakenBooks().size(); i++){
                System.out.println(i+1 + "-" + user.getTakenBooks().get(i).toString());
            }
        }
    }
    /**
     * Verilen kitap id'si ile sistemdeki o id'ye ait olan kitabi arar.
     * @param bookID Kitap id
     * @return Kitap objesi dondurur.
     */
    public Books searchBook (String bookID){
        for (int i=0; i<getBookList().size(); i++){
            if (getBookList().get(i).getBookID().compareTo(bookID) == 0)
                return getBookList().get(i);
        }
        return new Books();
    }
    /**
     *
     * @param bookID Kitap id
     * @param user Kitabi odunc alan kullanici
     * @return Kitap objesi dondurur.
     */
    public Books searchTakenBooks (String bookID,LibraryUser user){
        for (int i=0; i<user.getTakenBooks().size(); i++){
            if (user.getTakenBooks().get(i).getBookID().compareTo(bookID) == 0)
                return user.getTakenBooks().get(i);
        }
        return new Books();
    }
    /**
     * Kullanici adi ile sifrenin uyusup uyusmadigi kontrol edilir.
     * @param checkUser Kontrol edilecek kullanici
     * @param username Kullanici adi
     * @param password Kullanici sifresi
     * @return Kullanici adi ile sifre uyusursa true, aksi takdirde false dondurur
     */
    public boolean checkingPassword(Users checkUser,String username,String password){
        if ((checkUser.getUsername().compareTo(username)==0) && (checkUser.getPassword().compareTo(password)==0))
            return true;
        return false;
    }
    /**
     * Sistemi hangi kullanicinin kullandigini belirler.
     * @param username Kullanici adi
     * @param password Kullanici sifresi
     * @return Sistemi kullanan kullanicinin kullanici listesindeki indexini dondurur.
     */
    public int identifyUser (String username, String password){
        int index=-1;
        for (int i=0; i<getUserList().size(); i++){
            if ((getUserList().get(i).getUsername().compareTo(username)==0)&&
                    (getUserList().get(i).getPassword().compareTo(password)==0))
                index = i;
        }
        return index;
    }
}