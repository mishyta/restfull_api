package com.example.restfullapi.resources;

import com.example.restfullapi.dto.PhraseDTO;
import com.example.restfullapi.DocumentAction;
import com.example.restfullapi.FileAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
public class DocumentResource {

    @Autowired
    DocumentAction documentHandler;

    @Autowired
    FileAction fileAction;

    @PostMapping(value = "/companyMatches", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> findCompanyMatches(@RequestParam("file") MultipartFile file) {
        String source = fileAction.readSource(file);
        return new ResponseEntity<>(documentHandler.findCompanyMatches(source), HttpStatus.OK);
    }

    @PostMapping(value = "/sentance", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PhraseDTO>> getSentence(@RequestParam("file") MultipartFile file) {
        String source = fileAction.readSource(file);
        return new ResponseEntity<>(documentHandler.decomposeSentence(source), HttpStatus.OK);
    }

    @PostMapping(value = "/datesNAmounts", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PhraseDTO>> findDatesNAmounts(@RequestParam("file") MultipartFile file) {
        String source = fileAction.readSource(file);
        return new ResponseEntity<>(documentHandler.findDatesNAmounts(source), HttpStatus.OK);
    }

}

