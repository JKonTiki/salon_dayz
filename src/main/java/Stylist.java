import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Stylist {

  private int id;
  private String info;

  public Stylist(_info){
    info = _info;
  }

  public String getInfo(){
    return info;
  }

  public int getId(){
    return id;
  }

  public static List<Stylist> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM hair_salon";
      return con.createQuery(sql)
        .executeAndFetch(Stylist.class);
    }
  }

  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM CLIENTS WHERE id=:id";
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }

  public static Stylist find(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, info FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(info) VALUES(:info)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("info", this.info)
        .executeUpdate()
        .getKey();
    }
  }


  public void delete(){
    this.deleteClients();
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
