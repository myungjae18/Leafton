package idusw.leafton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/product")
@Controller
public class ProductController {
    //product page mapping
    @GetMapping (value="/product")
    public String goProduct() { return "product/product";}

    //shop page mapping
    @GetMapping(value="/shop")
    public String goShop() { return "product/shop";}
}
