package com.librarSystem.shelves;

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
    int addToReadShelf (@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        return shelvesService.addToReadShelf(userid, bookid);
    }

    @PostMapping("tbr/{userid}/{bookid}")
    public int addToTBR (@PathVariable("userid") int userid, @PathVariable("bookid") int bookid){
        return shelvesService.addToTBR(userid, bookid);
    }

}
