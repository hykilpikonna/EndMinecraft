package me.alikomi.endminecraft;

import me.alikomi.endminecraft.Data.BugData;
import me.alikomi.endminecraft.Data.InfoData;
import me.alikomi.endminecraft.Tasks.ScanBug;
import me.alikomi.endminecraft.Tasks.ScanInfo;
import me.alikomi.endminecraft.utils.Util;

import java.util.Scanner;

public class Main extends Util {

    public static BugData bugData;
    public static InfoData infoData;

    public static String ip;
    public static int port;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        log("请输入ip地址");
        ip = sc.next();
        log("请输入端口");
        port = sc.nextInt();
        infoData = new InfoData(ip, port);
        log("是否开始进服前漏洞探测y/n");
        if ("y".equalsIgnoreCase(sc.next())) {
            //bugData = new BugData(ip, port);
            //ScanBug.scanMOTD(ip, port);
            //log("漏洞检测结果： ");
            //log(bugData.toString());
            ScanInfo.scan(ip, port);
        }
    }

}
