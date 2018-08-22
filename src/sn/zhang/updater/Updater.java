package sn.zhang.updater;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Updater {
    Values values = new Values();
    boolean haveChecked = false;
    String updateInfo;

    public static String readInputStream(InputStream is) {
        byte[] result;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            result = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new String(result);
    }

    public void checkUpdate(String type) {
        //System.out.println("Checking Update");
        updateInfo = getByOkHttp(values.checkUrl + "?type=" + type);
        //System.out.println(updateInfo);
        parseUpdateJson(updateInfo);
        /**
         * System.out.println(values.updateUrl);           //  These     //
         * System.out.println(values.newVersion);         //   Only     //
         * System.out.println(values.newVersionCode);    //    For     //
         * System.out.println(values.desc);             //     Debug  //
         */
        /*
        if (values.versionCode < values.newVersionCode) {
            System.out.println("新版本可用：");
            System.out.println(values.version + "(" + values.versionCode + ")" + " -> " + values.newVersion + "(" + values.newVersionCode + ")");
            System.out.println(values.desc);
            update();
        } else if (values.versionCode < values.newVersionCode) {
            System.out.println("无更新可用，当前版本" + values.version + "(" + values.versionCode + ")");
        } else if (values.versionCode > values.newVersionCode) {
            System.out.println("当前版本 " + values.version + "(" + values.versionCode + ")" + " 似乎比服务器版本 " + values.newVersion + "(" + values.newVersionCode + ") 新，不科学啊");
        } else {
            System.out.println("除非你反编译不然你永远也看不到这句话:D");
        }*/
    }

    public void update() {
        switch (values.updateChannel) {
            case 1:
                //TODO:Finish Download Function
                break;
            case 2:
                //TODO:Notice User To Download Update Manually
                break;
            case 3:
                //TODO:Finish Download Function And Install
                break;
            case 4:
                //TODO:Notice User To Download Update Manually And Install It
                break;
            default:
                //TODO:Output Wrong
                break;
        }
    }

    public void parseUpdateJson(String JsonData) {
        this.haveChecked = true;
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(JsonData);
        values.updateUrl = jsonObject.get("url").getAsString();
        values.newVersion = jsonObject.get("version").getAsString();
        values.newVersionCode = jsonObject.get("versionCode").getAsInt();
        values.desc = jsonObject.get("desc").getAsString();
    }

    public String getURLContent(String urlStr) {
        HttpURLConnection httpConn = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = sb.toString();
        //System.err.println("Connect Success");
        return result;
    }

    public String getHttp(String path) {
        int code;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                String str = readInputStream(is);
                return str;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getByOkHttp(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String jsonData = response.body().string();
            return jsonData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cross Class Call-Tool
     */

    public String getNewVersion() {
        if (haveChecked == true) {
            return values.newVersion;
        } else {
            return null;
        }
    }

    public String getNewDesc() {
        if (haveChecked == true) {
            return values.desc;
        } else {
            return null;
        }
    }

    public int getNewVerCode() {
        if (haveChecked == true) {
            return values.newVersionCode;
        } else {
            return 0;
        }
    }

    public String getNewUrl() {
        if (haveChecked == true) {
            return values.updateUrl;
        } else {
            return null;
        }
    }

    public String getUpdateJson() {
        if (haveChecked == true) {
            return updateInfo;
        } else {
            return null;
        }
    }
}
