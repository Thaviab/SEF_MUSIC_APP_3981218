import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//***********************************************************************************************//
//before running the code set the path for SEF_Music.txt
//***********************************************************************************************//
public class Artist {
    private String id;
    private String name;
    private String address;
    private String birthdate;
    private String bio;
    private ArrayList<String> Occupations;
    private ArrayList<String> Genres;
    private ArrayList<String> Awards;
    public static ArrayList<Artist> artistsList = new ArrayList<>();
    public static String pathInitialized = "D:\\RMIT\\Sem 2\\SEF\\Assignment\\4\\SEF_MUSIC_APP\\resources\\SEF_Music.txt";

    public Artist(){}
    public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations, ArrayList<String> genres,
                  ArrayList<String> awards) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.bio = bio;
        this.Occupations = occupations;
        this.Genres = genres;
        this.Awards = awards;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<String> getOccupations() {
        return Occupations;
    }

    public void setOccupations(ArrayList<String> occupations) {
        Occupations = occupations;
    }

    public ArrayList<String> getGenres() {
        return Genres;
    }

    public void setGenres(ArrayList<String> genres) {
        Genres = genres;
    }

    public ArrayList<String> getAwards() {
        return Awards;
    }

    public void setAwards(ArrayList<String> awards) {
        Awards = awards;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", bio='" + bio + '\'' +
                ", Occupations=" + Occupations +
                ", Genres=" + Genres +
                ", Awards=" + Awards +
                '}';
    }


    public boolean addArtist(Artist artist){
        boolean isChecked;

        //checking id
        isChecked = Artist.checkArtistID(artist.getId());
        if(!isChecked)return false;

        //checking address
        isChecked = Artist.checkAddress(artist.getAddress());
        if(!isChecked)return false;

        //checking birthdate
        isChecked = Artist.checkBirthDate(artist.getBirthdate());
        if(!isChecked)return false;

        //checking bio
        isChecked = Artist.checkBio(artist.getBio());
        if(!isChecked) return false;

        //checking occupations
        isChecked = Artist.checkOccupations(artist.getOccupations());
        if(!isChecked)return false;

        //checking genres
        isChecked = Artist.checkGenres(artist.getGenres());
        if(!isChecked)return false;

        //checking awards
        isChecked = Artist.checkAwards(artist.getAwards());
        if (!isChecked)return false;

        //adding artist to the arrayList
        artistsList.add(artist);

        //calling writeFile method to copy the artist into text file
        Artist.writeFile();

        return true;
    }
    public static boolean checkArtistID(String id) {
        boolean idLength = id.length() == 10; //checks its 10 characters
        boolean pattern = id.matches("^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{2}$");
        //^[5-9]{3} = check first 3 characters between 5-9
        //[A-Z]{3} - checks next 5 characters
        //then checks the last 2 characters for special chcaracter
        return idLength && pattern;
    }

    public static boolean checkBirthDate(String birthdate) {
        //checking the date format
        return birthdate.matches("^\\d{2}-\\d{2}-\\d{4}$");
    }

    public static boolean checkAddress(String address) {
        //checking the length of address
        String[] checkAddress = address.split("\\|");
        return checkAddress.length == 3;
    }

    public static boolean checkBio(String bio) {
        //checking number of words in Bio
        String[] words = bio.split("\\s+");
        return words.length >= 10 && words.length <= 30;
    }

    public static boolean checkOccupations(ArrayList<String> occupations) {
        //checking number of occupations
        if (occupations == null || occupations.isEmpty()) {
            return false;
        } else if (occupations.size() < 1 || occupations.size() > 5) {
            return false;
        }
        return true;
    }
    public static boolean checkGenres(ArrayList<String> genres){
        if(genres==null || genres.isEmpty()){
            return false;
        } else if (genres.size() < 2 || genres.size() > 5){
            return false;
            //checking whether pop and rock is in the occupations
        }else if (genres.contains("pop") && genres.contains("rock")){
            return false;
        }
        return true;
    }

    public static boolean checkAwards(ArrayList<String> awards){
        if(awards == null || awards.isEmpty()){
            return true;
        }
        for (String oneAward : awards){
            String[] awardParts = oneAward.split("\\|");
            if(awardParts.length != 2){
                return false;
            }
            String title = awardParts[1];
            //checking number of words in title
            String[] titleWords = title.trim().split("\\s+");
            if(titleWords.length < 4 || titleWords.length > 10){
                return false;
            }
        }
        return true;
    }
    public static void writeFile(){
        String path = Artist.pathInitialized;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Artist artist : artistsList) {
                writer.write(artist.getId() + ", " +
                        artist.getName() + ", " +
                        artist.getAddress() + ", " +
                        artist.getBirthdate() + ", " +
                        artist.getBio() + ", " +
                        String.join("|", artist.getOccupations()) + ", " +
                        String.join("|", artist.getGenres()) + ", " +
                        String.join(",", artist.getAwards()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Artist adding unsucessfull");
        }
    }

    public static void loadArtistFromFile(){
        artistsList.clear();
        String path = Artist.pathInitialized;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String record;
            while ((record = reader.readLine()) != null) {
                String[] recordParts = record.split(", ", 8);
                String id = recordParts[0];
                String name = recordParts[1];
                String address = recordParts[2];
                String birthdate = recordParts[3];
                String bio = recordParts[4];
                ArrayList<String> occupations = new ArrayList<>(Arrays.asList(recordParts[5].split("\\|")));
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(recordParts[6].split("\\|")));
                ArrayList<String> awards = new ArrayList<>(Arrays.asList(recordParts[7].split(",")));
                Artist artist = new Artist(id, name, address, birthdate, bio, occupations, genres, awards);
                artistsList.add(artist);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayAllArtists() {
        if (Artist.artistsList.isEmpty()) {
            System.out.println("No Artists available");
            return;
        }
        for (Artist artist : Artist.artistsList) {
            System.out.println("------------------------------");
            System.out.println("ID: " + artist.getId());
            System.out.println("Name: " + artist.getName());
            System.out.println("Address: " + artist.getAddress());
            System.out.println("Birthdate: " + artist.getBirthdate());
            System.out.println("Bio: " + artist.getBio());
            System.out.println("Occupations: " + artist.getOccupations());
            System.out.println("Genres: " + artist.getGenres());
            System.out.println("Awards: " + artist.getAwards());
            System.out.println("------------------------------\n");
        }
    }
    public static Artist searchForArtist(String id){
        for (Artist artist : artistsList){
            if(artist.getId().equals(id)){
                return artist;
            }
        }
        return null;
    }

    public static boolean updateArtistOccupations(Artist artist, ArrayList<String> newOccupations) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date birthdate = format.parse(artist.getBirthdate());
        Date year = format.parse("01-01-2000");
        if(!birthdate.before(year)){
            return true;
        }
        return false;
    }

    public static ArrayList<String> updateArtistAwards(Artist artist, ArrayList<String> newAwards){
        ArrayList<String> updatedAwardsArray = new ArrayList<>();
        //getting current awards
        for(String award : artist.getAwards()){
            //splitting the award title and year
            String[] parts = award.split(",");
            for (int i=0; i<parts.length; i++){
                String[] awardData = parts[i].split("\\|");
                int year = Integer.parseInt(awardData[0].trim());
                //check the year
                if(year>2000){
                    awardData[1] = newAwards.get(i);
                    parts[i] = awardData[0] + "|" + awardData[1];
                }
            }
            updatedAwardsArray.add(String.join(",",parts));
        }
        return updatedAwardsArray;
    }

    //**ASSUMPTION - Assumming award titles are entered according to the number of current awards count
    public boolean updateArtist(String searchId, String newId, String newName, String newAddress, String newBirthdate, String newBio,
                                ArrayList<String> newOccupations, ArrayList<String> newGenres, ArrayList<String> newAwardTitles) throws ParseException {
        //searching the artist
        Artist searchArtist = searchForArtist(searchId);
        if(searchArtist == null){
            System.out.println("Artist with ID" + searchId + "not in the system");
            return false;
        }
        System.out.println("Updating Artist with ID: " + searchId);

        boolean isUpdateID = checkArtistID(newId);
        //
        System.out.println("ID " + isUpdateID);

        boolean isUpdateAddress = checkAddress(newAddress);
        //
        System.out.println("Address "+ isUpdateAddress);

        boolean isUpdatedBirthdate = checkBirthDate(newBirthdate);
        //
        System.out.println("Birthdate " + isUpdatedBirthdate);

        boolean isUpdateBio = checkBio(newBio);
        //
        System.out.println("Bio " + isUpdateBio);

        //checking the new occupation list for conditions
        boolean isNewOccupations = checkOccupations(newOccupations);

        boolean isUpdateOccupations = false;
        //checking whether the date is before 2000 or not
        if(isNewOccupations){
            isUpdateOccupations = updateArtistOccupations(searchArtist,newOccupations);
        }
        //
        System.out.println("Occupations" + isUpdateOccupations);

        boolean isUpdatedGenres = checkGenres(newGenres);
        //
        System.out.println("Genres " + isUpdatedGenres);

        //getting the new award format
        ArrayList<String> newAwardsAdded = updateArtistAwards(searchArtist,newAwardTitles);
        //checking the new award conditions
        boolean isUpdatedAwards = checkAwards(newAwardsAdded);
        //
        System.out.println("Awards " + isUpdatedAwards);

        if(isUpdateID && isUpdateAddress && isUpdatedBirthdate && isUpdateBio && isUpdateOccupations && isUpdatedGenres && isUpdatedAwards){
            searchArtist.setId(newId);
            searchArtist.setName(newName);
            searchArtist.setAddress(newAddress);
            searchArtist.setBirthdate(newBirthdate);
            searchArtist.setBio(newBio);
            searchArtist.setOccupations(newOccupations);
            searchArtist.setGenres(newGenres);
            searchArtist.setAwards(newAwardsAdded);

            Artist.writeFile();
            return true;
        }

        return false;
    }
}