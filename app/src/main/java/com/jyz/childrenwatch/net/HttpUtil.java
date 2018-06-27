package com.jyz.childrenwatch.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static void sendHttpRequest(final String url, final String data, final HttpCallbackListener listener) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL myUrl = new URL(url);
                    connection = (HttpURLConnection) myUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(data);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    listener.onFinish(response.toString());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    listener.onError(ex);
                } finally {
                    if(reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static String sendOkHttpRequest(String url, String data, okhttp3.Callback callback) {
        return null;
    }
}
