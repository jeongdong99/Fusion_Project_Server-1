package controller.server.thread;

import java.io.*;
import java.net.Socket;

public class RegistrationServerThread extends Thread{
    private Socket socket;
    InputStream is;
    BufferedReader reader;
    OutputStream os;
    BufferedWriter writer;

    public RegistrationServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        open();
        System.out.println("접속 완료");

        while(true){

        }
    }

    private void open(){
        try{
            is = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            os = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //로그인
    private void login(){

    }

    //로그아웃
    private  void logout(){

    }

    //교수 생성

    //학생 생성

    //교과목 생성

    //교과목 수정

    //교과목 삭제제
}
