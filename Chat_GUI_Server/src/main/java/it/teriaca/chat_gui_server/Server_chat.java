/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.teriaca.chat_gui_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teria
 */
public class Server_chat {
    
    private static int contatore = 0;
    private  int portNumber = 3000;
    private String nameServer = "Server_Chat_Teriaca-Rocchini";
    ServerSocket ss; 

    public Server_chat() throws IOException {
        this.ss = new ServerSocket(portNumber);
    }
    
    public void startServer(boolean running) throws IOException{
        
        List<ClientHandler> clients = new ArrayList<>();


    System.out.println("Server in ascolto sulla porta 3000");
    //boolean running = true;
    while (running) {
      Socket s = ss.accept();
      this.contatore++;
      System.out.println("Client connesso");
      ClientHandler client = new ClientHandler(s, contatore, clients);
      clients.add(client);
      client.start();
    }
    }
    
    public void stopServer() throws IOException{
        System.out.println("Server chiuso");
        ss.close();
        System.exit(0);
    }
    
    public int getPortNumber() {
        return portNumber;
    }
    public String getNameServer(){
        return nameServer;
    }
    public int getContatore(){
        return contatore;
     }
    
}
