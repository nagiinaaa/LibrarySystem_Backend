package com.librarSystem.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
