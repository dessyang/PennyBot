package com.yjymh.robot.command.everywhere;

import com.yjymh.robot.command.EverywhereCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.sys.annotate.Command;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 动态推送指令
 * 格式为：.指令名称 uid/名称
 */
@Command
@Component
public class DynamicCommand implements EverywhereCommand {
    @Override
    public CommandProperties properties() {
        return new CommandProperties("dynamic", "dc");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {

        try {
            long uid = Long.parseLong(args.get(0));

        } catch (Exception e) {
            String name = args.get(0);
        }


        return null;
    }
}
