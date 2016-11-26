package com.tarmantar.deacceldebug;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


public class Client extends AsyncTask<Void, String, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    TcpClient mTcpClient;

    Client(String addr, int port, TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
    }

    @Override
    protected Void doInBackground(Void... values) {
        try {
            mTcpClient = new TcpClient(dstAddress, dstPort, new TcpClient.OnMessageReceived() {
                @Override
                public void messageReceived(String message) {
                    Log.i("Debug", "Received Message: " + message);
                    switch (message) {
                        case "ping":
                            publishProgress(message);
                            mTcpClient.sendMessage("pong");
                            break;
                        default:
                            mTcpClient.sendMessage("idk");
                            break;
                    }
                }
            });
            mTcpClient.run();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }


    protected void onProgressUpdate(String... values) {
        textResponse.append(values[0] + "\r\n");
        super.onProgressUpdate();
    }

    public void sendMessage(String message) {
        mTcpClient.sendMessage(message);
    }
}