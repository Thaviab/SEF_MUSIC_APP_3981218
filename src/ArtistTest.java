import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArtistTest {
    private Artist testArtist1 = new Artist();

/*    @BeforeEach
    void intialize(){
        testArtist1 = new Artist();

    }*/
    //---------------------------Testing addArtist--------------------------//

    //testing addArtist with all valid inputs

    @Test
    @Order(1)
    void addArtist_validTest(){
        //Test Data 1
        Artist artist1 = new Artist("569MMMRR_^","Test Artist1.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        int count = Artist.artistsList.size();
        assertTrue(artist1.addArtist(artist1));
        //checks whether the artist is added to the list
        assertEquals(count+1,Artist.artistsList.size());

        //Test Data 2
        Artist artist2 = new Artist("569MMMRR_&","Test Artist1.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        count = Artist.artistsList.size();
        assertTrue(artist2.addArtist(artist2));
        //checks whether the artist is added to the list
        assertEquals(count+1,Artist.artistsList.size());
    }

    //testing addArtist with invalid id

    @Test
    @Order(2)
    void addArtist_IdTest(){
        //Test Data 1
        Artist artist1 = new Artist("569MMMRR_1","Test Artist2.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist2.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //in valid id 569MMMRR_1
        assertFalse(artist2.addArtist(artist2)); //invalid id 169MMmRR_#
    }

    //testing addArtist with invalid address
    @Test
    @Order(3)
    void addArtist_AddressTest(){

        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist3.1","Mel|Vic|Aus|3145","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist3.2","Mel|Vic","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //invalid address Mel|Vic|Aus|3145
        assertFalse(artist2.addArtist(artist2)); //invalid address Mel|Vic
    }

    //testing addArtist with invalid birthdate
    @Test
    @Order(4)
    void addArtist_brithdateTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist4.1","Mel|Vic|Aus","2001-10-11",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist4.2","Mel|Vic|Aus","10-2001-10",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //invalid birthdate 2001-10-11
        assertFalse(artist2.addArtist(artist2)); //invalid birthdate 10-2001-10
    }

    //testing addArtist with invalid bio
    @Test
    @Order(5)
    void addArtist_BioTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist5.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is a talented singer",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist5.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards. His performance is consistent. He has been " +
                        "able to continue is parents legacy throughout his career. He has also showed his talent in various sports.",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1)); //bio less than 10 words
        assertFalse(artist2.addArtist(artist2)); //bio greater than 30 words
    }

    //testing addArtist with invalid occupation conditions
    @Test
    @Order(6)
    void addartist_OccupationTest(){
        //Test Data1
        Artist artist1 = new Artist("569MMMRR_!","Test Artist6.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        //Test Data 2
        Artist artist2 = new Artist("169MMmRR_#","Test Artist6.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist2 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer", "Musician", "Gutarist", "Dancer", "Scientist")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia")));

        assertFalse(artist1.addArtist(artist1));
        assertFalse(artist2.addArtist(artist2));
    }

//---------------------------Test updateArtist--------------------------//

    //testing the functionality of updateArtist with valid inputs
    @Test
    @Order(7)
    void updateArtistTest() throws ParseException {
        Artist.loadArtistFromFile();
        //updating artist added from the first test
        boolean result1 = testArtist1.updateArtist("569MMMRR_^", "569MMMRR@@","Test Artist to Artist1.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist to Artist1.1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer1", "Composer2")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        boolean result2 = testArtist1.updateArtist("569MMMRR_&", "569MMMRR##","Test Artist to Artist1.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));


        assertTrue(result1);
        assertTrue(result2);
    }

    //testing updateArtist with invalid new id
    @Test
    @Order(8)
    void updateArtist_newIDTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR12","Update Artist1.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRRmr","Update Artist1.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //id wrong format 569MMMRR12
        assertFalse(result2); ////id wrong format 569MMMRRmr
    }

    //testing new artist address conditions with invalid address
    @Test
    @Order(9)
    void updateArtist_newAddressTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR&&","Update Artist2.1","Mel|Vic|Aus|3145","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRR%%","Update Artist2.2","Mel|Vic","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //wrong address format Mel|Vic|Aus|3145
        assertFalse(result2); ////wrong address format Mel|Vic
    }

    //testing new artist birthdate with invalid conditions
    @Test
    @Order(10)
    void updateArtist_newBirthdateTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        //adding artist1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR&&","Update Artist3.1","Mel|Vic|Aus","2000-10-11",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRR%%","Update Artist3.3","Mel|Vic|Aus","11-2000-09",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //wrong birthdate format 2000-10-11
        assertFalse(result2); //wrong birthdate format 11-2000-09

    }

    //testing new artist's bio with conditions
    @Test
    @Order(11)
    void updateArtist_newBioTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR&&","Update Artist4.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured singer",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRR%%","Update Artist4.2","Mel|Vic","11-2000-09",
                "Test Artist1 is very matured and talented singer with many awards. His performance is consistent. He has been " +
                        "able to continue is parents legacy throughout his career. He has also showed his talent in various sports.",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("Pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //bio less than 10 words
        assertFalse(result2); //bio more than 30 words
    }

    //testing new artist's occupations with invalid occupations
    @Test
    @Order(12)
    void updateArtist_newOccupationsTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR!@","Update Artist5.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRR@!","Update Artist5.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer", "Musician","Gutarist","Dancer","Scientist")),
                new ArrayList<>(Arrays.asList("pop","jazz")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //no occupation
        assertFalse(result2); //six occupations
    }

    //testing new artist's genres with conditions
    @Test
    @Order(13)
    void updateArtist_newGenresTest() throws ParseException {
        Artist.loadArtistFromFile();
        //TEST DATA 1
        boolean result1 = testArtist1.updateArtist("569MMMRR@@", "569MMMRR!!","Update Artist6.1","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop","rock")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        //TEST DATA 2
        boolean result2 = testArtist1.updateArtist("569MMMRR##", "569MMMRR_!","Update Artist6.2","Mel|Vic|Aus","10-10-2000",
                "Test Artist1 is very matured and talented singer with many awards",new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop")),new ArrayList<>(Arrays.asList("Best Singer award Australia", "Best Album award Australia")));

        assertFalse(result1); //pop and rock
        assertFalse(result2); //one genre
    }
}