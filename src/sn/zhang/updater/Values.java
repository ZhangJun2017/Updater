package sn.zhang.updater;

public class Values {
    //Globe Vars Here
    String version = "1.0.0";
    int versionCode = 100;
    String checkUrl = "http://update.zhang-jun.work/check.php";
    String appType = "debug";
    int updateChannel = 2;
    //Json Info Here
    String updateUrl = "";
    String newVersion = "";
    int newVersionCode = versionCode;
    String desc = "";
    //Channel Info Here
    final int CHANNEL_PC_DOWNLOAD = 1;
    final int CHANNEL_PC_URL = 2;
    final int CHANNEL_ANDROID_DOWNLOAD = 3;
    final int CHANNEL_ANDROID_URL = 4;
}

