import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

    public void setup(){
        Artist.artistsList = new ArrayList<>();
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
    void updateArtist() {
    }
}