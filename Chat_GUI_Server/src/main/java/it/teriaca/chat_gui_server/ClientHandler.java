/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.teriaca.chat_gui_server;

/**
 *
 * @author teria
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientHandler extends Thread {

    private Socket s;
    private String username;
    private String time;
    private String ipA;

    public Socket getS() {
        return s;
    }

    private PrintWriter pr = null;
    private BufferedReader br = null;

    private int c;
    private String comando;
    private String output;
    static String nomeServer = "Server_Teriaca";
    private List<ClientHandler> clients;

    // contatore = contatore+1;
    public ClientHandler(Socket s, int c, List<ClientHandler> x, String name, String time, String ip) {
        this.clients = x;
        this.s = s;
        this.comando = "";
        this.output = "";
        this.c = c;
        this.time = time;
        this.ipA = ip;
        this.username = name;
        try {
            pr = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIpA() {
        return ipA;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public void run() {

        try {
            //contatore++;
            ObjectMapper objectMapper = new ObjectMapper();
            Message message;

            String name = br.readLine();
            System.out.println(name);
            this.username = name;

            for (;;) {

                comando = br.readLine();
                message = objectMapper.readValue(comando, Message.class);
                String json = objectMapper.writeValueAsString(message);
                message.setTag(String.valueOf(message.getBody().charAt(0)));

                if (message.getTag().equals("@")) {
                    String receiver = message.getBody().split(" ")[0];
                    receiver = receiver.replace("@", "");
                    String body = message.getBody().split(" ")[1];
                    message.setBody(body);
                    sendToPrivate(json, receiver, message.getSender());

                } else {
                    sendToAll(json, message.getSender());
                }

                /*
                else if(comando.equals("chiudi")){
                    pr.println("Tutte le connessioni saranno chiuse!");
                    sendToAll("@");
                    /*
                    for(int i = 0; i < clients.size(); i++){
                        clients.get(i).getS().close();
                        System.out.println("Connessione con utente n. " + clients.get(i).c + " è stata CHIUSA!");
                    }
                    
                    clients.removeAll(clients);
                    break;
                }else{
                    pr.println("Il comando inserito non è valido");
                
                }
                 */
            }
        } catch (Exception e) {

        }

    }

    /**
     * @param msg
     */
    private void sendToAll(String msg, String sender) {

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUsername().equals(sender)) {
                //client che invia MSB
            } else {
                clients.get(i).pr.println(msg);
            }
        }

    }

    private void sendToPrivate(String msg, String receiver, String sender) {
        boolean trovato = false;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUsername().equals(receiver)) {
                trovato = true;
                clients.get(i).pr.println(msg);
            }
        }
        if (trovato == false) {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getUsername().equals(sender)) {
                    Message msgError = new Message("Utente non trovato", "Server");
                    clients.get(i).pr.println(msgError);
                }
            }
        }
    }
}
