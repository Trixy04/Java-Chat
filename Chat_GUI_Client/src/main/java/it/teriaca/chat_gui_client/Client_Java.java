/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package it.teriaca.chat_gui_client;

/**
 *
 * @author teria
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_Java {

    private Gui_chat chat;
    private static String fine = "fine";
    
    public Client_Java() {
        this.chat = new Gui_chat();
    }

    public void connection(String ipAddress, int portNumber) throws Exception{
        
        Socket s = new Socket(ipAddress, portNumber);
        ServerConnection serverConn = new ServerConnection(s);
        
        this.chat.setVisible(true);
        
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Eccomi");
        System.out.println(br.readLine());
        pr.println(tastiera.readLine());
        System.out.println(br.readLine());

        new Thread(serverConn).start();

        for (;;) {
            String x = tastiera.readLine();
            pr.println(x);
            if (x.equals(fine)) {
                break;
            } else if (x.equals("chiudi")) {
                break;
            }
        }
    }
}
*/