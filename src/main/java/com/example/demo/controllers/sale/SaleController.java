package com.example.demo.controllers.sale;

import com.example.demo.models.*;
import com.example.demo.services.book.BookService;
import com.example.demo.services.city.CityService;
import com.example.demo.services.client.ClientService;
import com.example.demo.services.sale.SaleService;
import com.example.demo.services.supplyDetail.SupplyDetailService;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class SaleController {
    @Autowired
    SaleService saleService;
    @Autowired
    CityService cityService;
    @Autowired
    ClientService clientService;
    @Autowired
    BookService bookService;
    @Autowired
    SupplyDetailService supplyDetailService;
    @Autowired
    UserService userService;

    @GetMapping({"/salesPage/index"})
    public String salesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("sales", saleService.readAll());
        return "salesPage/index";
    }

    @GetMapping({"/newSalePage/index"})
    public String newSale(Model model, Principal user) {
        Sale newSale = new Sale();
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newSale", newSale);
        return "newSalePage/index";
    }

    @PostMapping({"/newSalePage/index"})
    public String newSale(Model model, @ModelAttribute("newSale") Sale newSale) {
//        saveBook(newSupply.getSupplyDetails().get(0).getBook());
        if (sellBooks(newSale, model)) {
            saveSale(newSale);
            return "redirect:/salesPage/index";
        } else {
            return "/newSalePage/index";
        }
    }


    public City saveCity(City city) {
        City someCity = cityService.readByCityTitle(city.getTitle());
        if (someCity == null)
            return cityService.create(city);
        else
            return someCity;
    }

    public Client saveClient(Client client) {
        client.setCity((saveCity(client.getCity())));
        Client someClient = clientService.readByClientNameAndAddress(client.getClientName(), client.getClientAddress(), client.getCity().getTitle(), client.getEmail());
        if (someClient == null)
            return clientService.create(client);
        else {
            //clientService.update(client.getClientId(), client);
            return someClient;
        }
    }

    public Book saveBook(Book book) {
        return bookService.readByBookTitleAndAuthorAndPublisherAndLanguage(book.getTitle().toLowerCase().trim(), book.getAuthor().getAuthorName().toLowerCase().trim(), book.getPublisher().getPublisherTitle().toLowerCase().trim(), book.getLanguage().getLanguageName().toLowerCase().trim());
    }

    private Boolean sellBooks(Sale sale, Model model) {
        Book book = bookService.readByBookTitleAndAuthorAndPublisherAndLanguage(sale.getBook().getTitle().toLowerCase().trim(), sale.getBook().getAuthor().getAuthorName().toLowerCase().trim(), sale.getBook().getPublisher().getPublisherTitle().toLowerCase().trim(), sale.getBook().getLanguage().getLanguageName().toLowerCase().trim());
        if (book == null) {
            model.addAttribute("bookError", "Такой книги нет в магазине");
            return false;
        }
        if (book.getBooksAmount() >= sale.getAmount()) {
            int j = 0;
            SupplyDetail supplyDetail = book.getSupplyDetails().get(j);
            for (int i = 0; i < sale.getAmount(); i++) {
                supplyDetail.setAmount(supplyDetail.getAmount() - 1);
                if (supplyDetail.getAmount() < 0) {
                    j++;
                    supplyDetailService.update(supplyDetail.getSupplyDetailId(), supplyDetail);
                    supplyDetail = book.getSupplyDetails().get(j);
                }
            }
            return true;
        } else {
            model.addAttribute("bookError", "Такого количества данных книг нет на складе");
            return false;
        }
    }

    public Sale saveSale(Sale sale) {
        sale.setBook((saveBook(sale.getBook())));
        sale.setClient((saveClient(sale.getClient())));
        sale.setPrice(sale.getBook().getBookPrice());
        return saleService.create(sale);
    }
}
