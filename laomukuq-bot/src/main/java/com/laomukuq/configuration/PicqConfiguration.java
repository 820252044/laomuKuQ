package com.laomukuq.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author laomu
 * @version 1.0
 * @description Pi
 * @date 2019-12-10
 */
@ConfigurationProperties(prefix = "picq")
public class PicqConfiguration {
    /**
     * http端口
     */
    private String socketPort;
    /**
     * 是否输出Debug消息
     */
    private boolean debug;
    /**
     * 是否跳过酷Q版本验证 (不推荐)
     */
    private boolean noVerify;
    /**
     * 是否异步执行指令
     */
    private boolean useAsyncCommands;
    /**
     * 是否启用维护模式
     */
    private boolean maintenanceMode;
    /**
     * 维护模式回复 (设为空就不会回复了)
     */
    private String maintenanceResponse;
    /**
     * 是否自动判断是否开启多账号优化
     */
    private boolean autoMultiAccountOptimizatinos;
    /**
     * 不自动判断的时候是否手动开启多账号优化
     */
    private boolean multiAccountOptimizatinos;
    /**
     * 是否暂停事件
     */
    private String eventPaused;
    /**
     * 是否暂停HTTP接收
     */
    private String httpPaused;
    /**
     * SHA1 验证秘钥 (设置为空就是不验证w)
     */
    private String secret;
    /**
     * Access Token 访问令牌 (设置为空就是不用令牌)
     */
    private String accessToken;
    /**
     * 是否启用限速调用API (需要enable_rate_limited_actions=true)
     */
    private boolean apiRateLimited;
    /**
     * 是否异步调用API
     */
    private boolean apiAsync;
    /**
     * 解析指令的时候用来分割参数的正则
     */
    private String commandArgsSplitRegex;
    /**
     * Logger颜色支持级别 (设为DISABLED就没有颜色了)
     */
    private String colorSupportLevel;
    /**
     * Logger日志路径 (设为空就不输出文件了)
     */
    private String logPath;
    /**
     * Logger日志文件名
     */
    private String logFileName;
    /**
     * 是否输出启动日志
     */
    private boolean logInit;

    public String getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(String socketPort) {
        this.socketPort = socketPort;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isNoVerify() {
        return noVerify;
    }

    public void setNoVerify(boolean noVerify) {
        this.noVerify = noVerify;
    }

    public boolean isUseAsyncCommands() {
        return useAsyncCommands;
    }

    public void setUseAsyncCommands(boolean useAsyncCommands) {
        this.useAsyncCommands = useAsyncCommands;
    }

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public String getMaintenanceResponse() {
        return maintenanceResponse;
    }

    public void setMaintenanceResponse(String maintenanceResponse) {
        this.maintenanceResponse = maintenanceResponse;
    }

    public boolean isAutoMultiAccountOptimizatinos() {
        return autoMultiAccountOptimizatinos;
    }

    public void setAutoMultiAccountOptimizatinos(boolean autoMultiAccountOptimizatinos) {
        this.autoMultiAccountOptimizatinos = autoMultiAccountOptimizatinos;
    }

    public boolean isMultiAccountOptimizatinos() {
        return multiAccountOptimizatinos;
    }

    public void setMultiAccountOptimizatinos(boolean multiAccountOptimizatinos) {
        this.multiAccountOptimizatinos = multiAccountOptimizatinos;
    }

    public String getEventPaused() {
        return eventPaused;
    }

    public void setEventPaused(String eventPaused) {
        this.eventPaused = eventPaused;
    }

    public String getHttpPaused() {
        return httpPaused;
    }

    public void setHttpPaused(String httpPaused) {
        this.httpPaused = httpPaused;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isApiRateLimited() {
        return apiRateLimited;
    }

    public void setApiRateLimited(boolean apiRateLimited) {
        this.apiRateLimited = apiRateLimited;
    }

    public boolean isApiAsync() {
        return apiAsync;
    }

    public void setApiAsync(boolean apiAsync) {
        this.apiAsync = apiAsync;
    }

    public String getCommandArgsSplitRegex() {
        return commandArgsSplitRegex;
    }

    public void setCommandArgsSplitRegex(String commandArgsSplitRegex) {
        this.commandArgsSplitRegex = commandArgsSplitRegex;
    }

    public String getColorSupportLevel() {
        return colorSupportLevel;
    }

    public void setColorSupportLevel(String colorSupportLevel) {
        this.colorSupportLevel = colorSupportLevel;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public boolean isLogInit() {
        return logInit;
    }

    public void setLogInit(boolean logInit) {
        this.logInit = logInit;
    }
}
