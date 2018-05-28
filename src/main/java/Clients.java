import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Clients{
    private String client_fname;
    private String client_lname;
    private String client_style;
    private int id;

    public Clients(String client_fname, String client_lname, String client_style){
        this.client_fname = client_fname;
        this.client_lname = client_lname;
        this.client_style = client_style;
    }

    public String getClientFirstame(){
        return client_fname;
    }

    public String getClientLastname(){
        return client_lname;
    }

    public String getStyle(){
      return client_style;
    }

    public int getId(){
      return id;
    }

    public static List<Clients> all() {
        String sql = "SELECT id, client_fname,client_lname,client_style FROM clients";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Clients.class);
        }
      }

      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO clients(client_fname, client_lname,client_style) VALUES (:client_fname, :client_lname,:client_style)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("client_fname", this.client_fname)
            .addParameter("client_lname", this.client_lname)
            .addParameter("client_style", this.client_style)
            .executeUpdate()
            .getKey();
        }
      }

      public static Clients find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM clients where id=:id";
          Clients clients = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Clients.class);
          return clients;
        }
      }

      public void deleteClient() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
        }
      }
}