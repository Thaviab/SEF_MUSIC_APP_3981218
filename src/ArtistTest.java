import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {
    private Artist testArtist1;
    private Artist testArtist2;
    private Artist testArtist3;

    @BeforeEach
    void intialize(){
        testArtist1 = new Artist();

    }
    //---------------------------Testing addArtist--------------------------//

    //testing addArtist with all valid inputs
    @Test
    void addArtist_validTest(){
        //Test Data 1
        Artist artist1 = new Artist("569MMMRR_^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        int count = Artist.artistsList.size();
        assertTrue(artist1.addArtist(artist1));
        //checks whether the artist is added to the list
        assertEquals(count+1,Artist.artistsList.size());

        //Test Data 2
        Artist artist2 = new Artist("569MMMRR_^","Test Artist2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        count = Artist.artistsList.size();
        assertTrue(artist2.addArtist(artist2));
        //checks whether the artist is added to the list
        assertEquals(count+1,Artist.artistsList.size());
    }

    //testing addArtist with invalid id
    @Test
    void addArtist_IdTest(){
        //Test Data 1
        Artist artist1 = new Artist("569MMMRR_1","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //in valid id 569MMMRR_1
        assertFalse(artist2.addArtist(artist2)); //invalid id 169MMmRR_#
    }

    //testing addArtist with invalid address
    @Test
    void addArtist_AddressTest(){

        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist1","Mel|Vic|Aus|3145","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2","Mel|Vic","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //invalid address Mel|Vic|Aus|3145
        assertFalse(artist2.addArtist(artist2)); //invalid address Mel|Vic
    }

    //testing addArtist with invalid birthdate
    @Test
    void addArtist_brithdateTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist1","Mel|Vic|Aus","2001-10-11",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2","Mel|Vic|Aus","10-2001-10",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //invalid birthdate 2001-10-11
        assertFalse(artist2.addArtist(artist2)); //invalid birthdate 10-2001-10
    }

    //testing addArtist with invalid bio
    @Test
    void addArtist_BioTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is a talented singer",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards. His performance is consistent. He has been " +
                        "able to continue is parents legacy throughout his career. He has also showed his talent in various sports.",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //bio less than 10 words
        assertFalse(artist2.addArtist(artist2)); //bio greater than 30 words
    }

    //testing addArtist with invalid occupation conditions
    @Test
    void addartist_OccupationTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer", "Musician", "Gutarist", "Dancer", "Scientist")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1));
        assertFalse(artist2.addArtist(artist2));
    }

//---------------------------Test updateArtist--------------------------//

    //testing updateArtist with invalid new id
    @Test
    void updateArtist_newIDTest() throws ParseException {

        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR12","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRRmr","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //id wrong format 569MMMRR12
        assertFalse(result2); ////id wrong format 569MMMRRmr
    }

    //testing new artist address conditions with invalid address
    @Test
    void updateArtist_newAddressTest() throws ParseException {

        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR&&","Test Artist1","Mel|Vic|Aus|3145","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        //adding artist2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRR%%","Test Artist1","Mel|Vic","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //wrong address format Mel|Vic|Aus|3145
        assertFalse(result2); ////wrong address format Mel|Vic
    }

    //testing new artist birthdate with conditions
    @Test
    void updateArtist_newBirthdateTest() throws ParseException {
        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR&&","Test Artist1","Mel|Vic|Aus","2000-10-11",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        //adding artist2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRR%%","Test Artist1","Mel|Vic|Aus","11-2000-09",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //wrong birthdate format 2000-10-11
        assertFalse(result2); //wrong birthdate format 11-2000-09

    }

    //testing new artist's bio with conditions
    @Test
    void updateArtist_newBioTest() throws ParseException {
        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR&&","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured singer",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        //adding artist2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRR%%","Test Artist1","Mel|Vic","11-2000-09",
                "Test Artist1 is very matured and talented singer with many awards. His performance is consistent. He has been " +
                        "able to continue is parents legacy throughout his career. He has also showed his talent in various sports.",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //bio less than 10 words
        assertFalse(result2); //bio more than 30 words
    }

    //testing new artist's occupations with invalid occupations
    @Test
    void updateArtist_newOccupationsTest() throws ParseException {
        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR!@","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Aust\"Singer\", \"Composer\", \"Musician\",\"Gutarist\",\"Dancer\",\"Scientist\"ralia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRR@!","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer", "Musician","Gutarist","Dancer","Scientist")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //no occupation
        assertFalse(result2); //six occupations
    }

    //testing new artist's genres with conditions
    @Test
    void updateArtist_newGenresTest() throws ParseException {

        //TEST DATA 1
        //adding artist1
        Artist artist1 = new Artist("569MMMRR^^","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist1.addArtist(artist1);
        Artist.loadArtistFromFile();

        boolean result1 = artist1.updateArtist("569MMMRR^^", "569MMMRR!!","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","rock")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        Artist artist2 = new Artist("569MMMRR$$","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));
        artist2.addArtist(artist2);
        Artist.loadArtistFromFile();

        boolean result2 = artist2.updateArtist("569MMMRR$$", "569MMMRR_!","Test Artist1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //pop and rock
        assertFalse(result2); //one genre
    }


}