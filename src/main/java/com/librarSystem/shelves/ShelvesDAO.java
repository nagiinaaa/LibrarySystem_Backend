package com.librarSystem.shelves;

import java.util.List;
import java.util.Optional;

public interface ShelvesDAO {

    List<Shelves> getUsersReadShelf (int userid);
    List<Shelves> getUsersTBR (int userid);

    List<Shelves> checkIfOnReadShelf (int userid, int bookid);
    List<Shelves> checkIfOnTBR (int userid, int bookid);

    Optional<Shelves> selectShelfById (int id);

    int addToReadShelf (int userid, int bookid);
    int addToTBR (int userid, int bookid);

    int removeFromReadShelf (int id);
    int removeFromTBR (int id);


}
