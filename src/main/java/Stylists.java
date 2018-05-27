import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Stylists{
    private String stylist_fname;
    private String stylist_lname;
    private String specialty;
    private int stylist_id;

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
        return stylist_id;
    }

    public String getSpecialty(){
        return specialty;
    }

    public static List<Stylists> all() {
        String sql = "SELECT stylist_id, stylist_fname,stylist_lname,specialty FROM stylists";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Stylists.class);
        }
      }

      public static Stylists find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylists where stylist_id=:stylist_id";
          Stylists stylists = con.createQuery(sql)
            .addParameter("stylist_id", id)
            .executeAndFetchFirst(Stylists.class);
          return stylists;
        }
      }

      public List<Clients> getClients() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM clients where stylist_id=:id";
          return con.createQuery(sql)
            .addParameter("id", this.stylist_id)
            .executeAndFetch(Clients.class);
        }
      }

      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO stylists(stylist_fname,stylist_lname,specialty) VALUES (:stylist_fname,:stylist_lname,:specialty)";
          this.stylist_id = (int) con.createQuery(sql, true)
            .addParameter("stylist_fname", this.stylist_fname)
            .addParameter("stylist_lname", this.stylist_lname)
            .addParameter("specialty", this.specialty)
            .executeUpdate()
            .getKey();
        }
      }
      public void deleteStylist() {
       try(Connection con = DB.sql2o.open()) {
       String sql = "DELETE FROM stylists WHERE stylist_id = :id;";
       con.createQuery(sql)
         .addParameter("id", stylist_id)
         .executeUpdate();
       }
       try(Connection con = DB.sql2o.open()) {
         String sql = "UPDATE clients SET stylist_id = 0 WHERE stylist_id = :id;";
         con.createQuery(sql).addParameter("id", stylist_id).executeUpdate();
         }
       }
}