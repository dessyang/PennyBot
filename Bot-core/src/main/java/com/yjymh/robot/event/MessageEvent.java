package com.yjymh.robot.event;

import com.yjymh.robot.entity.FriendToken;
import com.yjymh.robot.entity.GroupToken;
import com.yjymh.robot.service.FriendTokenService;
import com.yjymh.robot.service.GroupTokenService;
import com.yjymh.robot.uitls.MD5Util;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class MessageEvent extends SimpleListenerHost {

    @Autowired
    private FriendTokenService friendTokenService;

    @Autowired
    private GroupTokenService groupTokenService;

    // 功能很少，就没必要把command模块化
    @NotNull
    @EventHandler
    public ListeningStatus friendMessage(@NotNull FriendMessageEvent event) {
        String msg = event.getMessage().contentToString();
        Long sendId = event.getSender().getId();
        FriendToken friendToken = friendTokenService.queryFriendById(sendId);
        String newToken = MD5Util.toMD5(sendId.toString());

        Timestamp time = new Timestamp(new Date().getTime());

        if (msg.contains(".token")) {
            if (friendToken != null) {
                // 发送token
                event.getSender().sendMessage(friendToken.getToken());
            } else {
                // 创建一个新的 token
                friendTokenService.addFriend(new FriendToken(sendId, newToken, true, time, time));
                event.getSender().sendMessage(newToken);
            }
        }
        if (msg.contains(".update")) {
            if (friendToken != null) {
                friendTokenService.updateFriend(new FriendToken(sendId, newToken, true, time, null));
            } else {
                friendTokenService.addFriend(new FriendToken(sendId, newToken, true, time, time));
            }
            event.getSender().sendMessage(newToken);
        }
        if (msg.contains(".del")) {
            if (friendToken != null) {
                friendTokenService.deleteFriend(sendId);
                event.getSender().sendMessage("删除成功");
            } else {
                event.getSender().sendMessage("已经删除，不要重复操作");
            }

        }

        return ListeningStatus.LISTENING;
    }

    // 群组消息
    @NotNull
    @EventHandler
    public ListeningStatus groupMessage(@NotNull GroupMessageEvent event) {
        String msg = event.getMessage().contentToString();
        Long sendId = event.getSender().getId();
        Long groupId = event.getGroup().getId();
        GroupToken groupToken = groupTokenService.queryGroupById(groupId);
        String newToken = MD5Util.toMD5(sendId.toString());

        Timestamp time = new Timestamp(new Date().getTime());

        if (event.getSender().getPermission() != MemberPermission.OWNER) {
            return ListeningStatus.LISTENING;
        }
        
        if (msg.contains(".token")) {
            if (groupToken != null) {
                // 发送token
                event.getGroup().sendMessage(groupToken.getToken());
            } else {
                // 创建一个新的 token
                groupTokenService.addGroup(new GroupToken(groupId, newToken, true, time, time));
                event.getGroup().sendMessage(newToken);
            }
        }
        if (msg.contains(".update")) {
            if (groupToken != null) {
                groupTokenService.updateGroup(new GroupToken(groupId, newToken, true, time, null));
            } else {
                groupTokenService.addGroup(new GroupToken(groupId, newToken, true, time, time));
            }
            event.getGroup().sendMessage(newToken);
        }
        if (msg.contains(".del")) {
            if (groupToken != null) {
                groupTokenService.deleteGroup(groupId);
                event.getGroup().sendMessage("删除成功");
            } else {
                event.getGroup().sendMessage("已经删除，不要重复操作");
            }

        }
        return ListeningStatus.LISTENING;
    }

}
