package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static final String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
        invokeCharacterListServlet("HelloWorld!");

        invokeGraphMatrixServlet(5, 4);
    }

    private static void invokeCharacterListServlet(String input) {
        try {
            URL url = new URL(BASE_URL + "/Servlets/stringToList?input=" + input);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("Character List:");
            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void invokeGraphMatrixServlet(int numVertices, int numEdges) {
        try {
            URL url = new URL(BASE_URL + "/Servlets/adjacencyMatrix?numVertices=" + numVertices + "&numEdges=" + numEdges);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("Adjacency Matrix:");
            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}