import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Artist(){
        this.Awards = new ArrayList<>();
    }
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

    public static boolean addArtist() {
        Scanner details = new Scanner(System.in);
        //getting user inputs for Artist
        String id;
        boolean checkID;
        do {
            System.out.print("Enter Artist ID: ");
            id = details.nextLine();
            checkID = Artist.checkArtistID(id);
            if (!checkID) System.out.println("Wrong ID format");
        }while (!checkID);

        System.out.print("Enter Artist Name: ");
        String name = details.nextLine();

        String address;
        boolean checkAdress;
        do {
            System.out.print("Enter Address (City|State|Country): ");
            address = details.nextLine();
            checkAdress = Artist.checkAddress(address);
            if (!checkAdress) System.out.println("Wrong address format");
        }while (!checkAdress);

        String birthdate;
        boolean checkBirthdate;
        do {
            System.out.print("Enter artist birthdate (DD-MM-YYYY): ");
            birthdate = details.nextLine();
            checkBirthdate = Artist.checkBirthDate(birthdate);
            if (!checkBirthdate) System.out.println("Wrong birthdate format");
        }while (!checkBirthdate);

        String bio;
        boolean checkBio;
        do {
            System.out.print("Enter Artist Bio: ");
            bio = details.nextLine();
            checkBio = Artist.checkBio(bio);
            if(!checkBio) System.out.println("Wrong Bio format");
        }while (!checkBio);

        ArrayList<String> occupations;
        boolean checkOccupations;
        do {
            System.out.print("Enter Artist Occupations (Ex: Singer|Songwriter): ");
            occupations = new ArrayList<>(Arrays.asList(details.nextLine()));
            checkOccupations = Artist.checkOccupations(occupations);
            if (!checkOccupations) System.out.println("Wrong occupation format");
        }while (!checkOccupations);

        ArrayList<String> genres = null;
        String genreString;
        boolean checkGenres;
        do {
            System.out.print("Enter Artist Genres (Ex: pop|classical): ");
            genreString = details.nextLine();
            //genres = new ArrayList<>(Arrays.asList(details.nextLine()));
            checkGenres = Artist.checkGenres(genreString);
            //checkGenres = Artist.checkGenres(genres);
            if (!checkGenres) {
                System.out.println("Wrong genre format");
            }else {
                genres = new ArrayList<>(Arrays.asList(genreString.split("\\|")));
            }
        }while (!checkGenres);

        ArrayList<String> awards = null;
        String awardsString;
        boolean checkAwards;
        do {
            System.out.print("Enter Artist Awards (Format: Year|Title): ");
            awardsString = details.nextLine();
            //awards = new ArrayList<>(Arrays.asList(details.nextLine()));
            checkAwards = Artist.checkAwards(awardsString);
            //checkAwards = Artist.checkAwards(awards);
            if(!checkAdress) {
                System.out.println("Wrong awards");
            }else {
                awards = new ArrayList<>(Arrays.asList(awardsString.split(",")));
            }
            //if (!checkAwards) System.out.println("Wrong awards format");
        }while (!checkAwards);

        Artist newArtist = new Artist(id,name,address,birthdate,bio,occupations,genres,awards);
        artistsList.add(newArtist);
        Artist.writeFile();
        return true;
    }
    public static void writeFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\RMIT\\Sem 2\\SEF\\Assignment\\4\\SEF_MUSIC_APP\\resources\\SEF_Music.txt"))) {
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

    public static boolean checkArtistID(String id) {
        boolean idLength = id.length() == 10; //checks its 10 characters
        boolean pattern = id.matches("^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{2}$");
        //^[5-9]{3} = check first 3 characters between 5-9
        //[A-Z]{3} - checks next 5 characters
        //then checks the last 2 characters for special chcaracter
        return idLength && pattern;
    }

    public static boolean checkBirthDate(String birthdate) {
        return birthdate.matches("^\\d{2}-\\d{2}-\\d{4}$");
    }

    public static boolean checkAddress(String address) {
        String[] checkAddress = address.split("\\|");
        return checkAddress.length == 3;
    }

    public static boolean checkBio(String bio) {
        String[] words = bio.split("\\s+");
        return words.length >= 10 && words.length <= 30;
    }

    public static boolean checkOccupations(ArrayList<String> occupations) {
        if (occupations == null || occupations.isEmpty()) {
            return false;
        }
        for (String occupationList : occupations) {
            String[] checkOccupations = occupationList.split("\\|");
            if (checkOccupations.length < 1 || checkOccupations.length > 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAwards(String awards){
        if(awards == null || awards.trim().isEmpty()){
            return true;
        }
        String[] checkAwards = awards.split(",");
        for(String award : checkAwards){
            String[] awardParts = award.split("\\|");
            if(awardParts.length != 2){
                return false;
            }
            String title = awardParts[1];
            String[] titleWords = title.trim().split("\\s+");
            if (titleWords.length < 4 || titleWords.length > 10){
                return false;
            }
        }
        return true;
    }
    public static boolean checkGenres(String genres){
        if(genres==null || genres.trim().isEmpty()){
            return false;
        }
        String[] checkGenres = genres.split("\\|");
        if(checkGenres.length < 2 || checkGenres.length > 5){
            return false;
        }
        List<String> genreList = Arrays.asList(checkGenres);
        if(genreList.contains("pop") && genreList.contains("rock")){
            return false;
        }
        return true;
    }

/*    public static boolean checkGenres(ArrayList<String> genres) {
        if (genres == null || genres.isEmpty()) {
            return false;
        }
        for (String genreList : genres) {
            String[] checkGenres = genreList.split("\\|");
            if (checkGenres.length < 2 || checkGenres.length > 5) {
                return false;
            }
            List<String> genreListAsList = Collections.singletonList(genreList);
            if (genreListAsList.contains("pop") && genreListAsList.contains("rock")) {
                return false;
            }
        }
        return true;
    }*/

    public static void loadArtistFromFile() {
        String path = "D:\\RMIT\\Sem 2\\SEF\\Assignment\\4\\SEF_MUSIC_APP\\resources\\SEF_Music.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String record;
            while ((record = reader.readLine()) != null) {
                String[] recordParts = record.split(", ", 8);
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

    public static boolean updateArtist() {
        Scanner details = new Scanner(System.in);
        System.out.print("Enter the Artist ID to update: ");
        String id = new Scanner(System.in).nextLine();

        Artist searchArtist = null;
        for (Artist artist : artistsList) {
            if (artist.getId().equals(id)) {
                searchArtist = artist;
                break;
            }
        }
        if (searchArtist == null){
            System.out.println("Artist with ID " + id + " not found");
            return false;
        }
        System.out.println("Updating artist with ID: " + searchArtist.getId());

        //updating ID
        String newId;
        boolean checkID;
        do {
            System.out.print("Enter new Artist ID: ");
            newId = details.nextLine();
            checkID = Artist.checkArtistID(newId);
            if (!checkID){
                System.out.println("Wrong ID format");
            }else {
                searchArtist.setId(newId);
            }
        }while (!checkID);

        System.out.println("Name: " + searchArtist.getName());
        System.out.print("Enter new artist name: ");
        String newName = details.nextLine();
        searchArtist.setName(newName);

        //updating address
        String newAddress;
        boolean checkAdress;
        System.out.println("Address: "+ searchArtist.getAddress());
        do {
            System.out.print("Enter new Address (City|State|Country): ");
            newAddress = details.nextLine();
            checkAdress = Artist.checkAddress(newAddress);
            if (!checkAdress){
                System.out.println("Wrong address format");
            }else {
                searchArtist.setAddress(newAddress);
            }
        }while (!checkAdress);

        //updating birthdate
        String newBirthdate;
        boolean checkBirthdate;
        System.out.println("Birthdate: "+ searchArtist.getBirthdate());
        do {
            System.out.print("Enter new artist birthdate (DD-MM-YYYY): ");
            newBirthdate = details.nextLine();
            checkBirthdate = Artist.checkBirthDate(newBirthdate);
            if (!checkBirthdate){
                System.out.println("Wrong birthdate format");
            }else {
                searchArtist.setBirthdate(newBirthdate);
            }
        }while (!checkBirthdate);

        //updating Bio
        String newBio;
        boolean checkBio;
        System.out.println("Bio: "+ searchArtist.getBio());
        do {
            System.out.print("Enter new Artist Bio: ");
            newBio = details.nextLine();
            checkBio = Artist.checkBio(newBio);
            if(!checkBio){
                System.out.println("Wrong Bio format");
            }else {
                searchArtist.setBio(newBio);
            }
        }while (!checkBio);

        //updating occupations
        ArrayList<String> newOccupations;
        boolean checkOccupations;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date birthdate;
        try {
            birthdate = format.parse(searchArtist.getBirthdate());
            Date year = format.parse("01-01-2000");
            if (birthdate.before(year)){
                System.out.println("Occupation cannot be changed");
            }else {
                do {
                    System.out.print("Enter new Artist Occupations (Ex: Singer|Songwriter): ");
                    newOccupations = new ArrayList<>(Arrays.asList(details.nextLine()));
                    checkOccupations = Artist.checkOccupations(newOccupations);
                    if (!checkOccupations){
                        System.out.println("Wrong occupation format");
                    }else {
                        searchArtist.setOccupations(newOccupations);
                    }
                }while (!checkOccupations);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //updating genres
        ArrayList<String> newGenres;
        String newGenreString;
        boolean checkGenres;
        do {
            System.out.print("Enter new Artist Genres (Ex: pop|classical): ");
            newGenreString = details.nextLine();
            checkGenres = Artist.checkGenres(newGenreString);
            if (!checkGenres) {
                System.out.println("Wrong genre format");
            }else {
                newGenres = new ArrayList<>(Arrays.asList(newGenreString.split("\\|")));
                searchArtist.setGenres(newGenres);
            }
        }while (!checkGenres);

        //updating awards
        ArrayList<String> updatedAwardsArray = new ArrayList<>();
        boolean checkAwards;
        String newAwardsString;
        System.out.println(searchArtist.getAwards());
        for(String award1 : searchArtist.getAwards()){
            String[] firstPart = award1.split(",");
            //System.out.println(Arrays.toString(firstPart));
            for(int i = 0; i < firstPart.length; i++){
                String[] secondPart = firstPart[i].split("\\|");
                System.out.println(Arrays.toString(secondPart));
                int year = Integer.parseInt(secondPart[0].trim());
                if(year < 2000) {
                    System.out.println("This award cannot be changed");
                }else {
                    System.out.print("Enter new title: ");
                    secondPart[1] = details.nextLine();

                    firstPart[i] = secondPart[0]+"|"+secondPart[1];
                }
            }
            newAwardsString = String.join(",",firstPart);
            updatedAwardsArray.add(newAwardsString);
        }
        searchArtist.setAwards(updatedAwardsArray);
        /*for(String award1 : searchArtist.getAwards()){
            String[] firstPart = award1.split(",");
            System.out.println(Arrays.toString(firstPart));
            for(String award2 : firstPart){
                String[] secondPart = award2.split("\\|");
                System.out.println(Arrays.toString(secondPart));
                int year = Integer.parseInt(secondPart[0].trim());
                if(year<2000){
                    System.out.println("This award cannot be updated");
                }else {
                    System.out.print("Enter new title: ");
                    secondPart[1] = details.nextLine();
                }
            }
        }*/
        Artist.writeFile();
        return true;
    }
}