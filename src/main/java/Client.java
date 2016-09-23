import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Client {

  private int id;
  private int stylistId;
  private String info;

  public Client(String _info, int _stylistId int) {
    info = _info;
    stylistId = _stylistId;
  }

  public String getInfo(){
    return info;
  }

  public int getId(){
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, stylistId, info FROM clients";
      return con.createQuery(sql).executeAndFetch(Person.class);
    }
  }

  public static Client find(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, stylistId, info FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients(info, stylistId) VALUES(:info, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("info", this.info)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  @Override
    public boolean equals(Object otherClient){
      if(!(otherClient instanceof Client)){
        return false;
      }else {
        Client newClient = (Client) otherClient;
        return this.getInfo().equals(newClient.getInfo()) &&
          this.getId() == newClient.getId() &&
          this.getStylistId() == newClient.getStylistId();
      }
    }

}
