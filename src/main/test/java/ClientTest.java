import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class RecordTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/records_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRecordsQuery = "DELETE FROM records *;";
      String deleteArtistsQuery = "DELETE FROM artists *;";
      con.createQuery(deleteRecordsQuery).executeUpdate();
      con.createQuery(deleteArtistsQuery).executeUpdate();
    }
  }

  @Test
  public void equals_returnsTrueifNamesAreTheSame() {
    Record newRecord = new Record("test", 1);
    Record newRecord2 = new Record("test", 2);
    assertTrue(newRecord.equals(newRecord2));
  }

  @Test
  public void save_returnsTrueifNamesAretheSame() {
    Record newRecord = new Record("test", 1);
    newRecord.save();
    assertTrue(Record.all().get(0).equals(newRecord));
  }

  @Test
  public void all_returnsAllInstancesOfRecord_true() {
    Record firstRecord =  new Record("test", 1);
    firstRecord.save();
    Record secondRecord = new Record("wall", 2);
    secondRecord.save();
    assertEquals(true, Record.all().get(0).equals(firstRecord));
    assertEquals(true, Record.all().get(1).equals(secondRecord));
  }

  @Test
  public void save_assignsIdToObject() {
    Record myRecord = new Record("test", 1);
    myRecord.save();
    Record savedRecord = Record.all().get(0);
    assertEquals(myRecord.getId(), savedRecord.getId());
  }

  @Test
  public void getId_recordsInstantiateWithId() {
    Record myRecord = new Record("test", 1);
    myRecord.save();
    assertTrue(myRecord.getId() > 0);
  }

  @Test
  public void find_returnsRecordWithSameId_secondRecord() {
    Record firstRecord = new Record("test", 1);
    firstRecord.save();
    Record secondRecord = new Record("wall", 2);
    secondRecord.save();
    assertEquals(Record.find(secondRecord.getId()), secondRecord);
  }

  @Test
  public void save_savesArtistIdIntoDB_true() {
    Artist myArtist = new Artist("Test");
    myArtist.save();
    Record myRecord = new Record("wall", myArtist.getId());
    myRecord.save();
    Record savedRecord = Record.find(myRecord.getId());
    assertEquals(savedRecord.getArtistId(), myArtist.getId());
  }
}
