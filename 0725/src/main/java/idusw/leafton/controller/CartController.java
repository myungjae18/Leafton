package idusw.leafton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/pay")
@Controller
public class CartController {

    @GetMapping(value = "/cart")
    public String goCart(){
        return "pay/cart";
    }

    @GetMapping(value = "/buy")
    public String goBuy(){
        return "pay/buy";
    }

    @GetMapping(value = "/complete")
    public String goComplete(){
        return "pay/complete";
    }




}


