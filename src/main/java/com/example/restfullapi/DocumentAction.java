package com.example.restfullapi;


import com.example.restfullapi.dto.PhraseDTO;

import java.util.List;

public interface DocumentAction {
    List<String> findCompanyMatches(String doc);
    List<PhraseDTO> decomposeSentence(String doc);

    List<PhraseDTO> findDatesNAmounts(String doc);
}
