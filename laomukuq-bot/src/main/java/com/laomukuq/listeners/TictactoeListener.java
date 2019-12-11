package com.laomukuq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import com.laomukuq.entity.Tictactoe;
import com.laomukuq.utils.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author laomu
 * @version 1.0
 * @description 井字棋
 * @date 2019-12-11
 */
@Component
public class TictactoeListener extends IcqListener {
    // List<群号>
    Map<Long, Tictactoe> tictactoeMap = new HashMap<>();

    @EventHandler
    public void tictactoeMatch(EventGroupMessage event) {
        // 群号
        Long groupId = event.getGroupId();
        // 发送人QQ号
        long qq = event.getGroupSender().getId();
        // 发送人昵称
        String nickname = event.getSender().getInfo().getNickname();
        // 信息
        String message = event.getMessage().trim();
        // 如果触发井字棋 询问是否开启对局 如果开启对局 就新建一个 Tictactoe根据群号存到Map
        if ("井字棋".equals(message)) {
            // TODO 判断重复开始对局。
            // 添加群号到游戏组
            tictactoeMap.put(groupId, null);
            // 设置超时时间为一分钟  如果一分钟内没有给出回应就删除对局
            // 如果未回复，则删除groupIds对应的群号
            event.respond("是否要开始井字棋，请回复 开始井字棋");
        }

        if (tictactoeMap.containsKey(groupId)) {
            // 如果这个群号在预备开始的游戏组中 并且触发开始井字棋
            if ("开始井字棋".equals(message)) {
                // 如果有
                // 实例化对象 将群号和对象保存到map
                Tictactoe tictactoe = new Tictactoe();
                tictactoeMap.put(groupId, tictactoe);
                event.respond("对局已创建，请玩家输入加入井字棋，加入对局");
            }
            if ("加入井字棋".equals(message)) {
                // 获取对应群组的棋盘对象 判断玩家数量 如果不足则添加
                Tictactoe tictactoe = tictactoeMap.get(groupId);
                // 判断两个参赛人员
                if (tictactoe.getPlayers() == 2) {
                    event.respond("该次对局玩家数已满");
                } else if (tictactoe.getPlayers() == 1) {
                    // 后加入的为O
                    tictactoe.setOQ(qq);
                    event.respond(nickname + "加入了对局" + "您使用O");
                } else {
                    // 先加入的为X
                    tictactoe.setXQ(qq);
                    event.respond(nickname + "加入了对局" + "您使用X");
                }
                tictactoe.setPlayers(tictactoe.getPlayers() + 1);
                tictactoeMap.put(groupId, tictactoe);
                String[][] chessboard = tictactoe.getChessboard();
                if (tictactoe.getPlayers() == 2) {
                    // 游戏开始
                    tictactoe.setStartFlag(true);
                    event.respond(new MessageBuilder().
                            add("游戏开始咯").
                            newLine().
                            add(ArrayUtils.printArray(chessboard)).
                            toString());
                }
            }

            if (message.matches("下[1-9]")) {
                // 根据参赛人员的发言来改变棋盘 直至满足获胜或者平局规则
                if (qq == tictactoeMap.get(groupId).getOQ()) {
                    // 下5
                    char c = message.charAt(message.length() - 1);
                    Integer qiWei = Integer.valueOf(String.valueOf(c));
                    Tictactoe tictactoe = tictactoeMap.get(groupId);
                    // 判断是否能走
                    if(tictactoe.getStepCount() % 2 == 1){
                        event.respond("稍等一下啊，还没到你走呢");
                        return;
                    }
                    String[][] chessboard = tictactoe.getChessboard();
                    int hang = 0;
                    int lie = qiWei - 1;
                    while (lie - 3 >= 0) {
                        lie -= 3;
                        hang++;
                    }
                    if ("X".equals(chessboard[hang][lie]) || "O".equals(chessboard[hang][lie])) {
                        event.respond("该位置已经有棋子");
                        return;
                    }
                    chessboard[hang][lie] = "O";
                    tictactoe.setChessboard(chessboard);
                    tictactoe.setStepCount(tictactoe.getStepCount() + 1);
                    try {
                        if (judgeGame(chessboard)){
                            event.respond(nickname + "胜利~~");
                            // 展示最终的棋盘
                            event.respond(new MessageBuilder().
                                    add(ArrayUtils.printArray(chessboard)).
                                    toString());
                            // 删除游戏对局
                            tictactoeMap.remove(groupId);
                            return;
                        }
                    } catch (Exception e) {
                        event.respond(new MessageBuilder().
                                add("平局啦").
                                newLine().
                                add(ArrayUtils.printArray(chessboard)).
                                toString());
                        tictactoeMap.remove(groupId);
                    }
                    event.respond(new MessageBuilder().
                            add(ArrayUtils.printArray(chessboard)).
                            toString());
                }
                if (qq == tictactoeMap.get(groupId).getXQ()) {
                    // 下3
                    char c = message.charAt(message.length() - 1);
                    Integer qiWei = Integer.valueOf(String.valueOf(c));
                    Tictactoe tictactoe = tictactoeMap.get(groupId);
                    // 判断是否能走
                    if(tictactoe.getStepCount() % 2 == 0){
                        event.respond("稍等一下啊，还没到你走呢");
                        return;
                    }
                    String[][] chessboard = tictactoe.getChessboard();
                    int hang = 0;
                    int lie = qiWei - 1;
                    while (lie - 3 >= 0) {
                        lie -= 3;
                        hang++;
                    }
                    if ("X".equals(chessboard[hang][lie]) || "O".equals(chessboard[hang][lie])) {
                        event.respond("该位置已经有棋子");
                        return;
                    }
                    chessboard[hang][lie] = "X";
                    tictactoe.setChessboard(chessboard);
                    tictactoe.setStepCount(tictactoe.getStepCount() + 1);
                    try {
                        if (judgeGame(chessboard)){
                            event.respond(nickname + "胜利~~");
                            // 展示最终的棋盘
                            event.respond(new MessageBuilder().
                                    add(ArrayUtils.printArray(chessboard)).
                                    toString());
                            // 删除游戏对局
                            tictactoeMap.remove(groupId);
                            return;
                        }
                    } catch (Exception e) {
                        event.respond(new MessageBuilder().
                                add("平局啦").
                                newLine().
                                add(ArrayUtils.printArray(chessboard)).
                                toString());
                        tictactoeMap.remove(groupId);
                    }
                    event.respond(new MessageBuilder().
                            add(ArrayUtils.printArray(chessboard)).
                            toString());
                }
            }

        }
    }
    // 判断获胜或平局的方法
    private boolean judgeGame(String chessboard[][]){
        // 判断是否有连续的横向相同的/纵向相同的/斜向相同的 如果有就返回true
        int hang = 0;
        int lie = 0;
        boolean tempFlag = true;
        // 平局 棋盘全满了
        for (String[] strings : chessboard) {
            for (String string : strings) {
                if(!"X".equals(string)&&!"Y".equals(string)){
                    throw new RuntimeException("平局");
                }
            }
        }
        // 第一种赢法 ***
        for (int i = 0; i < chessboard[0].length; i++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[0][0].equals(chessboard[0][i])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }
        tempFlag = true;
        // 第二种赢法*
        //          *
        //          *
        for (int i = 0; i < chessboard.length; i++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[0][0].equals(chessboard[i][0])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        tempFlag = true;
        // 第三种赢法 *
        //             *
        //               *
        for (int i = 0,j = 0; i < chessboard[0].length; i++,j++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[0][0].equals(chessboard[i][j])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        tempFlag = true;
        // 第四种赢法*
        //          *
        //          *
        for (int i = 0; i < chessboard.length; i++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[0][1].equals(chessboard[1][i])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        tempFlag = true;
        // 第五种赢法 * * *
        for (int i = 0; i < chessboard[1].length; i++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[1][0].equals(chessboard[1][i])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        tempFlag = true;
        // 第六种赢法 * * *
        for (int i = 0; i < chessboard[2].length; i++) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[2][0].equals(chessboard[2][i])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        tempFlag = true;
        // 第七种赢法*
        //     *
        //*
        for (int i = 0,j=2; i < chessboard[1].length; i++,j--) {
            // 如果有一个不相等 就不算结束
            if(!chessboard[2][0].equals(chessboard[j][i])){
                tempFlag = false;
            }
        }
        // 判断上面这种赢法
        if(tempFlag) {
            return true;
        }

        return false;
    }
}
