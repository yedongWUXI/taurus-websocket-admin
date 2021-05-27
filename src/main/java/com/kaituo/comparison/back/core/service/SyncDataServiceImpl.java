package com.kaituo.comparison.back.core.service;

import com.kaituo.comparison.back.core.listener.DataChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2021/5/19 13:42
 * @Modified by:
 */
@Service("syncDataService")
@Slf4j
public class SyncDataServiceImpl implements SyncDataService {

    @Autowired
    ApplicationEventPublisher eventPublisher;


    /**
     * 初始化数据
     *
     *   type暂时还没用到
     * @param type the type
     * @return
     */
    public boolean syncAll(String type) {
        log.info("websocket 初始化数据开始");
        List<String> listDelete = Arrays.asList("1", "2", "3", "4");
        eventPublisher.publishEvent(new DataChangedEvent("init", type, listDelete));
        return true;
    }





    /**
     * 同步删除数据
     *
     *   type暂时还没用到
     * @param type the type
     * @return
     */
    public boolean syncDel(String type) {
        log.info("websocket 同步 删除数据开始");
        List<String> listDelete = Arrays.asList("1", "2", "3", "4");
        eventPublisher.publishEvent(new DataChangedEvent("delete", type, listDelete));
        return true;
    }






}
