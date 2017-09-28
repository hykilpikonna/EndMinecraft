package me.alikomi.endminecraft;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.alikomi.endminecraft.data.BugData;
import me.alikomi.endminecraft.data.InfoData;
import me.alikomi.endminecraft.log.Loger;
import me.alikomi.endminecraft.tasks.attack.forge.ForgeHandShake;
import me.alikomi.endminecraft.tasks.scan.ScanBug;
import me.alikomi.endminecraft.tasks.scan.ScanInfo;
import me.alikomi.endminecraft.utils.DefinedPacket;
import me.alikomi.endminecraft.utils.Menu;
import me.alikomi.endminecraft.utils.Util;
import net.saralab.anticheat.Class5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main extends Util {

    public static BugData bugData = null;
    public static InfoData infoData = null;
    public static Loger logger = new Loger(new File("log/" + System.currentTimeMillis() + ".log"));

    private static String ip;
    public static int port = 25565;

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        setProgram(args);
        getInfo();
        scanServer();
        scanBug();
        showMenu();
    }

    private static void setProgram(String[] args) throws InterruptedException, FileNotFoundException {
        if (args == null || args.length == 0) {
            return;
        }
    }

    private static void getInfo() {
        log("欢迎使用EndMinecraft压测程序", "", "官方QQ群： 473516200", "=======================");
        log("请输入ip地址");
        ip = scanner.nextLine();
        if (ip.contains(":")) {
            String[] tmpip = ip.split(":");
            ip = tmpip[0];
            port = Integer.parseInt(tmpip[1]);
        } else {
            log("请输入端口(25565)");
            port = getCo(scanner.nextLine(), 25565);
        }
        infoData = new InfoData(ip, port);
    }

    private static void scanServer() {
        log("正在探测服务器信息，请稍后");
        ScanInfo.ScanMotdInfo(ip, port);
    }

    private static void scanBug() {
        log("是否开始进服前漏洞探测y/n");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            bugData = new BugData(ip, port);
            ScanBug.scanMOTD(ip, port);
            ScanBug.scanTAB(ip, port);
            log("漏洞检测结果： ", bugData.toString());
        }
    }

    private static void showMenu() throws IOException, InterruptedException {
        Menu menu = new Menu(scanner, ip, port);
        while (true) {
            log("请输入攻击方式：", "1 : MOTD攻击", "2 : 分布式假人攻击(集群压测)", "3 : 单ip，TAB压测", "4 : 分布式Forge协议假人攻击(集群压测)");
            log("========================");
            switch (getCo(scanner.nextLine(), 2)) {
                case 1: {
                    menu._1();
                    break;
                }
                case 2: {
                    menu._2();
                    break;
                }
                case 3: {
                    menu._3();
                    break;
                }
                case 4: {
                    menu._4();
                    break;
                }
                default: {
                    log("您的选择有误，请重新选择");
                    break;
                }
            }
        }
    }
}