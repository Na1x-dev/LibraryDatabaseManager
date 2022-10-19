package com.example.demo.controllers.main;

import com.example.demo.services.book.BookService;
import com.example.demo.services.client.ClientService;
import com.example.demo.services.sale.SaleService;
import com.example.demo.services.supply.SupplyService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    BookService bookService;
    @Autowired
    ClientService clientService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    SaleService saleService;

    @GetMapping({"/mainPage/index"})
    public String mainPage(Model model) {
        model.addAttribute("books", bookService.readAll());
        return "mainPage/index";
    }

    @GetMapping({"/clientsPage/index"})
    public String clientsPage(Model model) {
        model.addAttribute("clients", clientService.readAll());
        return "clientsPage/index";
    }

    @GetMapping({"/suppliesPage/index"})
    public String suppliesPage(Model model) {
        model.addAttribute("supplies", supplyService.readAll());
        return "suppliesPage/index";
    }

    @GetMapping({"/salesPage/index"})
    public String salesPage(Model model) {
        model.addAttribute("sales", saleService.readAll());
        return "salesPage/index";
    }

    @GetMapping({"/sqlPage/index"})
    public String sqlPage(Model model) {
        model.addAttribute("books", bookService.readAll());
        return "sqlPage/index";
    }

    @GetMapping({"/newSupplyPage/index"})
    public String newSupply(Model model) {
//        model.addAttribute("books", bookService.readAll());
        return "newSupplyPage/index";
    }
}
