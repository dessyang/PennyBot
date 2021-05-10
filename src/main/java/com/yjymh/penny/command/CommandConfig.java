package com.yjymh.penny.command;

import com.yjymh.penny.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommandConfig {
    private static final Logger logger = LoggerFactory.getLogger(CommandConfig.class);
    public static Map<String, Command> friendCommands = new HashMap<>();
    public static Map<String, Command> groupCommands = new HashMap<>();
    public static Map<String, Command> everywhereCommands = new HashMap<>();
    // 指令头 区分正常消息和指令消息
    private static Set<String> commandHeads = new HashSet<>();

    public void registerCommandHeads() {
        String[] heads = initCommandHeads();
        for (String head : heads) {
            commandHeads.add(head);
        }
    }

    public boolean isCommand(String msg) {
        return commandHeads.stream().anyMatch(head -> msg.startsWith(head));
    }
    
    public Command getCommand(String msg, Map<String, Command> commandMap) {
        String[] temp = msg.split(" ");

        String headcommand = temp[0];

        List<String> temps = commandHeads.stream()
                .filter(head -> headcommand.startsWith(head) && StringUtil.isNotEmpty(head))
                .map(head -> headcommand.replaceFirst(head, ""))
                .collect(Collectors.toList());

        String commandStr;
        if (temps.isEmpty()) {
            commandStr = headcommand;
        } else {
            commandStr = temps.get(0);
        }

        return commandMap.getOrDefault(commandStr.toLowerCase(), null);
    }

    // 注册指令 (批量）
    public void registerCommands(List<Command> commandList) {
        if (null == commandList || commandList.size() <= 0) {
            return;
        }
        commandList.forEach(command -> registerCommand(command));
    }

    // 注册指令 单个
    public void registerCommand(Command command) {
        Map<String, Command> tempCommands = new HashMap<>();
        tempCommands.put(command.properties().getName().toLowerCase(), command);
        command.properties().getAlias().forEach(alias -> tempCommands.put(alias.toLowerCase(), command));

        if (command instanceof FriendCommand) {
            friendCommands.putAll(tempCommands);
            logger.info("好友命令：{}注册成功", command.properties().getName());
        } else if (command instanceof GroupCommand) {
            groupCommands.putAll(tempCommands);
            logger.info("群组命令：{}注册成功", command.properties().getName());
        } else if (command instanceof EverywhereCommand) {
            everywhereCommands.putAll(tempCommands);
            logger.info("任意命令：{}注册成功", command.properties().getName());
        } else {
            logger.warn("发现未知指令类型[{}], 忽略该指令注册", command.properties().getName());
        }

    }

    // 指令头部
    public String[] initCommandHeads() {
        String[] heads = new String[]{
                "."
        };
        return heads;
    }
}
