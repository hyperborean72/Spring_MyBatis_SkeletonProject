package stepanov.andrey.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import stepanov.andrey.service.AppService;
import stepanov.andrey.model.Country;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: a.stepanov
 */

@Controller
public class CountryController {
    static Logger log = Logger.getLogger(CountryController.class);

    @Autowired
    private AppService appService;

    @RequestMapping(value = {"/listAll", "/"}, method = RequestMethod.GET)
    public String populateCountryTable(ModelMap model) {
        List<Country> allCountries =  appService.getAllCountries();
        System.out.println("in CountryController getAllCountries().size = " + allCountries.size());
        log.debug("in CountryController.getAllCountries().size = " + allCountries.size() );
        model.addAttribute("countries", allCountries);
        return "countryList";
    }

    // showing the form for adding a new country
    @RequestMapping(value = "/addCountry", method = RequestMethod.GET)
    public String newCountry(ModelMap model) {
        Country country = new Country();
        model.addAttribute("newcountry", country);
        model.addAttribute("edit", false);
        System.out.println("in CountryController.newCountry(): country = " + country.toString());
        return "registration";
    }

    // a new country form processing
    @RequestMapping(value = "/addCountry", method = RequestMethod.POST)
    public String addCountry(@Valid @ModelAttribute ("newcountry") Country country, BindingResult result, ModelMap model) {
        System.out.println("in CountryController.addCountry(): country = " + country.toString());

        if (result.hasErrors()) {
            return "registration";
        }

        appService.addCountry(country);
        model.addAttribute("success", country.getName() + " uploaded successfully");
        return "success";
    }


    // showing the form for the country editing
    @RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.GET)
    public String editCountry(@PathVariable("id") int id, ModelMap model) {
        Country country = appService.getCountryById(id);
        model.addAttribute("newcountry", country);
        model.addAttribute("edit", true);
        return "registration";
    }


    // submitted editing form processing
    @RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.POST)
    public String updateCountry(@Valid @ModelAttribute ("newcountry") Country country, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        System.out.println("in CountryController.updateCountry(): country = " + country.toString());
        appService.updateCountry(country);
        model.addAttribute("success", country.getName()	+ " updated successfully");
        return "success";
    }


    @RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET)
    public String deleteCountry(@PathVariable("id") int id){
        System.out.println("in CountryController.deleteCountry(): id = " + id);
        appService.deleteCountryById(id);
        return "redirect:/listAll";
    }
}