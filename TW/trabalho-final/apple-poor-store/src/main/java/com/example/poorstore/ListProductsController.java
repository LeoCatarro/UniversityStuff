package com.example.poorstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
public class ListProductsController {

    private static final Logger log = LoggerFactory.getLogger(ListProductsController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService service;

    @GetMapping("/homepage")
    public String listProducts(Model model)
    {
        List<Product> productList = (List<Product>) productRepository.findAll();

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : productRepository.findAll()) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productList", productList);
        return "homepage";
    }

    @RequestMapping("/search")
    public String listProductsBySearch(Model model, @Param("keyword") String keyword) {
        List<Product> productListBy = (List<Product>) productRepository.search(keyword);

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : productListBy) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productListBy", productListBy);
        return "search";
    }

    @RequestMapping("/list-category")
    public String listProductsByCategory(Model model, @Param("category") String category) {

        log.info("LOG:");
        log.info(category);

        List<Product> productListByCategory = (List<Product>) productRepository.findByCategory("Notebooks");

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : productListByCategory) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productListByCategory", productListByCategory);

        return "list-category";
    }

    @RequestMapping("/product/{id}")
    public String getProduct(Model model, @PathVariable long id) {

        Product prod = productRepository.findById(id);

        log.info("Products found with findAll():");
        log.info("-------------------------------");

        log.info(prod.toString());

        log.info("");

        model.addAttribute("prod", prod);

        return "product";
    }
}



