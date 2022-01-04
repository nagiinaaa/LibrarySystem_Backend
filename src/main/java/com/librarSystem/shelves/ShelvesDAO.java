package com.librarSystem.shelves;

import java.util.List;

public interface ShelvesDAO {

    List<Shelves> getUsersReadShelf (int userid);
    List<Shelves> getUsersTBR (int userid);

    List<Shelves> checkIfOnReadShelf (int userid, int bookid);
    List<Shelves> checkIfOnTBR (int userid, int bookid);

    int addToReadShelf (int userid, int bookid);
    int addToTBR (int userid, int bookid);

    int removeFromReadShelf (int userid, int bookid);
    int removeFromTBR (int userid, int bookid);


}
