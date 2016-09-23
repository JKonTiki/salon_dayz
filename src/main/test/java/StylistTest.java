import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ArtistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/collection_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteArtistsQuery = "DELETE FROM artists *;";
      String deleteRecordsQuery = "DELETE FROM records *;";
      con.createQuery(deleteRecordsQuery).executeUpdate();
      con.createQuery(deleteArtistsQuery).executeUpdate();
    }
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Artist firstArtist = new Artist("Test");
    Artist secondArtist = new Artist("Test");
    assertTrue(firstArtist.equals(secondArtist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Artist myArtist = new Artist("Test");
    myArtist.save();
    assertTrue(Artist.all().get(0).equals(myArtist));
  }

  @Test
  public void all_returnsAllInstancesOfArtist_true() {
    Artist firstArtist = new Artist("Test");
    firstArtist.save();
    Artist secondArtist = new Artist("Wall");
    secondArtist.save();
    assertEquals(true, Artist.all().get(0).equals(firstArtist));
    assertEquals(true, Artist.all().get(1).equals(secondArtist));
  }

  @Test
  public void save_assignsIdtoObject() {
    Artist myArtist = new Artist("new");
    myArtist.save();
    Artist savedArtist = Artist.all().get(0);
    assertEquals(myArtist.getId(), savedArtist.getId());
  }

  @Test
  public void getId_artistsInstantiateWithId_1() {
    Artist testArtist = new Artist("test");
    testArtist.save();
    assertTrue(testArtist.getId(0));
  }

  @Test
  public void find_returnsArtistWithSameId_secondArtist() {
    Artist firstArtist = new Artist("RHCP");
    firstArtist.save();
    Artist secondArtist = new Artist("CCR");
    secondArtist.save();
    assertEquals(Artist.find(secondArtist.getId()), secondArtist)
  }

  @Test
  public void getRecords_retrievesAllRecordsFromDatabase_recordsList() {
    Artist myArtist = new Artist("Test");
    myArtist.save();
    Record firstRecord = new Record("Tank", myArtist.getId());
    firstRecord.save();
    Record secondRecord = new Record("Wall", myArtist.getId());
    secondRecord.save();
    Record[] records = new Record[] { firstRecord, secondRecord };
    assertTrue(myArtist.getRecords().containsAll(Arrays.asList(records)));
  }

}
