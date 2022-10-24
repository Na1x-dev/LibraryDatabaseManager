package com.example.demo.controllers.sale;

import com.example.demo.models.*;
import com.example.demo.services.book.BookService;
import com.example.demo.services.city.CityService;
import com.example.demo.services.client.ClientService;
import com.example.demo.services.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping({"/salesPage/index"})
    public String salesPage(Model model) {
        model.addAttribute("sales", saleService.readAll());
        return "salesPage/index";
    }

    @GetMapping({"/newSalePage/index"})
    public String newSale(Model model) {
        Sale newSale = new Sale();
        model.addAttribute("newSale", newSale);
        return "newSalePage/index";
    }

    @PostMapping({"/newSalePage/index"})
    public String newSale(Model model, @ModelAttribute("newSale") Sale newSale) {
//        saveBook(newSupply.getSupplyDetails().get(0).getBook());
        saveSale(newSale);
        return "redirect:/salesPage/index";
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
        Client someClient = clientService.readByClientNameAndAddress(client.getClientName(), client.getClientAddress());
        if (someClient == null)
            return clientService.create(client);
        else
            return someClient;
    }

    public Book saveBook(Book book) {
        Book someBook = bookService.readByBookTitleAndAuthorAndPublisherAndLanguage(book.getTitle().toLowerCase().trim(), book.getAuthor().getAuthorName().toLowerCase().trim(), book.getPublisher().getPublisherTitle().toLowerCase().trim(), book.getLanguage().getLanguageName().toLowerCase().trim());
        return someBook;
    }

    public Sale saveSale(Sale sale) {
        sale.setBook((saveBook(sale.getBook())));
        sale.setClient((saveClient(sale.getClient())));
        return saleService.create(sale);
    }
}
