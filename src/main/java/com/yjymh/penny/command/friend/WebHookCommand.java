package com.yjymh.penny.command.friend;

import com.yjymh.penny.command.FriendCommand;
import com.yjymh.penny.entity.CommandProperties;
import com.yjymh.penny.entity.Token;
import com.yjymh.penny.service.TokenService;
import com.yjymh.penny.sys.annotate.Command;
import com.yjymh.penny.utils.MD5Util;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * webhook开关命令
 *
 * @author yjymh
 */
@Command
public class WebHookCommand implements FriendCommand {

    @Autowired
    TokenService tokenService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("token", "t");
    }

    @Override
    public Message execute(Friend sender, ArrayList<String> args, MessageChain messageChain, Friend subject) {
        // TODO: 2021/5/10
        long sendId = sender.getId();
        String msg = "";
        String newToken = MD5Util.toMD5(String.valueOf(sendId));

        Token tokenInfo = new Token() {{
            setAccount(sendId);
            setType("friend");
            setToken(newToken);
            setStatus(true);
        }};

        Token friendToken = tokenService.queryTokenById(tokenInfo);

        int size = args.size();

        switch (size) {
            case 0:
                if (friendToken != null) {
                    msg = friendToken.getToken();
                } else {
                    tokenService.addToken(tokenInfo);
                    msg = newToken;
                }
                break;
            case 1:
                msg = secondCommand(args.get(0), tokenInfo, friendToken);
                break;
            case 2:
                try {
                    long groupId = Long.parseLong(args.get(1));

                    Group group = subject.getBot().getGroupOrFail(groupId);

                    MemberPermission permission = group.get(sendId).getPermission();

                    if (MemberPermission.OWNER == permission) {
                        tokenInfo.setType("group");
                        tokenInfo.setAccount(groupId);
                        msg = secondCommand(args.get(0), tokenInfo, tokenService.queryTokenById(tokenInfo));
                    } else {
                        msg = PERMISSION_DENIED;
                    }
                } catch (NumberFormatException e) {
                    msg = THIRD_COMMAND_FAULT;
                } catch (NoSuchElementException e) {
                    msg = "请确认机器人是否在该群";
                }
                break;
            default:
                msg = "指令长度错误";
        }
        return new PlainText(msg);
    }

    private String secondCommand(String command, Token tokenInfo, Token friendToken) {

        String msg = "";

        switch (command) {
            case ADD_COMMAND_1:
            case ADD_COMMAND_2:
                if (friendToken != null) {
                    msg = friendToken.getToken();
                } else {
                    tokenService.addToken(tokenInfo);
                    msg = tokenInfo.getToken();
                }
                break;
            case UPDATE_COMMAND_1:
            case UPDATE_COMMAND_2:
            case UPDATE_COMMAND_3:
                if (friendToken != null) {
                    tokenService.updateToken(tokenInfo);
                } else {
                    tokenService.addToken(tokenInfo);
                }
                msg = tokenInfo.getToken();
                break;
            case DELETE_COMMAND_1:
            case DELETE_COMMAND_2:
            case DELETE_COMMAND_3:
                if (friendToken != null) {
                    tokenService.deleteToken(tokenInfo);
                    msg = DELETE_SUCCESS;
                } else {
                    msg = "已经删除，不要重复操作";
                }
                break;
            default:
                msg = SECOND_COMMAND_FAULT;
        }
        return msg;
    }

}
