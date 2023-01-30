package com.example.restfullapi;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileAction {
    List<String> readLines(MultipartFile file);

    List<String> readLines(File file);

    String readSource(MultipartFile file);

    String readSource(File file);


}
