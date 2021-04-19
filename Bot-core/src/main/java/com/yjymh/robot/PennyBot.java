package com.yjymh.robot;

import com.yjymh.robot.command.CommandConfig;
import com.yjymh.robot.events.GroupEvents;
import com.yjymh.robot.events.MessageEvents;
import com.yjymh.robot.sys.AnnotateScanner;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PennyBot implements ApplicationRunner {

    private static final String deviceInfo = "deviceInfo.json";

    private static Bot bot;

    @Autowired
    private CommandConfig commandConfig;
    @Autowired
    private MessageEvents messageEvents;
    @Autowired
    private GroupEvents groupEvents;
    @Autowired
    private AnnotateScanner annotateScanner;

    @Value("${bot.account}")
    private long botAccount;
    @Value("${bot.pwd}")
    private String botPwd;
    @Value("${bot.device:pad}")
    private String device;

    // 获取bot实例
    public static Bot getBot() {
        return bot;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startBot();
    }

    // bot启动方法
    public void startBot() {
        if (botAccount != 0 & !botPwd.equals("")) {

            bot = BotFactory.INSTANCE.newBot(botAccount, botPwd, new BotConfiguration() {{
                fileBasedDeviceInfo(deviceInfo);
                setProtocol(setDevice(device));
            }});

            bot.login();

            commandConfig.registerCommandHeads();
            commandConfig.registerCommands(annotateScanner.getCommandList());

            List<ListenerHost> events = Arrays.asList(
                    messageEvents,
                    groupEvents
            );

            for (ListenerHost event : events) {
                GlobalEventChannel.INSTANCE.registerListenerHost(event);
            }

            new Thread(() -> {
                bot.join();
            }).start();
        }else {
            return;
        }
    }

    // 设置登录设备
    private MiraiProtocol setDevice(String device) {
        switch (device) {
            case "phone":
            case "手机":
                return MiraiProtocol.ANDROID_PHONE;
            case "watch":
            case "手表":
                return MiraiProtocol.ANDROID_WATCH;
            default:
                return MiraiProtocol.ANDROID_PAD;
        }
    }

}
