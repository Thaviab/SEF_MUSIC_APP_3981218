import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {
    private Artist testArtist1;
    private Artist testArtist2;

    @BeforeEach
    public void setup(){
        ArrayList<String> occupations = new ArrayList<>(Arrays.asList("Singer", "Composer"));
        ArrayList<String> genres = new ArrayList<>(Arrays.asList("Pop"));
        ArrayList<String> awards;
        awards = new ArrayList<>(Arrays.asList("2010|Best Singer award Australia", "2015|Best Album award Australia"));

        testArtist1 = new Artist("569MMMRR_^","Test1","T1|T1|T1","10-10-2000","Test1 is very matured and talented singer with many awards",
                occupations,genres,awards);
        testArtist2 = new Artist("569MMMRR_#","Test2","T2|T2|T2","10-10-1998","Test2 is very matured and talented singer with many awards",
                occupations,genres,awards);

        Artist.artistsList = new ArrayList<>();
        Artist.artistsList.add(testArtist1);
        Artist.artistsList.add(testArtist2);

    }

    @Test
    void addArtistTest() {
        String testData = "569MMMRR_*\n"+"Test\n"+"Mel|Vic|Aus\n"+
                "02-10-1998\n"+"Test case is a very young talented singer with many awards\n"+
                "Singer|Writer\n"+"pop|classical\n"+"2022|Best pop composer Australia\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(testData.getBytes()));
            int count = Artist.artistsList.size();
            assertTrue(Artist.addArtist());
            //checks whether the artist is added to the list
            assertEquals(count+1,Artist.artistsList.size());
        }finally {
            System.setIn(stdin);
        }
    }

    @Test
    void checkArtistIDTest() {
        assertTrue(Artist.checkArtistID("569MMMRR_%")); //True
        assertFalse(Artist.checkArtistID("169MMMRR_%")); //True
        assertFalse(Artist.checkArtistID("569mmmRR_%")); //True
        assertFalse(Artist.checkArtistID("569MMMRR_1")); //True
    }

    @Test
    void checkBirthDateTest() {
        assertTrue(Artist.checkBirthDate("12-10-1998"));
        assertFalse(Artist.checkBirthDate("1998-10-09"));
        assertFalse(Artist.checkBirthDate("09-1998-10"));
        assertFalse(Artist.checkBirthDate("12-23-98"));
    }

    @Test
    void checkAddressTest() {
        assertTrue(Artist.checkAddress("MEL|VIC|Aus"));
        assertFalse(Artist.checkAddress("MEL,VIC,Aus"));
        assertFalse(Artist.checkAddress("Mel|VIC|3145|Aus"));
    }

    @Test
    void checkBioTest() {
        assertTrue(Artist.checkBio("A sample bio to check the junit test of checkBio() in Artist"));
        assertFalse(Artist.checkBio("A sample bio to check the junit test of checkBio() in Artist with words more than 30 30 30 30 30 30 30 30 30" +
                "30 30 30 30 30 30 30 30 30 30 30 30 30"));
        assertFalse(Artist.checkBio("A sample short bio"));
    }

    @Test
    void checkOccupationsTest() {
        assertTrue(Artist.checkOccupations(new ArrayList<>(Arrays.asList("Singer|Songwriter"))));
        assertFalse(Artist.checkOccupations(new ArrayList<>(Arrays.asList())));
        assertFalse(Artist.checkOccupations(new ArrayList<>(Arrays.asList("Singer|Songwriter|Composer|Musician|Beatboxer|Rapper"))));
    }

    @Test
    void checkAwardsTest() {
        assertTrue(Artist.checkAwards("2023|Best award for young talent"));
        assertTrue(Artist.checkAwards("2023|Best award for young talent, 2023|Best composer of the year"));
        assertFalse(Artist.checkAwards("2023|Best music award"));
        assertFalse(Artist.checkAwards("2023|Best music award, 2022|Best music award"));
    }

    @Test
    void checkGenresTest() {
        assertTrue(Artist.checkGenres("pop|classical"));
        assertFalse(Artist.checkGenres("pop|rock"));
        assertFalse(Artist.checkGenres("pop"));
        assertFalse(Artist.checkGenres("pop|classical|jazz|techno|house|baila"));
    }


    @Test
    void searchArtistTest(){
        Artist searchArtist = Artist.searchForArtist("569MMMRR_^");
        assertEquals("569MMMRR_^", searchArtist.getId());
    }

    @Test
    void updateArtistID(){
        assertTrue(Artist.updateArtistID(testArtist1,"569MMMRR_^"));
        assertEquals("569MMMRR_^",testArtist1.getId());
        assertFalse(Artist.updateArtistID(testArtist1,"569mmmRR_^"));
    }
    @Test
    void updateArtistAddressTest(){
        assertTrue(Artist.updateArtistAddress(testArtist1,"Mel|Vic|Aus"));
        assertEquals("Mel|Vic|Aus",testArtist1.getAddress());
    }

    @Test
    void updateArtistBirthdateTest(){
        assertTrue(Artist.updateArtistBirthdate(testArtist1,"12-10-1998"));
        assertEquals("12-10-1998",testArtist1.getBirthdate());
    }
    @Test
    void updateArtistBioTest(){
        assertTrue(Artist.updateArtistBio(testArtist1,"A globally recognized artist with many awards under his name"));
        assertEquals("A globally recognized artist with many awards under his name",testArtist1.getBio());
    }

    @Test
    void updateArtistOccupationsTest() throws ParseException {
        ArrayList<String> newOccupations = new ArrayList<>(Arrays.asList("Singer","Composer"));
        assertTrue(Artist.updateArtistOccupations(testArtist1,newOccupations));
        assertEquals(newOccupations,testArtist1.getOccupations());
    }
    @Test
    void updateArtistGenresTest(){
        assertTrue(Artist.updateArtistGenres(testArtist1,"Rock|Jazz"));
        ArrayList<String> expectedGenres = new ArrayList<>(Arrays.asList("Rock","Jazz"));
        assertEquals(expectedGenres,testArtist1.getGenres());
    }
}