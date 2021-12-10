package com.librarSystem.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "books")
public class BooksController {

    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<Books> getAllBooks(){
        return booksService.getAllBooks();
    }

    @GetMapping("/title/{title}")
    public List<Books> getBooksByTitle(@PathVariable("title") String title){
        return booksService.getBooksByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Books> getBooksByAuthor(@PathVariable("author") String author){
        return booksService.getBooksByAuthor(author);
    }

}
