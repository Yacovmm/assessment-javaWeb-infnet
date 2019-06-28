package com.infnet.infnet.controller;

import com.infnet.infnet.model.Product;
import com.infnet.infnet.model.User;
import com.infnet.infnet.repository.UserRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @RequestMapping(path = "/")
//    public String

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getAllProducts(HttpServletRequest request, Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping(path = "users", method = RequestMethod.POST)
    public String saveUser(User user){
        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login( Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, Model model, User user){
        for(User u: userRepository.findAll()){
            if (u.getEmail().toUpperCase().equals(user.getEmail().toUpperCase())
                    && u.getSenha().toUpperCase().equals(user.getSenha().toUpperCase())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "redirect:/index";

            }
        }
        return "redirect:/";
    }


    @RequestMapping(path = "/")
    public String createProduct(Model model){
        model.addAttribute("user", new User());
        return "cadastroUser";
    }
}
