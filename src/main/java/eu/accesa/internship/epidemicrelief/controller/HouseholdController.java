package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdFacade householdFacade;

    @Autowired
    public HouseholdController(HouseholdFacade householdFacade) {
        this.householdFacade = householdFacade;
    }

    @GetMapping
    public String getAllHouseholds(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return "household/householdList";
    }

    @GetMapping("/new")
    public String getNewHouseholdForm() {
        return "household/addHousehold";
    }

    @PostMapping(value = "/save")
    public String addHousehold(@Valid HouseholdData household, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bindingResultMsg", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return "household/addHousehold";
        }
        model.addAttribute("households", householdFacade.getHouseholds());
        householdFacade.addHousehold(household);
        return "redirect:/households";
    }

    @GetMapping("/edit/{id}")
    public String getUpdateHouseholdForm(@PathVariable("id") long id, Model model,@ModelAttribute("error") ArrayList<String> error) {
        Optional<HouseholdData> household = householdFacade.getById(id);
        model.addAttribute("errorSize", Integer.toString(error.size()));
        model.addAttribute("bindingResultMsg", error);
        if (household.isEmpty()) {
            throw new IllegalArgumentException("No household exists for id " + id);
        }

        model.addAttribute("household", household.get());
        return "household/updateHousehold";
    }

    @PostMapping("/update")
    public String updateHousehold(@Valid HouseholdData household, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
        List<String> bindingResultMsg = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", bindingResultMsg);
            return "redirect:/households/edit/" + household.getId();
        }

        householdFacade.updateHousehold(household);
        return "redirect:/households";
    }

    @GetMapping("/delete/{id}")
    public String deleteHousehold(@PathVariable("id") long id) {
        householdFacade.deleteHousehold(id);
        return "redirect:/households";
    }

}
