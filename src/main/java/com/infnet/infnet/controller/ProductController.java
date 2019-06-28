package com.infnet.infnet.controller;

import com.infnet.infnet.model.Product;
import com.infnet.infnet.model.User;
import com.infnet.infnet.repository.ProductRepository;
import com.infnet.infnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String createProduct(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        model.addAttribute("product", new Product());
        return "edit";
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public String saveProduct(HttpServletRequest request, Product product){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        product.setUsers(Arrays.asList(user));
//        user.setProducts(Arrays.asList(product));

        productRepository.save(product);
//        userRepository.save(user);
        return "redirect:/products";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") int id){
        model.addAttribute("product", productRepository.getOne(id));
        return "edit";
    }

    @RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") int id){

        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
