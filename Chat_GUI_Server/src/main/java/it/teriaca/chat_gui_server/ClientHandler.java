/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.teriaca.chat_gui_server;

/**
 *
 * @author teria
 */
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static java.lang.constant.ConstantDescs.NULL;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

    private Socket s;
    private String username;
    private String time;
    private String ipA;
    private ServerSocket ss;

    public Socket getS() {
        return s;
    }

    private PrintWriter pr = null;
    private BufferedReader br = null;

    private int c;
    private String comando;
    private String output;
    static String nomeServer = "Server_Teriaca";
    private ArrayList<ClientHandler> clients;
    ObjectMapper objectMapper = new ObjectMapper();
    private Server_chat sChat;

    // contatore = contatore+1;
    public ClientHandler(Socket s, int c, String name, String time, String ip, ServerSocket ss, ArrayList x) throws IOException {
        //this.clients = x;
        this.s = s;
        this.comando = "";
        this.output = "";
        this.c = c;
        this.clients = x;
        this.time = time;
        this.ipA = ip;
        this.username = name;
        this.ss = ss;
        this.sChat = new Server_chat();
        try {
            pr = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSs() {
        return ss;
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

    @Override
    public void run() {

        try {
            //contatore++;
            Message message;

            String name = br.readLine();
            message = objectMapper.readValue(name, Message.class);
            this.username = message.getSender();

            for (int i = 0; i < clients.size(); i++) {
                Message userConnect = new Message("Utente Connesso", clients.get(i).getUsername());
                String uC = objectMapper.writeValueAsString(userConnect);
                sendToAll(uC, "server", "");
            }

            for (;;) {

                comando = br.readLine();

                String body = "";
                int pos = 0;
                message = objectMapper.readValue(comando, Message.class);
                String json = objectMapper.writeValueAsString(message);
                message.setTag(String.valueOf(message.getBody().charAt(0)));

                switch (message.getTag()) {

                    case "@" -> {
                        String receiver = message.getBody().split(" ")[0];
                        receiver = receiver.replace("@", "");

                        for (int i = 0; i < 50; i++) {
                            if (message.getBody().charAt(i + 1) == ' ') {
                                pos = i + 1;
                                break;
                            }
                        }
                        for (int i = pos; i < message.getBody().length(); i++) {
                            body = body + message.getBody().charAt(i);
                        }
                        body = body.replaceAll("^.", "");

                        message.setBody(body);
                        message.setTag("@");
                        String msg = objectMapper.writeValueAsString(message);

                        sendToPrivate(msg, receiver, message.getSender());

                    }

                    case "/" -> {
                        String receiver = message.getBody().split(" ")[0];
                        receiver = receiver.replace("/", "");
                        //System.out.println(receiver);

                        switch (receiver) {
                            case "list" ->
                                listUsers(message.getSender());

                            case "close" -> {

                                Message userConnect = new Message("Utente Disconesso", this.getUsername());
                                System.out.println(getUsername());
                                String uC = objectMapper.writeValueAsString(userConnect);
                                sendToAll(uC, "server", "u");

                                Message msgerror = new Message("close", "Server", username);
                                String msgE;
                                msgE = objectMapper.writeValueAsString(msgerror);
                                clients.get(researchUser(message.getSender())).pr.println(msgE);
                                this.s.close();
                                aggiornaConnessione();
                            }

                            default -> {
                                Message msgerror = new Message("Comando non riconosciuto", "Server");
                                String msgE;
                                msgE = objectMapper.writeValueAsString(msgerror);
                                clients.get(researchUser(message.getSender())).pr.println(msgE);
                            }
                        }

                    }

                    default ->
                        sendToAll(json, message.getSender(), "");
                }

            }
        } catch (IOException e) {
        }

    }

    /**
     * @param msg
     */
    private void sendToAll(String msg, String sender, String urg) {
        if (urg.equals("")) {
            for (int i = 0; i < clients.size(); i++) {

                if (clients.get(i).getUsername().equals(sender)) {
                    //client che invia MSB
                } else {
                    clients.get(i).pr.println(msg);
                }
            }
        } else {
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).pr.println(msg);
            }
        }

    }

    private void listUsers(String sender) throws JsonProcessingException, IOException {
        aggiornaConnessione();
        String listaUsers = "### LISTA UTENTI CONNESSI ###" + "\n";
        String lista;
        for (int i = 0; i < clients.size(); i++) {
            if (!clients.get(i).getS().isClosed()) {
                if (!clients.get(i).getUsername().equals(sender)) {
                    listaUsers = listaUsers + "@" + clients.get(i).getUsername() + "\n";
                } else {
                    listaUsers = listaUsers + "@" + clients.get(i).getUsername() + " (TU) " + "\n";
                }
            }
        }
        int index = researchUser(sender);
        Message list = new Message(listaUsers, "Server");
        lista = objectMapper.writeValueAsString(list);
        clients.get(index).pr.println(lista);
    }

    private void sendToPrivate(String msg, String receiver, String sender) {

        int pos = researchUser(receiver);
        if (pos == -1) {
            Message msgerror = new Message("Utente non trovato", "Server");
            String msgE;
            try {
                msgE = objectMapper.writeValueAsString(msgerror);
                clients.get(researchUser(sender)).pr.println(msgE);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(ClientHandler.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            clients.get(pos).pr.println(msg);
        }
    }

    private int researchUser(String user) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUsername().equals(user)) {
                return i;
            }
        }
        return -1;
    }

    private void aggiornaConnessione() throws IOException {
        for (int i = 0; i < clients.size(); i++) {
            //System.out.println(clients.get(i).getUsername());
            if (clients.get(i).getS().isClosed()) {
                //System.out.println(clients.get(i).getUsername() + " DISCONNESSO");
                clients.get(i).getS().close();
                clients.remove(i);
            }
        }
    }
}
