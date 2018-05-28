import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Stylists{
    private String stylist_fname;
    private String stylist_lname;
    private String specialty;
    private int id;

    public Stylists(String stylist_fname, String stylist_lname, String specialty){
        this.stylist_fname = stylist_fname;
        this.stylist_lname = stylist_lname;
        this.specialty = specialty;
    }

    public String getStylistFirstname(){
        return stylist_fname;
    }

    public String getStylistLastname(){
        return stylist_lname;
    }

    public int getId(){
        return id;
    }

    public String getSpecialty(){
        return specialty;
    }

    public static List<Stylists> all() {
        String sql = "SELECT id, stylist_fname,stylist_lname,specialty FROM stylists";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Stylists.class);
        }
      }

      public static Stylists find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylists where id=:id";
          Stylists stylists = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Stylists.class);
          return stylists;
        }
      }

      public List<Clients> getClients() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM clients where id=:id";
          return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Clients.class);
        }
      }

      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO stylists(stylist_fname,stylist_lname,specialty) VALUES (:stylist_fname,:stylist_lname,:specialty)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("stylist_fname", this.stylist_fname)
            .addParameter("stylist_lname", this.stylist_lname)
            .addParameter("specialty", this.specialty)
            .executeUpdate()
            .getKey();
        }
      }
      public void deleteStylist() {
       try(Connection con = DB.sql2o.open()) {
       String sql = "DELETE FROM stylists WHERE id = :id;";
       con.createQuery(sql)
         .addParameter("id", id)
         .executeUpdate();
       }
}
}