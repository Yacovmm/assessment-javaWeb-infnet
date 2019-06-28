package com.infnet.infnet.boot;

import com.infnet.infnet.model.Product;
import com.infnet.infnet.model.User;
import com.infnet.infnet.repository.ProductRepository;
import com.infnet.infnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setNome("Yacov Rosenberg");
        user1.setEmail("yacov@yacov.com");
        user1.setSenha("12345");

        User user2 = new User();
        user2.setNome("Iossef Rosenberg");
        user2.setEmail("Iosses@iossi.com");
        user2.setSenha("12345");

        Product product1 = new Product();
        product1.setName("Milki Bar");
        product1.setDescription("Milki bar com chocolate");
        product1.setType("Balas");
        product1.setCategory("Bars");
        product1.setPrice(1.99);

//        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Barra amendoas");
        product2.setDescription("Barra com amendoas");
        product2.setType("Balas");
        product2.setCategory("Barras");
        product2.setPrice(2.99);


        product1.getUsers().addAll(Arrays.asList(user1,user2));
        product2.getUsers().addAll(Arrays.asList(user1,user2));

        user1.getProducts().addAll(Arrays.asList(product1, product2));
        user2.getProducts().addAll(Arrays.asList(product1, product2));

        productRepository.saveAll(Arrays.asList(product1, product2));
        userRepository.saveAll(Arrays.asList(user1, user2));
//        userRepository.save(user2);

//        productRepository.save(product2);
//        productRepository.save(product1);


    }
}
