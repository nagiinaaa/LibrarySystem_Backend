package com.librarSystem.libSystem;

import java.util.List;

public interface LibSystemDAO {

    List<LibSystem> checkAllLoans();
    List<LibSystem> selectLoansByUser(String username);
}
