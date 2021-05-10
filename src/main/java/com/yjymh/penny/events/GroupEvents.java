package com.yjymh.penny.events;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;
import net.mamoe.mirai.event.events.MemberMuteEvent;
import net.mamoe.mirai.event.events.MemberUnmuteEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
public class GroupEvents extends SimpleListenerHost {

    // TODO: 2021/5/10
    //群员加群事件
    @NotNull
    @EventHandler
    public ListeningStatus joinGroup(@NotNull MemberJoinEvent event) {
        return ListeningStatus.LISTENING;
    }

    //群员退群事件
    @NotNull
    @EventHandler
    public ListeningStatus leaveGroup(@NotNull MemberLeaveEvent event) {
        return ListeningStatus.LISTENING;
    }

    //群成员被禁言
    @NotNull
    @EventHandler
    public ListeningStatus muteMember(@NotNull MemberMuteEvent event) {
        return ListeningStatus.LISTENING;
    }

    //群成员被解除禁言
    @NotNull
    @EventHandler
    public ListeningStatus unMuteMember(@NotNull MemberUnmuteEvent event) {
        return ListeningStatus.LISTENING;
    }
}
