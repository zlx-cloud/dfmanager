package com.bj.dfmanager.util;

import com.bj.dfmanager.vo.common.PostResponseVO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    /**
     * 发送post请求
     */
    public static PostResponseVO doPost(String urlToRequest, String params) {
        PostResponseVO response = new PostResponseVO();
        try {
            long startTime = System.currentTimeMillis();

            // 创建URL对象
            URL url = new URL(urlToRequest);
            // 打开URL连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式为POST
            conn.setRequestMethod("POST");
            // 设置请求头部信息
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 允许向服务器写入数据
            conn.setDoOutput(true);

            // 获取输出流并写入请求数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            long endTime = System.currentTimeMillis();
            // 获取响应时长
            response.setTime(endTime - startTime);
            // 获取响应状态码
            response.setCode(conn.getResponseCode());
            // 获取响应结果
            response.setResult(sb.toString());

            // 关闭连接
            conn.disconnect();
            outputStream.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            response.setTime(0L);
            response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
            response.setResult(sw.toString());

            try {
                sw.close();
                pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 发送get请求
     */
    public static String doGet(String urlToRequest) {
        try {
            URL url = new URL(urlToRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 设置连接超时时间（单位：毫秒）
            int timeout = 5000; // 5秒
            connection.setConnectTimeout(timeout);
            // 设置读取超时时间（单位：毫秒）
            connection.setReadTimeout(timeout);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                return response.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isPortOpen(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}