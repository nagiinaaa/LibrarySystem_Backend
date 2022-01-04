package com.librarSystem.shelves;

import com.librarSystem.books.BooksService;
import com.librarSystem.exception.ResourceNotFound;
import com.librarSystem.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesService {

    private ShelvesDAO shelvesDAO;
    private UsersService usersService;
    private BooksService booksService;

    @Autowired
    public ShelvesService(ShelvesDAO shelvesDAO, UsersService usersService, BooksService booksService) {
        this.shelvesDAO = shelvesDAO;
        this.usersService = usersService;
        this.booksService = booksService;
    }

    public List<Shelves> getUsersReadShelf (int userid) {
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if(shelvesDAO.getUsersReadShelf(userid).isEmpty()){
            throw new ResourceNotFound("user has no books on their read shelf");
        }
        return shelvesDAO.getUsersReadShelf(userid);
    }

    public List<Shelves> getUsersTBR (int userid){
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if(shelvesDAO.getUsersTBR(userid).isEmpty()){
            throw new ResourceNotFound("user has no books on their to be read shelf");
        }
        return shelvesDAO.getUsersTBR(userid);
    }

    public List<Shelves> checkIfOnReadShelf (int userid, int bookid){
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if (booksService.getBooksById(bookid).isEmpty()){
            throw new ResourceNotFound("no books with the id " +bookid+ " found");
        }
        else if (shelvesDAO.checkIfOnReadShelf(userid, bookid).isEmpty()){
            throw new ResourceNotFound("user " +userid+ " hasn't read the book with the id " +bookid);
        }
        return shelvesDAO.checkIfOnReadShelf(userid, bookid);
    }

    public List<Shelves> checkIfOnTBR (int userid, int bookid) {
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if (booksService.getBooksById(bookid).isEmpty()){
            throw new ResourceNotFound("no books with the id " +bookid+ " found");
        }
        else if (shelvesDAO.checkIfOnTBR(userid, bookid).isEmpty()){
            throw new ResourceNotFound("book with the id " +bookid+ " isn't on user " +userid+ "'s to be read shelf");
        }
        return shelvesDAO.checkIfOnTBR(userid, bookid);
    }


    public int addToReadShelf (int userid, int bookid){
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if (booksService.getBooksById(bookid).isEmpty()){
            throw new ResourceNotFound("no books with the id " +bookid+ " found");
        } else if (shelvesDAO.checkIfOnReadShelf(userid, bookid).isEmpty()){
            throw new ResourceNotFound("book is already on read shelf");
        }
        return shelvesDAO.addToReadShelf(userid, bookid);
    }


    public int addToTBR (int userid, int bookid){
        if(usersService.selectUserById(userid).isEmpty()){
            throw new ResourceNotFound("user with id " +userid+ " doesn't exist");
        }
        else if (booksService.getBooksById(bookid).isEmpty()){
            throw new ResourceNotFound("no books with the id " +bookid+ " found");
        } else if (shelvesDAO.checkIfOnReadShelf(userid, bookid).isEmpty()){
            throw new ResourceNotFound("book is already on to be read shelf");
        }
        return shelvesDAO.addToTBR(userid, bookid);
        
    }

}
