package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client Avviato");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Gioco iniziato");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        Scanner scan = new Scanner(System.in);
        System.out.println(in.readLine());
        String giocatore = scan.nextLine();
        out.writeBytes(giocatore + "\n");

        String rispostaServer;
        while (true) {
            rispostaServer = in.readLine();
            System.out.println(rispostaServer);

            if (rispostaServer.equals("=")) {
                System.out.println(in.readLine());
                System.out.println(in.readLine());
                break;
            }

            System.out.println("Inserire il numero");
            String tentativo = scan.nextLine();
            out.writeBytes(tentativo + "\n");

            System.out.println(in.readLine());
            String risposta = scan.nextLine();
            out.writeBytes(risposta + "\n");

            if (risposta.equalsIgnoreCase("s")) {
                s.close();
            }
        }

    }
}