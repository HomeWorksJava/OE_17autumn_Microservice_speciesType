package hu.nik.controller;

import hu.nik.model.SpeciesType;
import hu.nik.service.SpeciesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// @CrossOrigin(origins = "*")
@CrossOrigin
@Controller
public class SpeciesTypeController {


    /*
    GET /all minden bejegyz�s rendezve
    GET /{id} egy rekordot add vissza, ahol az id egy String param�ter (tartalma lehet sz�m is) a rekord bels� azonos�t�j�val.
    GET /{tol}/{db} rendezett lista elemei tol-t�l db elemsz�mban
    DELETE /{id} torl�s
    POST form parameterek felvitel
    PUT /{id} form parameterek m�dost�s
     */

    @Autowired
    private SpeciesTypeService speciesTypeService;


    @RequestMapping("/")
    @ResponseBody
    public String resourceTypeWelcome() {
        return "Welcome to the Species Type Microservice!";
    }

    @RequestMapping("/speciestypes")
    @ResponseBody
    public List<SpeciesType> getSpeciesTypes() {
        //response.setHeader("Access-Control-Allow-Origin", "*");
        return speciesTypeService.getSpeciesTypes();
    }

    @RequestMapping(value = "/speciestypes/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SpeciesType getSpeciesType(@PathVariable("id") Long id) {
        return speciesTypeService.getSpeciesType(id);
    }

    @RequestMapping(value = "/postspeciestypes", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public SpeciesType postSpeciesType(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        return speciesTypeService.createSpeciesType(name, description);
    }

    @RequestMapping(value = "/deletespeciestypes/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteSpeciesType(@PathVariable("id") Long id) {
        System.out.println("Deleting " + id + " species type");
        if (speciesTypeService.deleteSpeciesType(id)) {
            return "Species type with the ID " + id + " is deleted.";
        }
        return "Species type with ID " + id + " not found.";
    }

}
