package com.example.restfullapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhraseDTO implements Serializable {
    Integer position;
    Integer length;
    String sentence;
}
