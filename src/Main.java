import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Artist.loadArtistFromFile();
        int num = 0;
        System.out.println("SEF Music App");
        do{
            System.out.println("\n--------------------------------------");
            System.out.println(">Select from main menu");
            System.out.println("--------------------------------------");
            System.out.printf("%10s\n","1) Add an Artist");
            System.out.printf("%10s\n","2) Update an Artist");
            System.out.printf("%10s\n","3) Display all Artists");
            System.out.printf("%5s\n","4) Exit");
            System.out.print("Please select: ");

            try{
                String userInput = new Scanner(System.in).nextLine();
                num = Integer.parseInt(userInput);
                switch (num){
                    case 1:
                        boolean isAdded = Artist.addArtist();
                        if(isAdded){
                            System.out.println("Artist Successfully Added");
                        }else {
                            System.out.println("Error. Try Again");
                        }
                        break;
                    case 2:
                        if(Artist.updateArtist()){
                            System.out.println("Artist updated sucessfully");
                        }else {
                            System.out.println("Artist update failed");
                        }break;
                    case 3:
                        Artist.displayAllArtists();break;
                    case 4: System.out.println("Thanks for using Social Media Analyzer");break;
                    default:
                        throw new InvalidMenuOption("Invalid selection. Please select a valid option!");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input "+e.getMessage());
                num = 0;
            }catch (InvalidMenuOption e){
                System.out.println(e.getMessage());
                num = 0;
            }
        }while (num != 4);
    }
}
//Added a seperate exception handling for the menu
class InvalidMenuOption extends RuntimeException {
    public InvalidMenuOption(String message) {
        super(message);
    }
}
