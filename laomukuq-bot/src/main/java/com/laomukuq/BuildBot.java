package com.laomukuq;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import com.laomukuq.entity.LaomuBot;
import com.laomukuq.properties.BotAccountProperties;
import com.laomukuq.utils.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author laomu
 * @version 1.0
 * @description 构建Bot
 * @date 2019-12-10
 */
public class BuildBot {
    private List<PicqBotX> picqBotXs;
    private ExecutorService executorService;

    @Autowired
    private BotAccountProperties botAccountProperties;
    @Autowired
    private ApplicationContext applicationContext;

    IcqListener listeners[];

    IcqCommand commands[];

    IcqListener filters[];

    private Logger logger = LoggerFactory.getLogger(BuildBot.class);
    public void initBots() {
        getBots();
        buildListeners();
        buildFilters();
        enableCommands();
        buildCommands();
        for (PicqBotX picqBotX : picqBotXs) {
            // 依次启动bot
            executorService.submit(picqBotX::startBot);
        }
        logger.info("机器人全部启动成功");
    }

    // 获取所有配置的bot 转换成真正的bot
    private void getBots(){
        // 获取配置的bot集合
        List<LaomuBot> laomuBots = botAccountProperties.getLaomuBots();
        picqBotXs = new ArrayList<>();
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
            picqBotXs.add(bot);
        }
        // 初始化bot线程池
        executorService = Executors.newFixedThreadPool(picqBotXs.size());
    }

    private void buildListeners() {
        // 根据listeners包下得类进行初始化
        Set<Class<?>> classes = ClassUtils.getClasses("com.laomukuq.listeners");
        Iterator<Class<?>> iterator = classes.iterator();
        listeners = new IcqListener[classes.size()];
        int index = 0;
        while (iterator.hasNext()){
            Class<IcqListener> listenerClass = (Class<IcqListener>) iterator.next();
            // 从Spring获取类添加到数组
            listeners[index++] = applicationContext.getBean(listenerClass);
        }

        for (PicqBotX bot : picqBotXs) {
            bot.getEventManager().registerListeners(listeners);
        }
    }

    private void buildFilters() {
        // 根据filters包下得类进行初始化
        Set<Class<?>> classes = ClassUtils.getClasses("com.laomukuq.filters");
        Iterator<Class<?>> iterator = classes.iterator();
        filters = new IcqListener[classes.size()];
        int index = 0;
        while (iterator.hasNext()){
            Class<IcqListener> filterClass = (Class<IcqListener>) iterator.next();
            // 从Spring获取类添加到数组
            filters[index++] = applicationContext.getBean(filterClass);
        }

        for (PicqBotX bot : picqBotXs) {
            bot.getEventManager().registerListeners(filters);
        }
    }

    private void enableCommands(){
        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        for (PicqBotX bot : picqBotXs) {
            // bot.enableCommandManager("bot -", "!", "/", "~");
        }

    }

    private void buildCommands(){
        // 根据commonds包下得类进行初始化
        Set<Class<?>> classes = ClassUtils.getClasses("com.laomukuq.commonds");
        Iterator<Class<?>> iterator = classes.iterator();
        commands = new IcqCommand[classes.size()];
        int index = 0;
        while (iterator.hasNext()){
            Class<IcqCommand> commandClass = (Class<IcqCommand>) iterator.next();
            // 从Spring获取类添加到数组
            commands[index++] = applicationContext.getBean(commandClass);
        }

        for (PicqBotX bot : picqBotXs) {
            bot.getCommandManager().registerCommands(commands);
        }
    }

    public List<PicqBotX> getpicqBotXs(){
        return picqBotXs;
    }

    public ExecutorService getExecutorService(){
        return executorService;
    }



    public static void main(String[] args) {
        Set<Class<?>> classSet =  ClassUtils.getClasses("com.laomukuq.listeners");
        for (Class<?> class1 : classSet) {
            System.out.println(class1.getSimpleName());
        }
    }
}
