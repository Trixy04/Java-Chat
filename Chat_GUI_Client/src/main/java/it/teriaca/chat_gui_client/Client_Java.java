package it.teriaca.chat_gui_client;

/**
 *
 * @author teria
 *
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client_Java {

    private Gui_chat chat;
    private static String fine = "fine";
    private Socket s;

    public Client_Java(String ipAddress, int portNumber, String user) throws Exception {
        connection(ipAddress, portNumber, user);
    }

    public void connection(String ipAddress, int portNumber, String user) throws IOException, Exception {

        this.s = new Socket(ipAddress, portNumber);
        if(!this.s.isConnected()){
            System.exit(0);
        }
        this.chat = new Gui_chat(ipAddress, portNumber, user, s);
        this.chat.setVisible(true);
        Gui_chat.ServerConnection serverConnect = chat.new ServerConnection(s, user);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        ObjectMapper objectMapper = new ObjectMapper();

        Message name = new Message(user.toLowerCase(), user.toLowerCase());
        String json = objectMapper.writeValueAsString(name);

        pr.println(json);

        new Thread(serverConnect).start();

    }

    public Socket getS() {
        return s;
    }

}
