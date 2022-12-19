/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.teriaca.chat_gui_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author teria
 */
public class Server_chat {

    public static int contatore = 0;
    public int portNumber = 3000;
    public String nameServer = "Server_Chat_Teriaca-Rocchini";
    public String name;
    public String ipAddress;
    public ServerSocket ss;
    public ArrayList<ClientHandler> clients;

    public Server_chat() throws IOException {

    }

    public void startServer(boolean running) throws IOException {
        this.ss = new ServerSocket(3000);
        this.clients = new ArrayList<ClientHandler>();

        System.out.println("Server in ascolto sulla porta 3000");
        //boolean running = true;
        while (running) {
            Socket s = ss.accept();
            this.contatore++;

            InetSocketAddress socketAddress = (InetSocketAddress) s.getRemoteSocketAddress();
            this.ipAddress = socketAddress.getAddress().getHostAddress();

            DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy 'alle' HH:mm:ss");

            String date = dateFormat.format(Calendar.getInstance().getTime());

            ClientHandler client = new ClientHandler(s, contatore, name, date, ipAddress, ss, clients);
            clients.add(client);
            client.start();
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public void stopServer() throws IOException {
        System.out.println("Server chiuso");
        ss.close();
        System.exit(0);
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getNameServer() {
        return nameServer;
    }

    public int getContatore() {
        return contatore;
    }

    public void deleteUser(ClientHandler x) {
        clients.remove(x);
    }

}
