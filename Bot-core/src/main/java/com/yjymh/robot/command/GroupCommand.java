package com.yjymh.robot.command;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.ArrayList;

public interface GroupCommand extends Command {
    Message execute(Member sender, ArrayList<String> args, MessageChain messageChain, Group subject);
}
