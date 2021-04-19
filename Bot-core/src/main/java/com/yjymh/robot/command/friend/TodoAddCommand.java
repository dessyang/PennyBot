package com.yjymh.robot.command.friend;

import com.yjymh.robot.command.FriendCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.sys.annotate.Command;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.ArrayList;

@Command
public class TodoAddCommand implements FriendCommand {
    @Override
    public CommandProperties properties() {
        return new CommandProperties("addtodo", "at");
    }

    @Override
    public Message execute(Friend sender, ArrayList<String> args, MessageChain messageChain, Friend subject) {
        return null;
    }
}
