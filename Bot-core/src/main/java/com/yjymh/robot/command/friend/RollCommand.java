package com.yjymh.robot.command.friend;

import com.yjymh.robot.PennyBot;
import com.yjymh.robot.command.FriendCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.sys.annotate.Command;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;

import java.util.ArrayList;

@Command
public class RollCommand implements FriendCommand {
    @Override
    public CommandProperties properties() {
        return new CommandProperties("roll", "r");
    }

    @Override
    public Message execute(Friend sender, ArrayList<String> args, MessageChain messageChain, Friend subject) {
        Bot bot = PennyBot.getBot();
        return new PlainText( "1");
    }
}
