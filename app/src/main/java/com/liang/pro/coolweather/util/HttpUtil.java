package com.liang.pro.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }

                    inputStream.close();
                    bufferedReader.close();
                    connection.disconnect();
                    if (listener != null){
                        listener.onFinish(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    if (listener != null){
                        listener.onError(e);
                    }
                }
            }
        }).start();

    }
}
