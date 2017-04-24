/**
 *
 * @author Devrim Akıncı
 */
public interface SystemInterface {

    /**
     * @return Kullanici adini alir.
     */
    public String getUsername();

    /**
     * @return Kullanici sifresini alir.
     */
    public String getPassword();

    /**
     * @return Kullanici id sini alir.
     */
    public String getID();

    /**
     * @param id Kullanici id
     */

    public void setID(String id);

    /**
     * @param username Kullanici adi
     */
    public void setUsername(String username);

    /**
     * @param password Kullanici sifresi
     */
    public void setPassword(String password);

}