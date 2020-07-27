package zyd.learn.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zyd.learn.tx.dao.mapper.OrderLogMapper;

@Transactional
public class OrderLogServiceImpl {

    @Autowired
    OrderLogMapper logMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertOrderLog() {

    }
}
