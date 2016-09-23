import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void equals_returnsTrueIfNamesAreTheSame(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    Stylist newStylist2 = new Stylist("Jerome","un homme gentrifique et plome");
    assertTrue(newStylist1.equals(newStylist2));
  }

  @Test
  public void save_returnsSavedStylist_true(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist1.save();
    assertEquals(newStylist1, Stylist.all().get(0));
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist1.save();
    Stylist newStylist2 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist2.save();
    assertEquals(true, Stylist.all().get(0).equals(newStylist1));
    assertEquals(true, Stylist.all().get(1).equals(newStylist2));
  }

  @Test
  public void save_assignsIdToObject(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist1.save();
    Stylist saveStylist = Stylist.all().get(0);
    assertEquals(newStylist1.getId(), saveStylist.getId());
  }

  @Test
  public void getId_StylistsInstantiateWithId(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist1.save();
    assertTrue(newStylist1.getId()>0);
  }

  @Test
  public void find_returnsStylistWithSameId_newStylist2(){
  Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
  newStylist1.save();
  Stylist newStylist2 = new Stylist("Maquis","Le long raconteur des couches magnifiques");
  newStylist2.save();
  assertEquals(true, Stylist.all().get(0).equals(newStylist1));
  assertEquals(Stylist.find(newStylist2.getId()), newStylist2);
  }

  @Test
  public void getClients_returnsClients_List(){
    Stylist newStylist1 = new Stylist("Jerome","un homme gentrifique et plome");
    newStylist1.save();
    Client newClient1 = new Client("Jean Valjean", "l'hero avec beaucoup de braverie", newStylist1.getId());
    newClient1.save();
    Client newClient2 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", newStylist1.getId());
    newClient2.save();
    assertEquals(newStylist1.getClients().get(0), newClient1);
  }

  @Test
    public void delete_removesStylistsFromDatabase_true() {
      Stylist testStylist = new Stylist("Jerome","un homme gentrifique et plome");
      testStylist.save();
      int testStylistId = testStylist.getId();
      testStylist.delete();
      assertEquals(null, Stylist.find(testStylistId));
    }

  @Test
  public void deleteClients_removesClientsFromStylist_true(){
    Stylist testStylist = new Stylist("Jerome","un homme gentrifique et plome");
    testStylist.save();
    Client testClient = new Client("Jean Valjean", "l'hero avec beaucoup de braverie", testStylist.getId());
    testClient.save();
    int testClientId = testClient.getId();
    testStylist.deleteClients();
    testStylist.delete();
    assertEquals(null, Client.find(testClientId));
  }

  @Test
    public void update_changesInfoToNewValue_true(){
      Stylist testStylist = new Stylist("Jerome","un homme gentrifique et plome");
      testStylist.save();
      testStylist.setInfo("l'info nouvelle");
      testStylist.updateInfo();
      assertEquals("l'info nouvelle",testStylist.getInfo());
    }

}
