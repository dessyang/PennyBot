package com.yjymh.penny.command.group;

import com.yjymh.penny.command.GroupCommand;
import com.yjymh.penny.entity.BiliLive;
import com.yjymh.penny.entity.CommandProperties;
import com.yjymh.penny.requests.service.BiliRequestService;
import com.yjymh.penny.service.BiliLiveService;
import com.yjymh.penny.sys.annotate.Command;
import com.yjymh.penny.utils.ChangeUtil;
import com.yjymh.penny.utils.StaticText;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * 直播开播关注命令
 */
@Command
public class LiveCommand implements GroupCommand {

    private static final Logger logger = LoggerFactory.getLogger(LiveCommand.class);
    @Autowired
    BiliLiveService biliLiveService;

    @Autowired
    BiliRequestService biliRequestService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("live", "l");
    }


    @Override
    public Message execute(Member sender, ArrayList<String> args, MessageChain messageChain, Group subject) {
        // TODO: 2021/5/8 只有两个指令 一个添加关注一个取消关注
        String msg = ""; // 需要返回的消息

        try {
            String com = args.get(0); // 第一个指令
            Long uid = Long.parseLong(args.get(1));
            long id = subject.getId();  // 消息的来源id，可能是群组也可能是好友

            switch (com) {
                case "add":
                case "a":
                    msg = addStar(uid, id);
                    break;
                case "del":
                case "d":
                    msg = delStar(uid, id);
                    break;
            }
        } catch (NumberFormatException e) {
            msg = "第三个参数应为纯数字";
        }
        return new PlainText(msg);
    }


    private String delStar(long uid, long groupId) {

        BiliLive biliLiveInfo = biliLiveService.queryBiliLiveByUid(uid);

        ArrayList<Long> starGroup = ChangeUtil.stringToLongList(biliLiveInfo.getStarGroup());

        boolean flag = starGroup.contains(groupId);

        String msg;
        if (flag) {
            starGroup.remove(groupId);
            biliLiveInfo.setStarGroup(ChangeUtil.listToString(starGroup));
            biliLiveService.updateBiliLive(biliLiveInfo);

            msg = StaticText.DEL_SUCCESS;
        } else {
            msg = StaticText.DEL_ED;
        }

        return msg;
    }

    private String addStar(long uid, long groupId) {
        String msg = "";

        BiliLive biliLiveInfo = biliLiveService.queryBiliLiveByUid(uid);

        BiliLive biliLive = new BiliLive(uid);

        if (biliLiveInfo != null) {
            String starGroup = biliLiveInfo.getStarGroup();
            ArrayList<Long> list = ChangeUtil.stringToLongList(starGroup);
            if (list.contains(groupId)) {
                msg = StaticText.ADD_ED;
            } else {
                list.add(groupId);
                String groups = ChangeUtil.listToString(list);
                biliLive.setStarGroup(groups);
                int i = biliLiveService.updateBiliLive(biliLive);
                msg = biliLive.getName() + StaticText.ADD_SUCCESS;
            }
        } else {
            biliLive.setStarGroup(String.valueOf(groupId));
            int i = biliLiveService.addBiliLive(biliLive);
            logger.info(String.format("添加了一个新的主播:%s", uid));
            msg = biliLive.getName() + StaticText.ADD_SUCCESS;
        }

        return msg;
    }

}
