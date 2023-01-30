package com.example.restfullapi.service;

import com.example.restfullapi.FileAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    FileAction fileAction;

    @Value("${company.dict.path:dict.txt}")
    String dictPath;

    @Override
    public List<String> getAll() {
        log.info("Get all companies");
        return fileAction.readLines(new File(dictPath));
    }
}

