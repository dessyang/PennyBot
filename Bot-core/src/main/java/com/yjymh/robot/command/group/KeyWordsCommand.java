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
import net.mamoe.mirai.message.data.PlainText;
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
            String com = args.get(0); // 获取指令模式

            switch (com) {
                case "del":
                    return new PlainText("删除成功");
                case "add":
                    return new PlainText(addKeyWord(group, sendId, args, keyWord, time));
                default:
                    return new PlainText("指令错误");
            }
        } else {
            subject.sendMessage("权限不足");
        }
        return null;
    }

    public String addKeyWord(Long group, Long send, ArrayList<String> args, KeyWord keyWord, Date time) {
        if (args.size() >= 3) {
            String key = args.get(1);
            String word = args.get(2);
            for (int i = 3; i < args.size(); i++) {
                word = word + " " + args.get(i);
            }
            keyWord.setKey(key);
            keyWord.setWord(word);
            keyWord.setGroup(group);
            keyWord.setStatus(true);
            keyWord.setMember(send);
            keyWord.setCreate_time(time);
            keyWord.setUpdate_time(time);

            boolean flag = keyWordService.keyWordExits(key, group);

            int i = 0;

            if (!flag) {
                i = keyWordService.addKeyWord(keyWord);
            } else {
                i = keyWordService.updateKeyWord(keyWord);
            }
            if (i == 0) {
                return "创建失败";
            } else {
                return "创建成功";
            }
        } else {
            return "格式错误";
        }
    }
}