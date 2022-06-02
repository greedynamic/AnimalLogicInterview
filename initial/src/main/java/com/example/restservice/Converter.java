package com.example.restservice;

public class Converter {

    private final long id;
    private final String algo;
    private final String inputData;
    private final String testStr;
    int ngrams = 3;

    public Converter(long id, String algo, String source, String str) {
        this.id = id;
        this.algo = algo;
        this.inputData = source;
        this.testStr = str;
    }

    public long getId() { return id; }

    public String getModel() { return algo; }

    public String getSource() { return inputData; }

    public String getNextChar() {
        MarkovChain nextCharGenerator = new MarkovChain(inputData, ngrams);
        String nextChar = nextCharGenerator.generateNextChar(testStr);
        String result = String.format("The next possible character for %s: ", nextChar);
        return result;
    }

}