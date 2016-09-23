import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Client {

  private int id;
  private int stylistId;
  private String name;
  private String info;

  public Client(String _info, String _name, int _stylistId) {
    name = _name;
    info = _info;
    stylistId = _stylistId;
  }

  public String getInfo(){
    return info;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }


  public void setInfo(String _info){
    info = _info;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, stylistId, info, name FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, stylistId, info, name FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
      .addParameter("id", _id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients(info, stylistId, name) VALUES(:info, :stylistId, :name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("info", this.info)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public void updateInfo() {
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE clients SET info=:info WHERE id=:id";
        con.createQuery(sql)
          .addParameter("info", this.info)
          .addParameter("id", this.id)
          .executeUpdate();
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
