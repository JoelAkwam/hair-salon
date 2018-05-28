import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
    public static void main(String[] args){

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
            } else {
                port = 4567;
        }
        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, respond) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylist", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String stylist_fname = request.queryParams("stylist_fname");
            String stylist_lname = request.queryParams("stylist_lname");
            String specialty = request.queryParams("specialty");
            Stylists newStylists = new Stylists(stylist_fname, stylist_lname,specialty);
            newStylists.save();
            model.put("template", "templates/success-stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/client", (request, respond)->{
            Map<String, Object> model = new HashMap<String, Object>();
            String client_fname = request.queryParams("client_fname");
            String client_lname = request.queryParams("client_lname");
            String client_style = request.queryParams("client_style");
            Clients newClient = new Clients(client_fname, client_lname, client_style);
            newClient.save();
            model.put("template", "templates/success-client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/list", (request, respond)->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylists.all());
            model.put("clients", Clients.all());
            model.put("template", "templates/list.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/deleteStylist/:id", (request, respond)->{
            Map<String, Object> model = new HashMap<String, Object>();
            Stylists stylist = Stylists.find(Integer.parseInt(request.params(":id")));
            stylist.deleteStylist();
            model.put("stylists", Stylists.all());
            model.put("clients", Clients.all());
            model.put("template", "templates/list.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/deleteClient/:id", (request, respond)->{
            Map<String, Object> model = new HashMap<String, Object>();
            Clients client = Clients.find(Integer.parseInt(request.params(":id")));
            client.deleteClient();
            model.put("stylists", Stylists.all());
            model.put("clients", Clients.all());
            model.put("template", "templates/list.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}