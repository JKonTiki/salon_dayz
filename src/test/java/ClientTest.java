import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest{

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
  public void equals_returnTrueIfClientsAreTheSame(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    Client newClient2 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    assertTrue(newClient1.equals(newClient2));
  }

  @Test
  public void save_returnTrueIfClientsAreTheSame(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    newClient1.save();
    assertTrue(Client.all().get(0).equals(newClient1));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    newClient1.save();
    Client newClient2 = new Client("Jean Valjean", "l'hero avec beaucoup de braverie", 3);
    newClient2.save();
    assertEquals(true, Client.all().get(0).equals(newClient1));
    assertEquals(true, Client.all().get(1).equals(newClient2));
  }

  @Test
  public void save_assignsIdToObject(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    newClient1.save();
    Client saveClient = Client.all().get(0);
    assertEquals(newClient1.getId(), saveClient.getId());
  }

  @Test
  public void getId_ClientsInstantiateWithId(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    newClient1.save();
    assertTrue(newClient1.getId()>0);
  }

  @Test
    public void find_returnsClientWithSameId_newClient2(){
    Client newClient1 = new Client("Javert", "l'anti-hero avec beaucoup des ambitions graves", 3);
    newClient1.save();
    Client newClient2 = new Client("Jean Valjean", "l'hero avec beaucoup de braverie", 3);
    newClient2.save();
    assertEquals(true, Client.all().get(0).equals(newClient1));
    assertEquals(Client.find(newClient2.getId()), newClient2);
    }

  @Test
    public void delete_removesClientsFromDatabase_true() {
      Client testClient = new Client("Jean Valjean", "l'hero avec beaucoup de braverie", 3);
      testClient.save();
      int testClientId = testClient.getId();
      testClient.delete();
      assertEquals(null, Client.find(testClientId));
    }


}
