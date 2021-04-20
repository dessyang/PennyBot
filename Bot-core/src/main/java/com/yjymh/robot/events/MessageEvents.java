package com.yjymh.robot.events;

import com.yjymh.robot.command.CommandConfig;
import com.yjymh.robot.command.EverywhereCommand;
import com.yjymh.robot.command.FriendCommand;
import com.yjymh.robot.command.GroupCommand;
import com.yjymh.robot.entity.KeyWord;
import com.yjymh.robot.service.KeyWordService;
import com.yjymh.robot.utils.StringUtil;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MessageEvents extends SimpleListenerHost {

    private static final Logger logger = LoggerFactory.getLogger(MessageEvents.class);

    @Autowired
    private CommandConfig commandConfig;

    @Autowired
    private KeyWordService keyWordService;

    @NotNull
    @EventHandler
    public ListeningStatus commandFriendMessage(@NotNull FriendMessageEvent event) {
        Friend sender = event.getSender();

        String oriMsg = event.getMessage().contentToString();

        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }

        FriendCommand command = (FriendCommand) commandConfig.getCommand(oriMsg, commandConfig.friendCommands);
        if (null == command) {
            return ListeningStatus.LISTENING;
        }

        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), event.getSubject());

        if (result != null) {
            event.getSubject().sendMessage(result);
        }

        event.intercept();

        return ListeningStatus.LISTENING;
    }

    // 所有消息事件
    @NotNull
    @EventHandler
    public ListeningStatus commandMessage(@NotNull MessageEvent event) {
        User sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();


        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }
        EverywhereCommand command = (EverywhereCommand) commandConfig.getCommand(oriMsg, commandConfig.everywhereCommands);

        if (command == null) {
            return ListeningStatus.LISTENING;
        }

        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), event.getSubject());

        if (result != null) {
            event.getSubject().sendMessage(result);
        }

        event.intercept();

        return ListeningStatus.LISTENING;
    }

    @NotNull
    @EventHandler
    public ListeningStatus commandGroupMessage(@NotNull GroupMessageEvent event) {
        Member sender = event.getSender();

        String oriMsg = event.getMessage().contentToString();

        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }

        GroupCommand command = (GroupCommand) commandConfig.getCommand(oriMsg, commandConfig.groupCommands);
        if (null == command) {
            return ListeningStatus.LISTENING;
        }

        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), event.getSubject());

        if (result != null) {
            event.getSubject().sendMessage(result);
        }

        event.intercept();

        return ListeningStatus.LISTENING;

    }

    @NotNull
    @EventHandler
    public ListeningStatus plainGroupMessage(@NotNull GroupMessageEvent event) {

        Long group = event.getGroup().getId();
        String oriMsg = event.getMessage().contentToString();

        if (commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }

        KeyWord keyWord = keyWordService.queryWordByKey(oriMsg, group);

        if (keyWord != null) {
            String word = keyWord.getWord();
            event.getGroup().sendMessage(word);
        }

        return ListeningStatus.LISTENING;
    }

    private ArrayList<String> getArgs(String msg) {
        String[] args = msg.trim().split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String arg : args) {
            if (StringUtil.isNotEmpty(arg)) list.add(arg);
        }
        list.remove(0);
        return list;
    }
}
