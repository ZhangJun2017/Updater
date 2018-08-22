package sn.zhang.updater;

public class Main {
    public static void main(String[] args) {
        Values values = new Values();
        Updater updater = new Updater();
        System.out.println("Updater Sample v" + values.version);
        System.out.println("This is a updater demo.");
        System.out.println("Now I'm going to check update.");
        System.out.println("===============");
        updater.checkUpdate(values.appType);
        System.out.println(updater.getUpdateJson());
        if (values.versionCode < updater.getNewVerCode()) {
            System.out.println("新版本可用：");
            System.out.println(values.version + "(" + values.versionCode + ")" + " -> " + updater.getNewVersion() + "(" + updater.getNewVerCode() + ")");
            System.out.println(updater.getNewDesc());
            updater.update();
        } else if (values.versionCode < updater.getNewVerCode()) {
            System.out.println("无更新可用，当前版本" + values.version + "(" + values.versionCode + ")");
        } else if (values.versionCode > updater.getNewVerCode()) {
            System.out.println("当前版本 " + values.version + "(" + values.versionCode + ")" + " 似乎比服务器版本 " + updater.getNewVersion() + "(" + updater.getNewVerCode() + ") 新，不科学啊");
        } else {
            System.out.println("除非你反编译不然你永远也看不到这句话:D");
        }
        System.out.println("===============");
        System.out.println("Finished");
    }
}
