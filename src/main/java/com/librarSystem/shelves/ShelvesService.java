package com.librarSystem.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesService {

    private ShelvesDAO shelvesDAO;

    @Autowired
    public ShelvesService(ShelvesDAO shelvesDAO) {
        this.shelvesDAO = shelvesDAO;
    }

    public List<Shelves> getUsersReadShelf (int userid) {
        return shelvesDAO.getUsersReadShelf(userid);
    }

    public List<Shelves> getUsersTBR (int userid){
        return shelvesDAO.getUsersTBR(userid);
    }

    public List<Shelves> checkIfOnReadShelf (int userid, int bookid){
        return shelvesDAO.checkIfOnReadShelf(userid, bookid);
    }

    public List<Shelves> checkIfOnTBR (int userid, int bookid) {
        return shelvesDAO.checkIfOnTBR(userid, bookid);
    }
}
