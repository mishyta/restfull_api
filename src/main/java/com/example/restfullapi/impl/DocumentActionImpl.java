package com.example.restfullapi.impl;

import com.example.restfullapi.DocumentAction;
import com.example.restfullapi.dto.PhraseDTO;
import com.example.restfullapi.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
@Component
public class DocumentActionImpl implements DocumentAction {

    @Autowired
    CompanyService companyService;

    @Value("${sentence.delimiter.regexp:[.!?]}")
    String delimiterRegex;

    @Value("${date.regexp}")
    String dateRegex;

    @Value("${amount.regexp}")
    String amountRegex;


    @Override
    public List<String> findCompanyMatches(String doc) {
        List<String> all = companyService.getAll();
        log.info("Start search");
        return all.parallelStream()
                   .filter(doc::contains)
                   .collect(Collectors.toList());
    }

    @Override
    public List<PhraseDTO> decomposeSentence(String doc) {
        //TODO think! Need to drop from result one-words sentence?
        return Arrays.stream(doc.trim().split(delimiterRegex)).parallel()
                .filter(s -> s.length() > 1)
                .map(sentence -> getPhraseData(doc, sentence))
                .collect(Collectors.toList());
    }

    @Override
    public List<PhraseDTO> findDatesNAmounts(String doc) {
        List<String> matches = getMatches(doc, dateRegex);
        matches.addAll(getMatches(doc, amountRegex));
        return matches.parallelStream()
                .map(phrase -> getPhraseData(doc, phrase))
                .collect(Collectors.toList());
    }

    private List<String> getMatches(String document, String regex){
        Pattern pattern = Pattern.compile(regex);
        List<String> list = new ArrayList<>();
        Matcher matcher = pattern.matcher(document);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    private PhraseDTO getPhraseData(String text, String sentence) {
        PhraseDTO sentenceDTO = new PhraseDTO();
        sentenceDTO.setSentence(sentence.trim().replaceAll(" +", " "));
        sentenceDTO.setPosition(text.indexOf(sentence));
        sentenceDTO.setLength(sentence.length());
        return sentenceDTO;
    }
}
