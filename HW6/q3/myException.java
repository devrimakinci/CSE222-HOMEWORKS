package tr.edu.gtu.devrimakinci.q3;

/**
 * @author Devrim Akıncı
 */
public class myException extends Throwable {

    //Data Fields

    private String name;
    private String parentName;

    //Methods

    /**
     * Constructor
     * @param name Kiz/Ogul adi
     * @param parentName Baba/Anne adi
     */

    public myException(String name,String parentName){
        this.name = name;
        this.parentName = parentName;
    }

    /**
     * Bu method eger FamilyTree'ye eleman eklenemezse hata mesaji gosterir
     */

    public void errorMessage(){
        System.err.printf("Can not insertion\nDaughter/Son Name:%s\nFather/Mother Name:%s",name,parentName);
        System.exit(1);
    }
}
