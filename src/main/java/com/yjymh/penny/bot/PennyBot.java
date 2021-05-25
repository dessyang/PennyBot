package com.yjymh.penny.bot;

import com.yjymh.penny.command.CommandConfig;
import com.yjymh.penny.constant.Const;
import com.yjymh.penny.events.GroupEvents;
import com.yjymh.penny.events.MessageEvents;
import com.yjymh.penny.sys.AnnotateScanner;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.network.UnsupportedSliderCaptchaException;
import net.mamoe.mirai.network.WrongPasswordException;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author yjymh
 */
@Component
public class PennyBot {
    private static final Logger logger = LoggerFactory.getLogger(PennyBot.class);
    private static final String DEVICE_INFO = "devices_info.json";
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

    /**
     * 获取bot实例
     */
    public static Bot getBot() {
        if (bot == null) {
            while (true) {
                if (bot != null) {
                    break;
                }
            }
        }
        return bot;
    }

    /**
     * bot启动方法
     */
    public void startBot() {
        if (botAccount != 0 & !Const.EMPTY.equals(botPwd)) {
            try {
                bot = BotFactory.INSTANCE.newBot(botAccount, botPwd, new BotConfiguration() {{
                    fileBasedDeviceInfo(DEVICE_INFO);
                    setProtocol(setDevice(device));
                }});

                bot.login();

                // 注册指令
                commandConfig.registerCommandHeads();
                commandConfig.registerCommands(annotateScanner.getCommandList());

                List<ListenerHost> events = Arrays.asList(
                        messageEvents,
                        groupEvents
                );
                // 监听消息事件和群组事件
                for (ListenerHost event : events) {
                    GlobalEventChannel.INSTANCE.registerListenerHost(event);
                }

                new Thread(() -> {
                    bot.join();
                }).start();
            } catch (UnsupportedSliderCaptchaException | WrongPasswordException e) {
                logger.error(String.valueOf(e));
                System.exit(1);
            }
        }
    }

    /**
     * 设置登录设备
     */
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
