package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Procesador implements Runnable {
    private String csvLink;
    private String csvNombre;

    public Procesador(String csvLink, String csvNombre) {
        this.csvLink = csvLink;
        this.csvNombre = csvNombre;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(csvLink);
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
            int linea = 0;
            while (bf.readLine() != null) {
                linea++;
            }
            bf.close();
            System.out.println(csvNombre + " tiene lineas: " + linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}