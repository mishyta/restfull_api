package com.example.restfullapi.impl;

import com.example.restfullapi.FileAction;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileActionImpl implements FileAction {

    @Override
    @SneakyThrows
    public List<String> readLines(MultipartFile file) {
        log.info("Read file: {}", file.getName());
        return readLines(new InputStreamReader(file.getInputStream()));
    }

    @Override
    @SneakyThrows
    public List<String> readLines(File file) {
        log.info("Read file: {}", file.getName());
        return readLines(new FileReader(file));
    }

    @Override
    public String readSource(MultipartFile file) {
        return String.join("", readLines(file));
    }

    @Override
    public String readSource(File file) {
        return String.join("", readLines(file));
    }

    @SneakyThrows
    private List<String> readLines(Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> lines = new ArrayList<>();
        while (reader.ready()) {
            lines.add(bufferedReader.readLine());
        }
        return lines;
    }
}
