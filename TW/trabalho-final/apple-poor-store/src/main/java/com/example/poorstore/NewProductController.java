package com.example.poorstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewProductController {

    private static final Logger log = LoggerFactory.getLogger(NewProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/new-product")
    public String newProduct(
            @RequestParam(name="productName", required=false, defaultValue="World") String productName,
            @RequestParam(name="price", required=false, defaultValue="") Integer price,
            @RequestParam(name="quantity", required=false, defaultValue="") Integer quantity,
            @RequestParam(name="description", required=false, defaultValue="") String description,
            @RequestParam(name="category", required=false, defaultValue="") String category,
            @RequestParam(name="imgUrl", required=false, defaultValue="") String imgUrl,
            @RequestParam(name="fullDescription", required=false, defaultValue="") String fullDescription,
            Model model)
    {

        productRepository.save(new Product(productName, description, price, quantity, category, imgUrl, fullDescription));

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product aProduct : productRepository.findAll()) {
            log.info(aProduct.toString());
        }
        log.info("");

        model.addAttribute("productName", productName);
        model.addAttribute("description", description);
        model.addAttribute("price", price);
        model.addAttribute("quantity", quantity);
        model.addAttribute("category", category);
        model.addAttribute("imgUrl", imgUrl);
        model.addAttribute("fullDescription", fullDescription);

        return "new-product-view";
    }

    @RequestMapping("remove-product/{id}")
    public String removeProduct(Model model, @PathVariable("id") long id) {

        Product product = productRepository.findById(id);

        log.info("Product found:");
        log.info("-------------------------------");
        log.info(product.toString());

        log.info("");

        productRepository.delete(product);

        return "remove-product";
    }
}