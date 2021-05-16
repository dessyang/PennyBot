package com.yjymh.penny;

import com.yjymh.penny.bot.PennyBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author yjymh
 */
@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BotApplication.class, args);
        PennyBot bot = context.getBean(PennyBot.class);
        bot.startBot();
    }

}
