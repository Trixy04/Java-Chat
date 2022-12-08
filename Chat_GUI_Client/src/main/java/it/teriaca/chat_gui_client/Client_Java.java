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

    public Client_Java() {
        //this.chat = new Gui_chat();
    }

    public void connection(String ipAddress, int portNumber, String user) throws Exception {

        this.s = new Socket(ipAddress, portNumber);
        ServerConnection serverConn = new ServerConnection(s);
        //this.chat.setVisible(true);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println(user);

        new Thread(serverConn).start();
        
    }

    public Socket getS() {
        return s;
    }


}
