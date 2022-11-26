/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package it.teriaca.chat_gui_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Chat_GUI_Server {

  public static void main(String[] args) throws Exception {

GUI_server serverGui = new GUI_server();
serverGui.setVisible(true);
}
  
}
