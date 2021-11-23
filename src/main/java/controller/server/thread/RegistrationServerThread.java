package controller.server.thread;

import java.io.*;
import java.net.Socket;

public class RegistrationServerThread extends Thread{
    private Socket socket;

    public RegistrationServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        InputStream is;
        BufferedReader reader;
        OutputStream os;
        BufferedWriter writer;

        try {
            is = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            os = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));

            System.out.println("접속 완료");

//            while(true){
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
