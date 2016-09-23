import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Stylist {

  private int id;
  private String name;
  private String info;

  public Stylist(String _name, String _info){
    info = _info;
    name = _name;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getInfo(){
    return info;
  }

  public void setInfo(String _info){
    info = _info;
  }


  public static List<Stylist> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql)
        .executeAndFetch(Stylist.class);
    }
  }

  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM CLIENTS WHERE stylistid=:id";
      return con.createQuery(sql)
        .addParameter("id",this.getId())
        .executeAndFetch(Client.class);
    }
  }

  public static Stylist find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, name, info FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", _id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(info, name) VALUES(:info, :name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("info", this.info)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public void updateInfo() {
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE stylists SET info=:info WHERE id=:id";
        con.createQuery(sql)
          .addParameter("info", this.info)
          .addParameter("id", this.id)
          .executeUpdate();
    }
  }


  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void deleteClients(){
    for(Client client : this.getClients()){
      client.delete();
    }
  }

  @Override
    public boolean equals(Object otherStylist){
      if(!(otherStylist instanceof Stylist)){
        return false;
      }else {
        Stylist newStylist = (Stylist) otherStylist;
        return this.getInfo().equals(newStylist.getInfo()) &&
          this.getId() == newStylist.getId();
      }
    }
}
