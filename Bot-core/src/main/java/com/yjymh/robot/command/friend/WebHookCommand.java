package com.yjymh.robot.command.friend;

import com.yjymh.robot.command.FriendCommand;
import com.yjymh.robot.entity.CommandProperties;
import com.yjymh.robot.entity.FriendToken;
import com.yjymh.robot.service.FriendTokenService;
import com.yjymh.robot.sys.annotate.Command;
import com.yjymh.robot.uitls.MD5Util;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Command
public class WebHookCommand implements FriendCommand {

    @Autowired
    FriendTokenService friendTokenService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("token", "t");
    }

    @Override
    public Message execute(Friend sender, ArrayList<String> args, MessageChain messageChain, Friend subject) {

        Long sendId = sender.getId();
        FriendToken friendToken = friendTokenService.queryFriendById(sendId);
        String newToken = MD5Util.toMD5(sendId.toString());

        Timestamp time = new Timestamp(new Date().getTime());

        if (args.size() == 0) {
            // 发送当前token，如果没有就新建一个
            if (friendToken != null) {
                // 发送token
                sender.sendMessage(friendToken.getToken());
            } else {
                // 创建一个新的 token
                friendTokenService.addFriend(new FriendToken(sendId, newToken, true, time, time));
                sender.sendMessage(newToken);
            }
        } else if (args.size() == 1) {
            if (args.get(0).contains("del")) {
                if (friendToken != null) {
                    friendTokenService.deleteFriend(sendId);
                    sender.sendMessage("删除成功");
                } else {
                    sender.sendMessage("已经删除，不要重复操作");
                }
            } else if (args.get(0).contains("update")) {
                if (friendToken != null) {
                    friendTokenService.updateFriend(new FriendToken(sendId, newToken, true, time, null));
                } else {
                    friendTokenService.addFriend(new FriendToken(sendId, newToken, true, time, time));
                }
                sender.sendMessage(newToken);
            }
        }
        return null;
    }
}
