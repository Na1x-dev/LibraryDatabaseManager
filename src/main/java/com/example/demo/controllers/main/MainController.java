package com.example.demo.controllers.main;

import com.example.demo.models.*;
import com.example.demo.services.book.BookService;
import com.example.demo.services.client.ClientService;
import com.example.demo.services.sale.SaleService;
import com.example.demo.services.supply.SupplyService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        Supply newSupply = new Supply();
        model.addAttribute("newSupply", newSupply);
        return "newSupplyPage/index";
    }

    @PostMapping({"/newSupplyPage/index"})
    public String newSupply(Model model, @ModelAttribute("newSupply") Supply newSupply) {

        System.out.println(newSupply);
        System.out.println(newSupply.getSupplier().getSupplierName());
        System.out.println(newSupply.getSupplyDate());
        System.out.println(newSupply.getSupplyDetails().get(0).getBook().getAuthor().getAuthorName());
        System.out.println(newSupply.getSupplyDetails().get(0).getAmount());
        System.out.println(newSupply.getSupplyDetails().get(0).getBook().getTitle());
        return "mainPage/index";
    }
}
