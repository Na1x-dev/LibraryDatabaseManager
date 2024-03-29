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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
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
        model.addAttribute("showBookList", getBookList());
        model.addAttribute("bookAuthorList", getAuthorNameList(getBookList()));
        model.addAttribute("bookPublisherList", getPublisherNameList(getBookList()));
        model.addAttribute("bookLanguageList", getLanguageNameList(getBookList()));
        for (int i = 0; i < getBookList().size(); i++) {
            System.out.println(i + " - " + getBookList().get(i));
        }
        return "newSalePage/index";
    }

    @PostMapping({"/newSalePage/index"})
    public String newSale(Model model, @ModelAttribute("newSale") Sale newSale, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        saveBook(newSupply.getSupplyDetails().get(0).getBook());
        if (sellBooks(newSale, model)) {
            saveSale(newSale);
            return "redirect:/salesPage/index";
        } else {
            return "/newSalePage/index";
        }
    }

    private List<Book> getBookList() {
        List<Book> books = bookService.readAll();
        for (int i = 0; i < books.size(); ) {
            if (books.get(i).getBooksAmount() < 1) {
                books.remove(i);
            } else {
                i++;
            }
        }
        return books;
    }

    private List<String> getAuthorNameList(List<Book> bookList) {
        List<String> authorName = new ArrayList<>();
        for (Book book : bookList) {
            authorName.add("\"" + book.getAuthor().getAuthorName() + "\"");
        }
        return authorName;
    }

    private List<String> getPublisherNameList(List<Book> bookList) {
        List<String> publisherName = new ArrayList<>();
        for (Book book : bookList) {
            publisherName.add("\"" + book.getPublisher().getPublisherTitle() + "\"");
        }
        return publisherName;
    }

    private List<String> getLanguageNameList(List<Book> bookList) {
        List<String> languageName = new ArrayList<>();
        for (Book book : bookList) {
            languageName.add("\"" + book.getLanguage().getLanguageName() + "\"");
        }
        return languageName;
    }

    @PostMapping("/clientsPage/index/update/{id}")
    public String updateClient(Model model, @ModelAttribute("updateClient") Client updateClient, Principal user, @PathVariable("id") Long clientId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Client client = clientService.readById(clientId);
        updateClient(client, updateClient);
        clientService.update(clientId, client);
        return "redirect:/clientsPage/index";
    }

    @GetMapping("/clientsPage/index/delete/{id}")
    public String deleteClient(Model model, Principal user, @PathVariable("id") Long clientId) {
        clientService.delete(clientId);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        return "redirect:/clientsPage/index";
    }

    public void updateClient(Client client, Client updateClient) {
        client.setClientAddress(updateClient.getClientAddress().toLowerCase().trim());
        client.setClientName(updateClient.getClientName().toLowerCase().trim());
        client.setEmail(updateClient.getEmail().toLowerCase().trim());
        client.setCity(saveCity(updateClient.getCity()));
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
