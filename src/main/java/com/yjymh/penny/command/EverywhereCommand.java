package com.yjymh.penny.command;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.ArrayList;

/**
 * @author yjymh
 */
public interface EverywhereCommand extends Command {
    Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject);
}
