package it.teriaca.chat_gui_client;

/**
 *
 * @author teria
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_Java {

    private Gui_chat chat;
    private static String fine = "fine";
    private Socket s;

    public Client_Java(String ipAddress, int portNumber, String user) throws Exception {
        connection(ipAddress, portNumber, user);
    }

    public void connection(String ipAddress, int portNumber, String user) throws Exception {

        this.s = new Socket(ipAddress, portNumber);
        this.chat = new Gui_chat(ipAddress, portNumber, user, s);
        Gui_chat.ServerConnection serverConnect = chat.new ServerConnection(s);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println(user.toLowerCase());

        new Thread(serverConnect).start();
        
    }

    public Socket getS() {
        return s;
    }


}
