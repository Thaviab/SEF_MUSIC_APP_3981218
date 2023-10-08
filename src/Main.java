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
                        Scanner details = new Scanner(System.in);
                        //getting user inputs for Artist
                        System.out.print("Enter Artist ID: ");
                        String id = details.nextLine();
                        System.out.print("Enter Artist Name: ");
                        String name = details.nextLine();
                        System.out.print("Enter Address (City|State|Country): ");
                        String address = details.nextLine();
                        System.out.print("Enter artist birthdate (DD-MM-YYYY): ");
                        String birthdate = details.nextLine();
                        System.out.print("Enter Artist Bio: ");
                        String bio = details.nextLine();
                        System.out.print("Enter Artist Occupations (Ex: Singer|Songwriter): ");
                        ArrayList<String> occupations = new ArrayList<>(Arrays.asList(details.nextLine()));
                        System.out.print("Enter Artist Genres (Ex: pop|classical): ");
                        ArrayList<String> genres = new ArrayList<>(Arrays.asList(details.nextLine()));
                        System.out.print("Enter Artist Awards (Format: Year|Title): ");
                        ArrayList<String> awards = new ArrayList<>(Arrays.asList(details.nextLine()));
                        Artist artist = new Artist(id,name,address,birthdate,bio,occupations,genres,awards);
                        boolean isAdded = artist.addArtist();
                        if(isAdded){
                            System.out.println("Artist Successfully Added");
                        }else {
                            System.out.println("Error. Try Again");
                        }
                        break;
                    case 2:
                        System.out.println("Update Artist");break;
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
        }while (num != 3);
    }
}
//Added a seperate exception handling for the menu
class InvalidMenuOption extends RuntimeException {
    public InvalidMenuOption(String message) {
        super(message);
    }
}
