package com.librarSystem.shelves;

import java.util.List;

public interface ShelvesDAO {

    List<Shelves> getUsersReadShelf (int userid);
    List<Shelves> getUsersTBR (int userid);

    List<Shelves> checkIfOnReadShelf (int userid, int bookid);
    List<Shelves> checkIfOnTBR (int userid, int bookid);

    int addToReadShelf (Shelves shelves);
    int addToTBR (Shelves shelves);

    int removeFromReadShelf (int userid, int bookid);
    int removeFromTBR (int userid, int bookid);


}
