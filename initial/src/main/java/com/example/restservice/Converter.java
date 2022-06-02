package com.example.restservice;

public class Converter {

    private final long id;
    private final String algo;
    private final String textFileInput;

    public Converter(long id, String algo, String content) {
        this.id = id;
        this.algo = algo;
        this.textFileInput = content;
    }

    public long getId() {
        return id;
    }

    public String getAlgo() { return algo; }

    public String getContent() {
        return textFileInput;
    }

}