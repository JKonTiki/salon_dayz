import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("stylist-name");
      String info = request.queryParams("stylist-info");
      Stylist stylist = new Stylist(name, info);
      stylist.save();
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-specific.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());




  }
}
