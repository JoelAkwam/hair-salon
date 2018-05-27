import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Clients{
    private String client_fname;
    private String client_lname;
    private int client_id;
    private int stylist_id;

    public Clients(String client_fname, String client_lname, int stylist_id){
        this.client_fname = client_fname;
        this.client_lname = client_lname;
        this.stylist_id = stylist_id;
    }

    public String getClientFirstame(){
        return client_fname;
    }

    public String getClientLastname(){
        return client_lname;
    }

    public static List<Clients> all() {
        String sql = "SELECT client_id, client_fname,client_lname,stylist_id FROM clients";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Clients.class);
        }
      }

      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO clients(client_fname, client_lname,stylist_id) VALUES (:client_fname, :client_lname,:stylist_id)";
          this.client_id = (int) con.createQuery(sql, true)
            .addParameter("client_fname", this.client_fname)
            .addParameter("client_lname", this.client_lname)
            .addParameter("stylist_id", this.stylist_id)
            .executeUpdate()
            .getKey();
        }
      }

      public void deleteClient() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients WHERE client_id = :id;";
        con.createQuery(sql)
          .addParameter("id", client_id)
          .executeUpdate();
        }
      }
}