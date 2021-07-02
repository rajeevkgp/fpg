package com.floating.group.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CryptoDataReader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/300072873/Downloads/FTX_Futures_BTCPERP_minute.csv"));
        sc.useDelimiter(",");
        while (sc.hasNext()) {

        }
    }
}
