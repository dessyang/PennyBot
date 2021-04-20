package com.yjymh.robot.command.group;

import com.yjymh.robot.command.GroupCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.entity.KeyWord;
import com.yjymh.robot.service.KeyWordService;
import com.yjymh.robot.sys.annotate.Command;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Command
public class KeyWordsCommand implements GroupCommand {

    @Autowired
    KeyWordService keyWordService;


    @Override
    public CommandProperties properties() {
        return new CommandProperties("keyword", "k");
    }

    @Override
    public Message execute(Member sender, ArrayList<String> args, MessageChain messageChain, Group subject) {

        KeyWord keyWord = new KeyWord();

        long group = subject.getId();
        long sendId = sender.getId();
        Timestamp time = new Timestamp(new Date().getTime());

        MemberPermission permission = subject.get(sendId).getPermission();

        keyWordService.createTable(group);

        if (permission != MemberPermission.MEMBER) {
            if (args.size() == 2) {
                String key = args.get(0);
                String word = args.get(1);

                keyWord.setKey(key);
                keyWord.setWord(word);
                keyWord.setGroup(group);
                keyWord.setStatus(true);
                keyWord.setMember(sendId);
                keyWord.setCreate_time(time);
                keyWord.setUpdate_time(time);

                boolean flag = keyWordService.keyWordExits(key, group);

                if (!flag) {
                    keyWordService.addKeyWord(keyWord);
                } else {
                    keyWordService.updateKeyWord(keyWord);
                }
            } else {
                subject.sendMessage("格式错误");
            }
        } else {
            subject.sendMessage("权限不足");
        }
        return null;
    }
}