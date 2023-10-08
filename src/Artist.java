import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations, ArrayList<String> genres,
                  ArrayList<String> awards){
        this.id = id;
        this.name=name;
        this.address=address;
        this.birthdate=birthdate;
        this.bio=bio;
        this.Occupations=occupations;
        this.Genres=genres;
        this.Awards=awards;
    }
    public boolean addArtist(){
        /*if(this.checkArtistID()){
            System.out.println("ID OK");
            if (this.checkAddress()){
                System.out.println("Address OK");
                if (this.checkBirthDate()){
                    System.out.println("Birthdate OK");
                    if (this.checkBio()){
                        System.out.println("Bio OK");
                        if (this.checkOccupations()){
                            System.out.println("Occu OK");
                            if (this.checkGenres()){
                                System.out.println("Genre OK");
                                if (checkAwards()){
                                    System.out.println("Awards OK");
                                }
                            }
                        }
                    }
                }
            }
        }*/
        if (!checkArtistID() || !checkAddress() || !checkBirthDate() || !checkBio() || !checkOccupations() || !checkGenres() || !checkAwards()){
            return false;
        }
        artistsList.add(this);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\RMIT\\Sem 2\\SEF\\Assignment\\4\\SEF_MUSIC_APP\\resources\\SEF_Music.txt"))) {
            for (Artist artist : artistsList){
                writer.write(artist.getId() + ", " +
                                artist.getName() + ", " +
                                artist.getAddress() + ", " +
                                artist.getBirthdate() + ", " +
                                artist.getBio() + ", " +
                                String.join("|", artist.getOccupations())+ ", " +
                                String.join("|",artist.getGenres()) + ", " +
                                String.join("|",artist.getAwards()) + "\n");
            }
            System.out.println("Artist added successfully");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Artist adding unsucessfull");
            return false;
        }
    }

    public boolean checkArtistID(){
        boolean idLength = getId().length() == 10; //checks its 10 characters
        boolean pattern = getId().matches("^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{2}$");
        //^[5-9]{3} = check first 3 characters between 5-9
        //[A-Z]{3} - checks next 5 characters
        //then checks the last 2 characters for special chcaracter
        return idLength && pattern;
    }
    public boolean checkBirthDate(){
        return getBirthdate().matches("^\\d{2}-\\d{2}-\\d{4}$");
    }
    public boolean checkAddress(){
        String[] address = getAddress().split("\\|");
        return address.length == 3;
    }
    public boolean checkBio(){
        String[] words = getBio().split("\\s+");
        return words.length >= 10 && words.length <=30;
    }
    public boolean checkOccupations(){
        if(getOccupations() == null || getOccupations().isEmpty()){
            return false;
        }
        for (String occupationList : getOccupations()){
            String[] occupations = occupationList.split("\\|");
            if (occupations.length < 1 || occupations.length > 5){
                return false;
            }
        }
        return true;
    }
    public boolean checkAwards(){
        if(getAwards()==null && getAwards().isEmpty()){
            return true;
        }
        String[] awards = String.join("|",getAwards()).split("\\|");
        if (awards.length>3){
            return false;
        }
        for (String award : awards){
            String[] awardParts = award.split(", ");
            if (awardParts.length!=2){
                return false;
            }
            String title = awardParts[1];
            String[] titleWords = title.split("\\s+");
            if(titleWords.length < 4 || titleWords.length > 10){
                return false;
            }
        }
        return true;
    }
    public boolean checkGenres(){
        if(getGenres() == null || getGenres().isEmpty()){
            return false;
        }
        for (String genreList : getGenres()){
            String[] genres = genreList.split("\\|");
            if (genres.length < 2 || genres.length > 5){
                return false;
            }
            List<String> genreListAsList = Arrays.asList(genreList);
            if (genreListAsList.contains("pop") && genreListAsList.contains("rock")){
                return false;
            }
        }
        return true;
    }
    public static void loadArtistFromFile(){
        String path = "D:\\RMIT\\Sem 2\\SEF\\Assignment\\4\\SEF_MUSIC_APP\\resources\\SEF_Music.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String record;
            while ((record = reader.readLine()) != null){
                String[] recordParts = record.split(", ",8);
                String id = recordParts[0];
                String name = recordParts[1];
                String address = recordParts[2];
                String birthdate = recordParts[3];
                String bio = recordParts[4];
                ArrayList<String> occupations = new ArrayList<>(Arrays.asList(recordParts[5]));
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(recordParts[6]));
                ArrayList<String> awards = new ArrayList<>(Arrays.asList(recordParts[7]));
                Artist artist = new Artist(id, name, address, birthdate, bio, occupations, genres, awards);
                artistsList.add(artist);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayAllArtists() {
        if(Artist.artistsList.isEmpty()){
            System.out.println("No Artists available");
            return;
        }
        for (Artist artist: Artist.artistsList){
            System.out.println("------------------------------");
            System.out.println("ID: "+artist.getId());
            System.out.println("Name: "+artist.getName());
            System.out.println("Address: "+artist.getAddress());
            System.out.println("Birthdate: "+artist.getBirthdate());
            System.out.println("Bio: "+artist.getBio());
            System.out.println("Occupations: "+artist.getOccupations());
            System.out.println("Genres: "+artist.getGenres());
            System.out.println("Awards: "+artist.getAwards());
            System.out.println("------------------------------");
        }
    }
    public boolean updateArtist(){
        return true;
    }
}