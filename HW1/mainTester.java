/**
 *
 * @author Devrim Akıncı
 */

import java.util.Scanner;

public class mainTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Kutuphane sisteminin olusturulmasi
        LibrarySystem libSystem = new LibrarySystem();

        //Kutuphane personelinin olusturulmasi
        LibraryStaff staff = new LibraryStaff("11111", "admin", "admin123");

        //Sisteme personelin eklenmesi
        libSystem.addUsers(staff, args[1]);

        do{
            System.out.println("Welcome to Library Management System");
            libSystem.printBooks(libSystem.getBookList());
            libSystem.printUsers(libSystem.getUserList());
            System.out.println("Press q to quit");
            System.out.println("What will you log in to system?");
            System.out.println("1-Library Staff\n" + "2-Library User");
            int choose = input.nextInt();
            if (choose == 1){
                System.out.printf("username:");
                String un = input.next();
                System.out.printf("password:");
                String pw = input.next();
                if (libSystem.checkingPassword(staff, un, pw)){
                    System.out.println("What want do you do?");
                    System.out.println("1-Add Book\n" + "2-Remove Book\n" +"3-Register New User");
                    int userChoice = input.nextInt();
                    if (userChoice == 1){//Personelin kitabi sisteme eklemesi
                        System.out.printf("BookID:");
                        String bookID = input.next();
                        System.out.printf("Author:");
                        String author = input.next();
                        System.out.printf("Book Name:");
                        String bookName = input.next();
                        System.out.printf("Topic:");
                        String topic = input.next();
                        System.out.printf("Page:");
                        String page = input.next();
                        Books book = new Books(bookID,author,bookName,topic,page);
                        staff.addBook(book,libSystem.getBookList(),args[0]);
                    }
                    else if (userChoice == 2){//Personelin kitabi sistemden silmesi
                        Books removingBook = new Books();
                        System.out.printf("Enter removing BookID:");
                        String bookID = input.next();
                        removingBook = libSystem.searchBook(bookID);
                        staff.removeBook(removingBook,libSystem.getBookList(),args[0]);
                    }
                    else if (userChoice == 3){//Personelin sisteme yeni bir kullanici kayit etmesi
                        System.out.printf("userID:");
                        String userID = input.next();
                        System.out.printf("Username:");
                        String username = input.next();
                        System.out.printf("Password:");
                        String password = input.next();
                        LibraryUser newUser = new LibraryUser(userID, username, password);
                        staff.registerNewUser(newUser,libSystem.getUserList(),args[1]);
                    }
                }
                else{//Yanlis sifre veya kullanici adi girildigi durumda
                    System.out.println("Wrong username or password");
                    System.out.println("Program will exit!");
                    System.exit(1);
                }
            }
            else if (choose == 2){
                System.out.printf("username:");
                String un = input.next();
                System.out.printf("password:");
                String pw = input.next();
                int index = libSystem.identifyUser(un, pw);
                if (libSystem.checkingPassword(libSystem.getUserList().get(index), un, pw)){
                    System.out.println("What want do you do?");
                    System.out.println("1-Return Book\n" + "2-Borrow Book");
                    int userChoice = input.nextInt();
                    if (userChoice == 1){//Kullanicinin kitabi iade etmesi
                        Books returningBook = new Books();
                        System.out.printf("Enter returning BookID:");
                        String bookID = input.next();
                        returningBook = libSystem.searchTakenBooks(bookID,(LibraryUser)libSystem.getUserList().get(index));
                        libSystem.getUserList().get(index).addBook(returningBook, libSystem.getBookList(), args[0]);
                        libSystem.printTakenBooks((LibraryUser) libSystem.getUserList().get(index));
                    }
                    else if (userChoice == 2){//Kullanicinin kitabi sistemden odunc almasi
                        Books borrowingBook = new Books();
                        System.out.printf("Enter borrowing BookID:");
                        String bookID = input.next();
                        borrowingBook = libSystem.searchBook(bookID);
                        libSystem.getUserList().get(index).removeBook(borrowingBook, libSystem.getBookList(), args[0]);
                        libSystem.printTakenBooks((LibraryUser) libSystem.getUserList().get(index));
                    }
                }
                else{//Yanlis sifre veya kullanici adi girildigi durumda
                    System.out.println("Wrong username or password");
                    System.out.println("Program will exit!");
                    System.exit(1);
                }
            }
            System.out.println("Press any key to continue");
            System.out.println("Press q to quit");
        }while(input.next().charAt(0) != 'q');
        libSystem.printBooks(libSystem.getBookList());
        libSystem.printUsers(libSystem.getUserList());
    }
}