package com.yjymh.penny.job;

import com.yjymh.penny.bot.PennyBot;
import com.yjymh.penny.entity.BiliLive;
import com.yjymh.penny.requests.service.BiliRequestService;
import com.yjymh.penny.service.BiliLiveService;
import com.yjymh.penny.utils.ChangeUtil;
import com.yjymh.penny.utils.HttpUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author yjymh
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class BiliJobs {
    // TODO: 2021/5/10  
    private static final Logger logger = LoggerFactory.getLogger(BiliJobs.class);

    @Autowired
    BiliRequestService biliRequestService;

    @Autowired
    BiliLiveService biliLiveService;

    //3.添加定时任务
    @Scheduled(cron = "0/60 * * * * ?")
    //@Scheduled(fixedRate = 3000)
    private void biliLivePush() {

        logger.info("直播轮询开始");

        ArrayList<BiliLive> list = biliLiveService.queryLiveId();

        for (int i = 0; i < list.size(); i++) {
            String msg = "%s 正在直播\n%s\n%s";

            BiliLive info = list.get(i);
            Long liveId = info.getLiveId();
            String url = "https://live.bilibili.com/" + liveId;

            boolean liveStatus = info.isLiveStatus();
            Long uid = info.getUid();
            String name = info.getName();

            boolean liveState = biliRequestService.getLiveStateByUid(uid);

            if (liveState && !liveStatus) {

                Bot bot = PennyBot.getBot();

                String title = biliRequestService.getLiveTitle(uid);
                String coverLink = biliRequestService.getLiveCoverLink(uid);

                ArrayList<Long> starGroup = ChangeUtil.stringToLongList(info.getStarGroup());


                String fileName = String.format("%s.jpg", uid);
                MessageChain chain = null;
                try {
                    HttpUtil.getImage(coverLink, fileName);
                    File file = new File(fileName);
                    Image image = bot.getAsFriend().uploadImage(ExternalResource.create(file));
                    chain = new MessageChainBuilder()
                            .append(new PlainText(String.format(msg, name, title, url)))
                            .append(image)
                            .build();
                } catch (Exception e) {
                    e.printStackTrace();
                    chain = new MessageChainBuilder()
                            .append(new PlainText(String.format(msg, name, title, url)))
                            .build();
                }

                for (Long group : starGroup) {
                    try {
                        bot.getGroupOrFail(group).sendMessage(chain);
                    } catch (NoSuchElementException e) {
                        // 没有获取到好友或者群组
                    }
                }
                biliLiveService.updateLiveState(uid);
            } else if (!liveState && liveStatus) {
                biliLiveService.updateLiveState(uid);
            }
        }
    }
}