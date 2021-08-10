package com.example.springboot_security_bookedition;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepo.findByUsername(username));
        return "secure";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/processregister")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }else{
            model.addAttribute("user", user);
            model.addAttribute("message", "New user account created");
            user.setEnabled(true);
            userRepo.save(user);

            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepo.save(role);
        }
        return "index";
    }
    @RequestMapping("/")
    public String listActors(){
        return "index";
    }


//    @PostMapping("/add")
//    public String processActor(@ModelAttribute Actor actor,
//                               @RequestParam("file") MultipartFile file){
//        if(file.isEmpty()){
//            return "redirect:/add";
//        }
//        try{
//            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
//            actor.setPhoto(uploadResult.get("url").toString());
//            actorRepo.save(actor);
//        }catch(IOException e){
//            e.printStackTrace();
//            return "redirect:/add";
//        }
//        return "redirect:/";
//    }

    @RequestMapping("/login")
    public String login(){return "login";}

    @RequestMapping("/admin")
    public String admin(){return "admin";}

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true";
    }
}
