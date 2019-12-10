package com.laomukuq;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import com.laomukuq.entity.LaomuBot;
import com.laomukuq.listeners.IdCodeListener;
import com.laomukuq.listeners.NewsListener;
import com.laomukuq.listeners.TestListener;
import com.laomukuq.properties.BotAccountProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author laomu
 * @version 1.0
 * @description 构建Bot
 * @date 2019-12-10
 */
public class BuildBot {
    private List<PicqBotX> picqBotXList;
    private ExecutorService executorService;

    @Autowired
    private BotAccountProperties botAccountProperties;
    @Autowired
    private NewsListener newsListener;
    @Autowired
    private TestListener testListener;
    @Autowired
    private IdCodeListener idCodeListener;

     private Logger logger = LoggerFactory.getLogger(BuildBot.class);
    public void initBots(){
        getBots();
        buildListeners();
        enableCommands();
        buildCommands();
        for (PicqBotX picqBotX : picqBotXList) {
            // 依次启动bot
            executorService.submit(picqBotX::startBot);
        }
        logger.info("机器人全部启动成功");
    }

    // 获取所有配置的bot 转换成真正的bot
    private void getBots(){
        // 获取配置的bot集合
        List<LaomuBot> laomuBots = botAccountProperties.getLaomuBots();
        picqBotXList = new ArrayList<>();
        // 获取配置的属性 封装为bot
        for (LaomuBot laomuBot : laomuBots) {
            PicqConfig picqConfig = null;
            if(laomuBot.getPostPort()!=null){
                picqConfig = new PicqConfig(Integer.parseInt(laomuBot.getSocketPort()));
            }else {
                logger.error("没有设置机器人得端口!");
                return;
            }
            BeanUtils.copyProperties(picqConfig, laomuBot);
            // 根据机器人配置 初始化机器人
            PicqBotX bot = new PicqBotX(picqConfig);
            // 添加机器人账户
            if(!StringUtils.isEmpty(laomuBot.getBotName()) && !StringUtils.isEmpty(laomuBot.getIpAddr()) && !StringUtils.isEmpty(laomuBot.getPostPort())) {
                bot.addAccount(laomuBot.getBotName(), laomuBot.getIpAddr(), Integer.parseInt(laomuBot.getPostPort()));
            }else {
                logger.error("机器人账户信息配置错误");
                return;
            }
            // 添加机器人对象到集合
            picqBotXList.add(bot);
        }
        // 初始化bot线程池
        executorService = Executors.newFixedThreadPool(picqBotXList.size());
    }

    private void buildListeners(){
        // 暂时写死,未来将会根据listeners包下得类进行初始化
        for (PicqBotX bot : picqBotXList) {
            bot.getEventManager().registerListeners(idCodeListener,
                    newsListener,
                    testListener);
        }
    }

    public void enableCommands(){
        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        for (PicqBotX bot : picqBotXList) {
            // bot.enableCommandManager("bot -", "!", "/", "~");
        }

    }

    private void buildCommands(){
        // 暂时写死,未来将会根据commonds包下得类进行初始化
        /*for (PicqBotX bot : picqBotXList) {
            bot.getCommandManager().registerCommands(
            );
        }*/
    }

    public List<PicqBotX> getPicqBotXList(){
        return picqBotXList;
    }

    public ExecutorService getExecutorService(){
        return executorService;
    }
}
