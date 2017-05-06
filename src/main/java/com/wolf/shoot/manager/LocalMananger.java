package com.wolf.shoot.manager;

/**
 * Created by jwp on 2017/2/4.
 */

import com.snowcattle.game.executor.event.service.AsyncEventService;
import com.snowcattle.game.executor.update.service.UpdateService;
import com.wolf.shoot.common.constant.Loggers;
import com.wolf.shoot.manager.spring.LocalSpringBeanManager;
import com.wolf.shoot.manager.spring.LocalSpringServiceManager;
import com.wolf.shoot.manager.spring.LocalSpringServicerAfterManager;
import com.wolf.shoot.service.IService;
import com.wolf.shoot.service.net.process.GameTcpMessageProcessor;
import com.wolf.shoot.service.net.process.GameUdpMessageOrderProcessor;
import com.wolf.shoot.service.net.process.GameUdpMessageProcessor;
import net.sf.jsqlparser.statement.update.Update;
import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author b053-mac
 *	本地服务管理
 */
public class LocalMananger extends AbstractLocalManager{

    public static LocalMananger instance = new LocalMananger();

    public LocalMananger() {
        services = new LinkedHashMap<Class,Object>(40,0.5f);
    }

    public static LocalMananger getInstance(){
        return instance;
    }

    private LocalSpringServiceManager localSpringServiceManager;

    private LocalSpringBeanManager localSpringBeanManager;

    private LocalSpringServicerAfterManager localSpringServicerAfterManager;

    //因为这里比较常用，单独提取出来
    private GameTcpMessageProcessor gameTcpMessageProcessor;
    private GameUdpMessageOrderProcessor gameUdpMessageOrderProcessor;
    private GameUdpMessageProcessor gameUdpMessageProcessor;
    private UpdateService updateService;
    private AsyncEventService asyncEventService;


    public LocalSpringBeanManager getLocalSpringBeanManager() {
        return localSpringBeanManager;
    }

    public void setLocalSpringBeanManager(LocalSpringBeanManager localSpringBeanManager) {
        this.localSpringBeanManager = localSpringBeanManager;
    }

    public LocalSpringServiceManager getLocalSpringServiceManager() {
        return localSpringServiceManager;
    }

    public void setLocalSpringServiceManager(LocalSpringServiceManager localSpringServiceManager) {
        this.localSpringServiceManager = localSpringServiceManager;
    }

    @Override
    public <T> void add(Object service, Class<T> inter) {
        super.add(service, inter);
        if (service instanceof GameTcpMessageProcessor) {
            this.gameTcpMessageProcessor = (GameTcpMessageProcessor) service;
        } else if (service instanceof GameUdpMessageOrderProcessor) {
            this.gameUdpMessageOrderProcessor = (GameUdpMessageOrderProcessor) service;
        } else if (service instanceof GameUdpMessageProcessor) {
            this.gameUdpMessageProcessor = (GameUdpMessageProcessor) service;
        } else if (service instanceof UpdateService) {
            this.updateService = (UpdateService) service;
        } else if(service instanceof AsyncEventService){
            this.asyncEventService = (AsyncEventService) service;
        }

    }

    public GameTcpMessageProcessor getGameTcpMessageProcessor() {
        return gameTcpMessageProcessor;
    }

    public void setGameTcpMessageProcessor(GameTcpMessageProcessor gameTcpMessageProcessor) {
        this.gameTcpMessageProcessor = gameTcpMessageProcessor;
    }

    public GameUdpMessageOrderProcessor getGameUdpMessageOrderProcessor() {
        return gameUdpMessageOrderProcessor;
    }

    public void setGameUdpMessageOrderProcessor(GameUdpMessageOrderProcessor gameUdpMessageOrderProcessor) {
        this.gameUdpMessageOrderProcessor = gameUdpMessageOrderProcessor;
    }

    public GameUdpMessageProcessor getGameUdpMessageProcessor() {
        return gameUdpMessageProcessor;
    }

    public void setGameUdpMessageProcessor(GameUdpMessageProcessor gameUdpMessageProcessor) {
        this.gameUdpMessageProcessor = gameUdpMessageProcessor;
    }

    public LocalSpringServicerAfterManager getLocalSpringServicerAfterManager() {
        return localSpringServicerAfterManager;
    }

    public void setLocalSpringServicerAfterManager(LocalSpringServicerAfterManager localSpringServicerAfterManager) {
        this.localSpringServicerAfterManager = localSpringServicerAfterManager;
    }

    public UpdateService getUpdateService() {
        return updateService;
    }

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }

    public AsyncEventService getAsyncEventService() {
        return asyncEventService;
    }

    public void setAsyncEventService(AsyncEventService asyncEventService) {
        this.asyncEventService = asyncEventService;
    }
}
