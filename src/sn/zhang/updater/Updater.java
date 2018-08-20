package sn.zhang.updater;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Updater {
    Values values = new Values();

    public void checkUpdate() {
        System.out.println("Checking Update");
        String updateInfo = getURLContent(values.checkUrl + "?type=" + values.appType);
        //System.out.println(updateInfo);
        parseUpdateJson(updateInfo);
        /**System.out.println(values.updateUrl);           //  These     //
         **System.out.println(values.newVersion);         //   Only     //
         **System.out.println(values.newVersionCode);    //    For     //
         **System.out.println(values.desc);             //     Debug  //
         **/
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
        }
    }

    private void update() {
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

    private void parseUpdateJson(String JsonData) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(JsonData);
        values.updateUrl = jsonObject.get("url").getAsString();
        values.newVersion = jsonObject.get("version").getAsString();
        values.newVersionCode = jsonObject.get("versionCode").getAsInt();
        values.desc = jsonObject.get("desc").getAsString();
    }

    private static String getURLContent(String urlStr) {
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
}
