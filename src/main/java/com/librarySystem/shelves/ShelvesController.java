package com.librarySystem.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "shelves")
public class ShelvesController {

    private ShelvesService shelvesService;

    @Autowired
    public ShelvesController(ShelvesService shelvesService) {
        this.shelvesService = shelvesService;
    }


    @GetMapping("read/{userid}")
    public List<Shelves> getUserReadShelf (@PathVariable("userid") int userid){
        return shelvesService.getUsersReadShelf(userid);
    }

    @GetMapping("tbr/{userid}")
    public  List<Shelves> getUsersTBR (@PathVariable("userid")  int userid){
        return shelvesService.getUsersTBR(userid);
    }


    @PostMapping("read/{userid}/{bookid}")
    public void addToReadShelf (@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        shelvesService.addToReadShelf(userid, bookid);
    }

    @PostMapping("tbr/{userid}/{bookid}")
    public void addToTBR (@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        shelvesService.addToTBR(userid, bookid);
    }

    @DeleteMapping("read/{id}")
    public void removeFromReadShelf (@PathVariable("id") int id){
        shelvesService.removeFromReadShelf(id);
    }

    @DeleteMapping("tbr/{id}")
    public void removeFromTBR (@PathVariable("id") int id){
        shelvesService.removeFromTBR(id);
    }

}
