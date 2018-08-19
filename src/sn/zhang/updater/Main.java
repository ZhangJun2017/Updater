package sn.zhang.updater;

public class Main {
    public static void main(String[] args) {
        Values values = new Values();
        Updater updater = new Updater();
        System.out.println("Updater Sample v" + values.version);
        System.out.println("This is a updater demo.");
        System.out.println("Now I'm going to check update.");
        System.out.println("===============");
        updater.checkUpdate();
        System.out.println("===============");
        System.out.println("Finished");
    }
}
