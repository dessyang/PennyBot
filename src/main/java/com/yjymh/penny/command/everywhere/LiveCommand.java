package com.yjymh.penny.command.everywhere;

import com.yjymh.penny.command.EverywhereCommand;
import com.yjymh.penny.entity.BiliLive;
import com.yjymh.penny.entity.CommandProperties;
import com.yjymh.penny.requests.service.BiliRequestService;
import com.yjymh.penny.service.BiliLiveService;
import com.yjymh.penny.sys.annotate.Command;
import com.yjymh.penny.utils.ListUtil;
import com.yjymh.penny.utils.StaticText;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * 直播动态推送指令
 *
 * @author yjymh
 */
@Command
public class LiveCommand implements EverywhereCommand {

    private static final Logger logger = LoggerFactory.getLogger(LiveCommand.class);

    @Autowired
    BiliLiveService biliLiveService;

    @Autowired
    BiliRequestService biliRequestService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("live", "直播");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        String msg = "";
        try {
            switch (args.get(0)) {
                case "add":
                case "添加":
                    msg = addStar(Long.parseLong(args.get(1)), sender, subject);
                    break;
                case "del":
                case "删除":
                    msg = delStar(Long.parseLong(args.get(1)), sender, subject);
                    break;
                case "list":
                case "列表":
                    msg = starList(sender, subject);
                    break;
                default:
                    msg = "指令错误";
            }
        } catch (NumberFormatException e) {
            msg = "第三个参数应为纯数字";
        }
        return new PlainText(msg);
    }

    private String delStar(long uid, User sender, Contact subject) {

        String msg = "";
        BiliLive biliLiveInfo = biliLiveService.queryBiliLiveByUid(uid);

        if (subject instanceof Friend) {
            ArrayList<Long> starFriend = ListUtil.stringToLongList(biliLiveInfo.getStarFriend());
            boolean flag = starFriend.contains(sender.getId());
            if (flag) {
                starFriend.remove(sender.getId());
                biliLiveInfo.setStarFriend(ListUtil.listToString(starFriend));
                biliLiveService.updateBiliLive(biliLiveInfo);
                msg = StaticText.DEL_SUCCESS;
            } else {
                msg = StaticText.DEL_ED;
            }

        }
        if (subject instanceof Group) {
            ArrayList<Long> starGroup = ListUtil.stringToLongList(biliLiveInfo.getStarGroup());
            boolean flag = starGroup.contains(subject.getId());
            if (flag) {
                starGroup.remove(subject.getId());
                biliLiveInfo.setStarGroup(ListUtil.listToString(starGroup));
                biliLiveService.updateBiliLive(biliLiveInfo);
                msg = StaticText.DEL_SUCCESS;
            } else {
                msg = StaticText.DEL_ED;
            }
        }

        return msg;
    }

    private String addStar(long uid, User sender, Contact subject) {
        String msg = "";
        long senderId = sender.getId();
        BiliLive biliLiveInfo = biliLiveService.queryBiliLiveByUid(uid);

        if (biliLiveInfo != null) {
            if (subject instanceof Friend) {
                ArrayList<Long> starFriendList = ListUtil.stringToLongList(biliLiveInfo.getStarFriend());
                if (starFriendList.contains(senderId)) {
                    msg = StaticText.ADD_ED;
                } else {
                    starFriendList.add(senderId);

                    String friends = ListUtil.listToString(starFriendList);

                    BiliLive biliLive = new BiliLive() {{
                        setUid(uid);
                        setStarFriend(friends);
                    }};

                    int i = biliLiveService.updateBiliLive(biliLive);
                    msg = biliLiveInfo.getName() + StaticText.ADD_SUCCESS;
                }


            }
            if (subject instanceof Group) {
                ArrayList<Long> starGroupList = ListUtil.stringToLongList(biliLiveInfo.getStarGroup());
                if (starGroupList.contains(subject.getId())) {
                    msg = StaticText.ADD_ED;
                } else {
                    starGroupList.add(subject.getId());

                    String groups = ListUtil.listToString(starGroupList);

                    BiliLive biliLive = new BiliLive() {{
                        setUid(uid);
                        setStarGroup(groups);
                    }};

                    int i = biliLiveService.updateBiliLive(biliLive);
                    msg = biliLiveInfo.getName() + StaticText.ADD_SUCCESS;
                }
            }
        } else {
            if (subject instanceof Friend) {
                biliLiveService.addBiliLiveByFriend(uid, senderId);
            }
            if (subject instanceof Group) {
                biliLiveService.addBiliLiveByGroup(uid, subject.getId());
            }
            msg = biliRequestService.getName(uid) + StaticText.ADD_SUCCESS;
        }

        return msg;
    }

    private String starList(User sender, Contact subject) {
        String msg = null;
        ArrayList<BiliLive> biliLives = biliLiveService.queryBiliLiveList();
        ArrayList<String> stars = new ArrayList<String>();
        if (subject instanceof Friend) {
            for (BiliLive live : biliLives) {
                String starFriend = live.getStarFriend();
                ArrayList<Long> longs = ListUtil.stringToLongList(starFriend);
                boolean isExist = longs.contains(sender.getId());
                if (isExist) {
                    stars.add(live.getName() + "-->" + live.getUid());
                }
            }
        }
        if (subject instanceof Group) {
            for (BiliLive live : biliLives) {
                String starGroup = live.getStarGroup();
                ArrayList<Long> longs = ListUtil.stringToLongList(starGroup);
                boolean isExist = longs.contains(subject.getId());
                if (isExist) {
                    stars.add(live.getName() + "-->" + live.getUid());
                }
            }
        }
        if (stars.size() != 0){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("已关注用户：\n");
            for (String star : stars) {
                stringBuffer.append(star + "\n");
            }
            msg = stringBuffer.toString();
        } else {
            msg = "没有关注的用户";
        }

        return msg;
    }

}

