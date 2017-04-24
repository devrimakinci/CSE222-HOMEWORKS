/**
 *
 * @author Devrim Akıncı
 */
public class Books {
    //Data fields
    private String bookID;
    private String author;
    private String bookName;
    private String topic;
    private String page;

    //Methods
    /**
     * Constructor
     * @param bookID Kitap id
     * @param author Kitabin yazari
     * @param bookName Kitabin ismi
     * @param topic Kitabin turu
     * @param page Kitabin sayfa sayisi
     */
    public Books (String bookID,String author,String bookName,String topic,String page){
        this.bookID = bookID;
        this.author = author;
        this.bookName = bookName;
        this.topic = topic;
        this.page = page;
    }
    /**
     * No parameter constructor
     */
    public Books(){
        this.bookID = "";
        this.author = "";
        this.bookName = "";
        this.topic = "";
        this.page = "";
    }
    /**
     *
     * @return Kitabin id sini alir.
     */
    public String getBookID(){
        return bookID;
    }
    /**
     *
     * @return Kitabin yazarini alir.
     */
    public String getAuthor(){
        return author;
    }
    /**
     *
     * @return Kitabin ismini alir.
     */
    public String getBookName(){
        return bookName;
    }
    /**
     *
     * @return Kitabin turunu alir.
     */
    public String getTopic(){
        return topic;
    }
    /**
     *
     * @return Kitabin sayfa sayisini alir.
     */
    public String getPage(){
        return page;
    }
    /**
     *
     * @param bookID Kitabin id
     */
    public void setBookID(String bookID){
        this.bookID = bookID;
    }
    /**
     *
     * @param author Kitabin yazari
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     *
     * @param bookName Kitabin ismi
     */
    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    /**
     *
     * @param topic Kitabi turu
     */
    public void setTopic(String topic){
        this.topic = topic;
    }
    /**
     *
     * @param page Kitabin sayfa sayisi
     */
    public void setPage(String page){
        this.page = page;
    }
    @Override
    public String toString(){
        return getBookID() + " " + getAuthor() + " " + getBookName() + " "
                + " " + getTopic() + " " + getPage() + "\n";
    }
}