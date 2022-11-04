package com.example.demo.controllers.main;

import com.example.demo.models.*;
import com.example.demo.services.author.AuthorService;
import com.example.demo.services.book.BookService;
import com.example.demo.services.client.ClientService;
import com.example.demo.services.genre.GenreService;
import com.example.demo.services.isbn.IsbnService;
import com.example.demo.services.language.LanguageService;
import com.example.demo.services.publisher.PublisherService;
import com.example.demo.services.sale.SaleService;
import com.example.demo.services.supplier.SupplierService;
import com.example.demo.services.supply.SupplyService;
import com.example.demo.services.supplyDetail.SupplyDetailService;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    Supply newSupply;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    ClientService clientService;
    @Autowired
    SupplyService supplyService;

    @Autowired
    AuthorService authorService;
    @Autowired
    GenreService genreService;
    @Autowired
    IsbnService isbnService;
    @Autowired
    LanguageService languageService;
    @Autowired
    PublisherService publisherService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupplyDetailService supplyDetailService;

    @GetMapping({"/mainPage/index"})
    public String mainPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("updateBook", new Book());
        model.addAttribute("books", bookService.readAll());
        return "mainPage/index";
    }

    @GetMapping({"/clientsPage/index"})
    public String clientsPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("clients", clientService.readAll());
        return "clientsPage/index";
    }

    @GetMapping({"/suppliesPage/index"})
    public String suppliesPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("supplies", supplyService.readAll());
        return "suppliesPage/index";
    }

    @GetMapping({"/newSupplyPage/index"})
    public String newSupply(Model model, Principal user) {
        if (newSupply == null) {
            newSupply = new Supply();
        }
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newSupply", newSupply);
        return "newSupplyPage/index";
    }

    @PostMapping({"/newSupplyPage/index"})
    public String newSupply(Model model, @ModelAttribute("newSupply") Supply newSupply, Principal user) {
        this.newSupply = newSupply;
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        saveSupply(this.newSupply);
        return "redirect:/mainPage/index";
    }

    @GetMapping({"/newSupplyPage/index/addBook"})
    public String addBookToSupply(Model model, Principal user) {
        System.out.println(newSupply);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        newSupply.getSupplyDetails().add(new SupplyDetail());
        model.addAttribute("newSupply", newSupply);
        return "redirect:/newSupplyPage/index";
    }

    @GetMapping({"/newSupplyPage/index/removeBook"})
    public String removeBookFromSupply(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        if (newSupply.getSupplyDetails().size() > 1) {
            newSupply.getSupplyDetails().remove(newSupply.getSupplyDetails().size() - 1);
        }
        model.addAttribute("newSupply", newSupply);
        return "redirect:/newSupplyPage/index";
    }

    @PostMapping("/mainPage/index/update/{id}")
    public String updateBook(Model model, @ModelAttribute("updateBook") Book updateBook, Principal user, @PathVariable("id") Long bookId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        Book book = bookService.readById(bookId);
        updateBook(book, updateBook);
//        updateBook.setBookId(bookId);
        bookService.update(book, bookId);
        return "redirect:/mainPage/index";
    }

    @GetMapping("/mainPage/index/delete/{id}")
    public String deleteBook(Model model, Principal user, @PathVariable("id") Long userId) {
        bookService.delete(userId);
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        return "redirect:/mainPage/index";
    }

    public Publisher savePublisher(Publisher publisher) {
        Publisher somePublisher = publisherService.readByPublisherTitle(publisher.getPublisherTitle());
        if (somePublisher == null)
            return publisherService.create(publisher);
        else
            return somePublisher;
    }

    public Author saveAuthor(Author author) {
        Author someAuthor = authorService.readByAuthorName(author.getAuthorName());
        if (someAuthor == null)
            return authorService.create(author);
        else
            return someAuthor;
    }

    public Genre saveGenre(Genre genre) {
        Genre someGenre = genreService.readByGenreName(genre.getGenreName());
        if (someGenre == null)
            return genreService.create(genre);
        else
            return someGenre;
    }

    public Isbn saveIsbn(Book book) {
        book.getIsbn().generateIsbnNumber(book);
        Isbn someIsbn = isbnService.readByIsbnNumber(book.getIsbn().getIsbnNumber());
        if (someIsbn == null)
            return isbnService.create(book.getIsbn());
        else
            return someIsbn;
    }

    public Language saveLanguage(Language language) {
        Language someLanguage = languageService.readByLanguageName(language.getLanguageName());
        if (someLanguage == null)
            return languageService.create(language);
        else
            return someLanguage;
    }

    public void updateBook(Book book, Book updateBook){
        book.setTitle(updateBook.getTitle());
        book.setAuthor(saveAuthor(updateBook.getAuthor()));
        book.setGenre(saveGenre(updateBook.getGenre()));
        book.setIsbn(saveIsbn(updateBook));
        book.setLanguage(saveLanguage(updateBook.getLanguage()));
        book.setPublisher(savePublisher(updateBook.getPublisher()));
    }

    public Book saveBook(Book book) {
        book.setAuthor(saveAuthor(book.getAuthor()));
        book.setGenre(saveGenre(book.getGenre()));
        book.setIsbn(saveIsbn(book));
        book.setLanguage(saveLanguage(book.getLanguage()));
        book.setPublisher(savePublisher(book.getPublisher()));
        Book someBook = bookService.readByBookTitle(book.getTitle());
        if (someBook == null || !someBook.equals(book))
            return bookService.create(book);
        else
            return someBook;
    }

    public void saveSupplyDetail(Supply supply) {
        List<SupplyDetail> supplyDetails = supply.getSupplyDetails();
        for (int i = 0; i < supplyDetails.size(); i++) {
            supplyDetails.get(i).setSupply(supply);
            supplyDetails.get(i).setBook(saveBook(supplyDetails.get(i).getBook()));
            supplyDetails.set(i, supplyDetailService.create(supplyDetails.get(i)));
        }
    }

    public Supplier saveSupplier(Supplier supplier) {
        Supplier someSupplier = supplierService.readBySupplierName(supplier.getSupplierName());
        if (someSupplier == null)
            return supplierService.create(supplier);
        else
            return someSupplier;
    }

    public void saveSupply(Supply supply) {
        supply.setSupplier(saveSupplier(supply.getSupplier()));
        saveSupplyDetail(supplyService.create(supply));
    }
}
