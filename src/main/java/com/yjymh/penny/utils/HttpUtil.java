package com.yjymh.penny.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yjymh
 */
public class HttpUtil {

    public static String getImage(String imgUrl, String fileName) throws Exception {
        URL url = new URL(imgUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        //下载路径及下载图片名称
        File file = new File(fileName);
        FileOutputStream os = new FileOutputStream(file, true);
        // 开始写入
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

        return fileName;
    }

    public static String getImage(String imgUrl) throws Exception {
        return getImage(imgUrl, "temp.jpg");
    }

    public static <T> T getDataByApi(String apiUrl, Class<T> classOfT) {
        try {
            URL url = new URL(apiUrl);
            URLConnection con = url.openConnection();

            InputStream inputStream = con.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(inputStreamReader);

            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }

            return JSON.parseObject(urlString, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getDataByApi(String apiUrl) {
        return getDataByApi(apiUrl, JSONObject.class);
    }

}
