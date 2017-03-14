package com.bigmacdev.all_med;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    String address, response;
    int port;
    PrintWriter out;
    BufferedReader in;


    Client(String add, int por){
       address=add;
        port=por;
    }

    protected String runRequest(String requestString){
        Socket socket=null;
        try {
            socket = new Socket(address, port);
            Log.d("Client", socket.isConnected()+"");
            out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(requestString);
            Log.d("Client", "Out.Write Executed.");
            response=in.readLine();
            Log.d("Client", response);
            socket.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
