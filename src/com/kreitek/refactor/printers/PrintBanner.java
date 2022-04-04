package com.kreitek.refactor.printers;

public class PrintBanner {
    private String text;

    public PrintBanner(String text){
        this.text = text;
    }

    public void print(){
        System.out.println("=====================");
        System.out.println(text);
        System.out.println("=====================");
    }
}
