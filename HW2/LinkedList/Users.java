
import java.util.LinkedList;

/**
 * @author Devrim Akıncı
 */
public abstract class Users implements SystemInterface {
    //Data fields
    private String username;
    private String password;
    private String id;

    //Methods
    /**
     * Constructor
     * @param id Kullanici id
     * @param username Kullanici ismi
     * @param password Kullanici sifresi
     */
    public Users (String id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Abstract method
     * Kutuphaneye kitap ekler.
     * @param newBook Eklenecek kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */

    public abstract void addBook (Books newBook, LinkedList<Books> booksList, String filename);

    /**
     * Abstract method
     * Kutuphaneden kitap cikarir.
     * @param books Silinecek kitap
     * @param booksList Kitap listesi
     * @param filename Dosya ismi
     */

    public abstract void removeBook (Books books,LinkedList<Books>booksList,String filename);

    /**
     * Kutuphanede aranan kitabin olup olmadigini bulur.
     * @param bookID Kitap id
     * @param booksList Kitap listesi
     * @return Kitap kutuphanede varsa true yoksa false return eder.
     */

    public boolean isAvailable(String bookID,LinkedList<Books>booksList){
        for (int i=0; i<booksList.size(); i++){
            if (booksList.get(i).getBookID().compareTo(bookID) == 0)
                return true;
        }
        return false;
    }

    @Override
    /**
     * @return Kullanici adini alir
     */
    public String getUsername(){
        return username;
    }

    @Override
    /**
     * Kullanici adini set eder.
     * @param username Kullanici adi
     */
    public void setUsername(String username){
        this.username = username;
    }

    @Override
    /**
     * @return Kullanici sifresini alir.
     */
    public String getPassword(){
        return password;
    }

    @Override
    /**
     * Kullanici sifresini set eder.
     * @param password Kullanici sifresi
     */
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    /**
     * @return Kullanici id alir.
     */
    public String getID(){
        return id;
    }

    @Override
    /**
     * Kullanici id sini set eder.
     * @param id Kullanici id
     */
    public void setID(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return getID() + " " + getUsername() + " " + getPassword() + "\n";
    }
}