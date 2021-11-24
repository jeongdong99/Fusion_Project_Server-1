package controller.server;

import controller.server.thread.RegistrationServerThread;

import java.io.*;
import java.net.*;


public class RegistrationServer {

    public static void main(String[] args) {

        ServerSocket ss = null;
        Socket conn = null;

        try {

            ss = new ServerSocket(3000);
            System.out.println("Server socket created.Waiting for connection...");

            conn = ss.accept();
            System.out.println("클라이언트 접속");
            RegistrationServerThread client = new RegistrationServerThread(conn);
            client.start();
            System.out.println("Connection received from " + conn.getInetAddress().getHostName() + " : " + conn.getPort());


        } catch (IOException e) {
            System.err.println("IOException");
        }

    }
}
