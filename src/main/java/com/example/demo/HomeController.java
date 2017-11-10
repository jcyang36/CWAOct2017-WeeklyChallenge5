package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String showDefault(Model model){
        model.addAttribute("pets", petRepository.findAll());
    return "home";
    }

    @RequestMapping("/list")
    public String listPets(Model model){
        model.addAttribute("pets", petRepository.findAll());
        return "list";
    }

    @RequestMapping("/home")
    public String showHome(Model model){
        model.addAttribute("pets", petRepository.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String petForm(Model model){
        Pet pet = new Pet();
        pet.setStatus("lost");
        model.addAttribute("pet", pet);

        return "petform";
    }


    @PostMapping("/process")
    public String processForm(@Valid Pet pet, BindingResult result)
    {
        if(result.hasErrors()){
            return "petform";
        }
        petRepository.save(pet);
        return "redirect:/list";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepository.findOne(id));
        return "petform";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        petRepository.delete(id);
        return "redirect:/";
    }
}
