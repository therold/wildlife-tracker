import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  private static Map<String, Object> model;

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    model = new HashMap<String, Object>();

    before((request, response) -> {
      model.clear();
    });

    // Home
    get("/", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Animal
    get("/animals", (request, response) -> {
      model.put("regularanimals", RegularAnimal.all());
      model.put("endangeredanimals", EndangeredAnimal.all());
      model.put("template", "templates/animals/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/new", (request, response) -> {
      model.put("template", "templates/animals/new.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/edit/:id", (request, response) -> {
      Animal animal = tryFindAnimal(request.params(":id"));
      if(animal == null) {
        response.redirect("/");
      } else {
        model.put("animal", animal);
      }
      model.put("template", "templates/animals/edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/delete/:id", (request, response) -> {
      Animal animal = tryFindAnimal(request.params(":id"));
      if(animal == null) {
        response.redirect("/");
      } else {
        model.put("animal", animal);
      }
      model.put("template", "templates/animals/delete.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/search", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/:id", (request, response) -> {
      Animal animal = tryFindAnimal(request.params(":id"));
      if(animal == null) {
        response.redirect("/");
      } else {
        model.put("animal", animal);
      }
      model.put("template", "templates/animals/view.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals", (request, response) -> {
      String type = request.queryParams("type");
      String name = request.queryParams("name");
      if(type.equals(EndangeredAnimal.DATABASE_TYPE)) {
        String health = request.queryParams("health");
        double age = Double.parseDouble(request.queryParams("age"));
        EndangeredAnimal animal = new EndangeredAnimal(name, age, health);
        animal.save();
      } else {
        RegularAnimal animal = new RegularAnimal(name);
        animal.save();
      }
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/edit", (request, response) -> {
      int id = tryParseInt(request.queryParams("id"));
      String type = request.queryParams("type");
      String name = request.queryParams("name");
      if(type.equals(EndangeredAnimal.DATABASE_TYPE)) {
        String health = request.queryParams("health");
        double age = Double.parseDouble(request.queryParams("age"));
        EndangeredAnimal animal = EndangeredAnimal.find(id);
        animal.setName(name);
        animal.setHealth(health);
        animal.setAge(age);
        animal.update();
      } else {
        RegularAnimal animal = RegularAnimal.find(id);
        animal.setName(name);
        animal.update();
      }
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/delete", (request, response) -> {
      Animal animal = tryFindAnimal(request.queryParams("animalId"));
      if(animal != null) {
        animal.delete();
      }
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Location
    get("/locations", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/new", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/edit/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/delete/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/search", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations/edit", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations/delete", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Ranger
    get("/rangers", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/new", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/edit/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/delete/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/search", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/rangers", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/rangers/edit", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/rangers/delete", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Sighting
    get("/sightings", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/new", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/edit/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/delete/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/search", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/:id", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/edit", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/delete", (request, response) -> {
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Admin
    get("/admin", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

  private static Animal tryFindAnimal(String id) {
    Integer animalId = tryParseInt(id);
    if(animalId != null && Animal.idExists(animalId)) {
      if(Animal.getAnimalType(animalId).equals(EndangeredAnimal.DATABASE_TYPE)) {
        return EndangeredAnimal.find(animalId);
      } else if (Animal.getAnimalType(animalId).equals(RegularAnimal.DATABASE_TYPE)) {
        return RegularAnimal.find(animalId);
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  private static Integer tryParseInt(String toParse) {
    Integer number = null;
    try {
      number = Integer.parseInt(toParse);
    } catch (NumberFormatException e) {
      System.out.println("Error parsing integer: " + e.getMessage());
    }
    return number;
  }

}
