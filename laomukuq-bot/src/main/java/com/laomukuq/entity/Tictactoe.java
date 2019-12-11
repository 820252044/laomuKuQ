package com.laomukuq.entity;

/**
 * @author laomu
 * @version 1.0
 * @description 井字棋
 * @date 2019-12-11
 */
public class Tictactoe {
    // 井字棋 棋盘  先手X 后手O
    private String chessboard[][] = new String[][]{{"①","②","③"},{"④","⑤","⑥"},{"⑦","⑧","⑨"}};

    // 开始标志
    private boolean startFlag = false;
    // 结束标志
    // private boolean endFlag = !startFlag;
    // 步数  来判断该谁走
    private int stepCount = 1;
    // 讨论组/群号
    // private Long groupNum;
    // 玩家数量
    private int players = 0;
    // X方QQ号
    private Long XQ;
    // O方QQ号
    private Long OQ;

    public String[][] getChessboard() {
        return chessboard;
    }

    public void setChessboard(String[][] chessboard) {
        this.chessboard = chessboard;
    }

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public Long getXQ() {
        return XQ;
    }

    public void setXQ(Long XQ) {
        this.XQ = XQ;
    }

    public Long getOQ() {
        return OQ;
    }

    public void setOQ(Long OQ) {
        this.OQ = OQ;
    }
}
