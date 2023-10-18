import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputDetails = new Scanner(System.in);
        //loading saved artist to the array list
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

                num = Integer.parseInt(inputDetails.nextLine());
                switch (num){
                    case 1:
                        //getting user inputs for artist
                        System.out.print("Enter artist ID: ");
                        String id = inputDetails.nextLine();

                        System.out.print("Enter artist name: ");
                        String name = inputDetails.nextLine();

                        System.out.print("Enter artist address(City|State|Country) : ");
                        String address = inputDetails.nextLine();

                        System.out.print("Enter artist birthdate(DD-MM-YYYY) : ");
                        String birthdate = inputDetails.nextLine();

                        System.out.print("Enter artist bio: ");
                        String bio = inputDetails.nextLine();

                        System.out.print("Enter artist occupations(Singer|Songwriter) : ");
                        String occupations = inputDetails.nextLine();
                        ArrayList<String> occupationsList = new ArrayList<>(Arrays.asList(occupations.split("\\|")));

                        System.out.print("Enter artist genres(pop|classical) : ");
                        String genres = inputDetails.nextLine();
                        ArrayList<String> genresList = new ArrayList<>(Arrays.asList(genres.split("\\|")));

                        System.out.print("Enter artist awards(Year|Title) : ");
                        String awards = inputDetails.nextLine();
                        ArrayList<String> awardsList = new ArrayList<>(Arrays.asList(awards.split(",")));

                        Artist newArtist = new Artist(id,name,address,birthdate,bio,occupationsList,genresList,awardsList);

                        boolean isAdded = newArtist.addArtist(newArtist);
                        if(isAdded){
                            System.out.println("Artist Successfully Added");
                        }else {
                            System.out.println("Error. Try Again");
                        }
                        break;
                    case 2:
                        System.out.print("Enter artist id to update: ");
                        String searchId = inputDetails.nextLine();
                        //checking artist
                        Artist searchArtist = Artist.searchForArtist(searchId);
                        if(searchArtist == null) {
                            System.out.println("Artist not found!");
                            break;
                        }
                        System.out.print("Enter new artist ID: ");
                        String newID = inputDetails.nextLine();

                        System.out.println(searchArtist.getName());
                        System.out.print("Enter artist's new name: ");
                        String newName = inputDetails.nextLine();

                        System.out.println(searchArtist.getAddress());
                        System.out.print("Enter artist's new address(city|state|country): ");
                        String newAddress = inputDetails.nextLine();

                        System.out.println(searchArtist.getBirthdate());
                        System.out.print("Enter artist's new birthdate(dd-MM-yyyy): ");
                        String newBirthdate = inputDetails.nextLine();

                        System.out.println(searchArtist.getBio());
                        System.out.print("Enter artist's new bio: ");
                        String newBio = inputDetails.nextLine();

                        System.out.println(searchArtist.getOccupations());
                        System.out.print("Enter artist's new occupations(occupation1|occupation2): ");
                        String strNewOccupations = inputDetails.nextLine();
                        ArrayList<String> newOccupations = new ArrayList<>(Arrays.asList(strNewOccupations.split("\\|")));

                        System.out.println(searchArtist.getGenres());
                        System.out.print("Enter artist's new genres(genre1|genre2): ");
                        String strNewGenres = inputDetails.nextLine();
                        ArrayList<String> newGenres = new ArrayList<>(Arrays.asList(strNewGenres.split("\\|")));

                        System.out.println(searchArtist.getAwards());
                        System.out.print("Enter artist's new award titles(title1,title2): ");
                        String strNewAwards = inputDetails.nextLine();
                        ArrayList<String> newAwards = new ArrayList<>(Arrays.asList(strNewAwards.split(",")));

                        Artist updateArtist = new Artist(newID,newName, newAddress, newBirthdate,newBio,newOccupations,newGenres,newAwards);

                        if(updateArtist.updateArtist(searchId,newID,newName,newAddress,newBirthdate,newBio,newOccupations,newGenres,newAwards)){
                            System.out.println("Artist updated sucessfully");
                        }else {
                            System.out.println("Artist update failed");
                        }
                        break;
                    case 3:
                        Artist.displayAllArtists();
                        break;
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
            } catch (ParseException e) {
                throw new RuntimeException(e);
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
