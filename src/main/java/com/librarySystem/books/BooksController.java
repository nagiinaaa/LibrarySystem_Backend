package com.librarySystem.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{username}/{id}")
    public void deleteBook (@PathVariable("username") String username, @PathVariable("id") int id){
        booksService.deleteBook(username, id);
    }

    @PutMapping("{username}/{id}")
    public void updateBook (@PathVariable("username") String username, @PathVariable("id") int id,
                            @RequestBody Books books){
        booksService.updateBook(username, id, books);
    }

    @PostMapping("{username}")
    public void addBook(@PathVariable("username") String username, @RequestBody Books books){
        booksService.addBook(username, books);
    }

    @GetMapping("object/{title}/{author}/{bookFormat}")
    public Object checkTotalCopies(@PathVariable("title") String title, @PathVariable("author") String author, @PathVariable("bookFormat") String bookFormat){
        return booksService.checkTotalCopies(title, author, bookFormat);
    }

}
