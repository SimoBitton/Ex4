package com.example.myapplication;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;

public class Client {

    public static Client instance = null;
    private static Socket clientSocket;


    public Client()
    {

    }

    public static Client getInstance(){
        if (instance == null)
            instance = new Client();

        return instance;
    }

    public static void connect() {
        String temp;
        String displayBytes;
        try
        {
            //create input stream
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //create client socket, connect to server
            Socket clientSocket = new Socket("10.0.2.2",1234);
            //create output stream attached to socket
            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());



            System.out.print("Command : ");

            //create input stream attached to socket
            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

            temp = inFromUser.readLine();

            //send line to server
            //outToServer.writeBytes(temp);
            outToServer.writeUTF(temp);
            outToServer.flush();


            //read line from server
            //displayBytes = inFromServer.readLine();

            while((displayBytes = inFromServer.readUTF()) != null)
            {
                System.out.print(displayBytes);
            }
        }
        catch(Exception ex)
        {

        }
    }

    public static void Close(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
