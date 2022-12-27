/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package it.teriaca.chat_gui_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import static java.lang.constant.ConstantDescs.NULL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.applet.Applet;
import java.applet.AudioClip;
import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static java.awt.Color.white;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

/**
 *
 * @author teria
 */
public class Gui_chat extends javax.swing.JFrame {

    private static String fine = "fine";
    private static final String erroreIizio = "Manca l'indicazione del destinatario";
    private Client_Java client;
    Socket s;
    PrintWriter pr;
    static BufferedReader in;
    ServerConnection serverConn;
    private ArrayList<User_conn> uCC = new ArrayList();

    /**
     * Creates new form Gui_chat
     *
     * @param ip
     * @param port
     * @param username
     * @throws java.lang.Exception
     */
    public Gui_chat(String ip, int port, String username, Socket s) throws Exception {
        initComponents();
        jPanel2.setLayout(new MigLayout("fillx"));
        jPanel3.setLayout(new MigLayout("fillx"));
        this.s = s;
        this.setTitle("JAVA CHAT - Area Client");
        this.jLabel2.setText(username);
        this.setVisible(false);
        this.setResizable(false);
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        avvioUser("Gruppo - Broadcast", "...");
    }

    private void avvioUser(String nome, String init) {
        User_conn user = new User_conn(nome, "...");
        uCC.add(user);
        this.jPanel3.add(user, "wrap, w 100%");
        this.jPanel3.repaint();
        this.jPanel3.validate();
        //invioRichiestaUtente();
    }

    private void lightMessage(String name) throws InterruptedException {
        for (int i = 0; i < uCC.size(); i++) {
            if (uCC.get(i).getName().equals(name)) {
                uCC.get(i).setBackground(GREEN);

                uCC.get(i).setBackground(WHITE);

            }

        }
    }

    private void removeUser(String nome) {
        System.out.println("EMTRATP");

        for (int i = 0; i < uCC.size(); i++) {
            if (uCC.get(i).getName().equals(nome)) {
                jPanel3.remove(uCC.get(i));
                this.jPanel3.repaint();
                this.jPanel3.validate();
                uCC.remove(i);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nome utente");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("INVIA >");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guide .png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setText("Utenti Connessi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        if (!jTextField1.getText().equals("")) {
            if (jTextField1.getText().equals("/close")) {
                System.out.println("CHIUDO");
            }
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Message message;
                pr = new PrintWriter(s.getOutputStream(), true);

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String text = jTextField1.getText();

                message = new Message(text, this.jLabel2.getText());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                String orario = String.valueOf(dtf.format(LocalDateTime.now()));
                Item_Right item = new Item_Right("Tu" + "\n" + text, orario);
                jPanel2.add(item, "wrap, w 80%, al right");
                jPanel2.repaint();
                jPanel2.revalidate();
                jTextField1.setText("");

                String json = objectMapper.writeValueAsString(message);
                pr.println(json);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(Gui_chat.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(Gui_chat.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        new StarterGuide().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public class ServerConnection extends Applet implements Runnable {

        private Socket s;
        BufferedReader in;
        ObjectMapper objectMapper = new ObjectMapper();
        private Message message = new Message();
        ArrayList<String> arrayUtenti = new ArrayList();

        public ServerConnection(Socket s) throws IOException {
            this.s = s;
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("SS --> " + Rese(jLabel2.getText()));
            if (Rese(jLabel2.getText()) != -1) {
                arrayUtenti.remove(Research(jLabel2.getText()));
                JOptionPane.showMessageDialog(this, "Nome utente già in uso");
                Message chiudo = new Message("?chiudo", jLabel2.getText());
                String json = objectMapper.writeValueAsString(chiudo);
                pr.println(json);
                //wait(1000);
                this.s.close();
            }else{
                addThis();
            }
        }

        private void addThis() {
            arrayUtenti.add(jLabel2.getText());
            System.out.println("aggiunto");
        }

        private void formWindowClosing(java.awt.event.WindowEvent evt) throws IOException, InterruptedException {
            // TODO add your handling code here:
            Message chiudo = new Message("?chiudo", jLabel2.getText());
            String json = objectMapper.writeValueAsString(chiudo);
            pr.println(json);
            wait(1000);
            this.s.close();
        }
        
        private int Rese(String x) {
            for (int i = 0; i < arrayUtenti.size(); i++) {
                if (arrayUtenti.get(i).equals(x)) {
                    return i;
                }
            }
            return -1;
        }

        private int Research(String x) {
            for (int i = 0; i < arrayUtenti.size(); i++) {
                if (arrayUtenti.get(i).equals(message.getSender())) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    String serverResponse = in.readLine();
                    message = objectMapper.readValue(serverResponse, Message.class);

                    System.out.println(message.getTag());

                    if ((message.getTag().equals("!!!")) && message.getBody().equals("Utente Connesso")) {
                        if (Research(message.getSender()) == -1 && !jLabel2.getText().equals(message.getSender())) {
                            arrayUtenti.add(message.getSender());
                            avvioUser(message.getSender(), "");
                        }

                    } else if ((message.getTag().equals("!!!")) && message.getBody().equals("Utente Disconesso")) {
                        int posA = Research(message.getSender());

                        System.out.println(message.getSender());

                        if (!message.getSender().equals(jLabel2.getText())) {
                            arrayUtenti.remove(posA);
                        }
                        removeUser(message.getSender());

                    } else if (message.getBody().equals("close") && message.getSender().equals("Server")) {
                        this.s.close();
                        System.exit(0);
                    } else if (message.getTag().equals("@")) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String orario = String.valueOf(dtf.format(LocalDateTime.now()));
                        Item_Left item = new Item_Left(message.getSender() + " (Private Message) " + "\n" + message.getBody(), orario);
                        lightMessage(message.getSender());
                        jPanel2.add(item, "wrap, w 80%");
                        jPanel2.repaint();
                        jPanel2.revalidate();
                    } else {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String orario = String.valueOf(dtf.format(LocalDateTime.now()));
                        Item_Left item = new Item_Left(message.getSender() + "\n" + message.getBody(), orario);
                        jPanel2.add(item, "wrap, w 80%");
                        lightMessage("Gruppo - Broadcast");
                        jPanel2.repaint();
                        jPanel2.revalidate();
                    }

                }
            } catch (IOException e) {
            } catch (InterruptedException ex) {
                Logger.getLogger(Gui_chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
