package com.yjymh.robot.command.everywhere;

import com.yjymh.robot.command.EverywhereCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.sys.annotate.Command;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.ArrayList;

@Command
public class LiveNoticeCommand implements EverywhereCommand {
    @Override
    public CommandProperties properties() {
        return new CommandProperties("");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        return null;
    }
}
