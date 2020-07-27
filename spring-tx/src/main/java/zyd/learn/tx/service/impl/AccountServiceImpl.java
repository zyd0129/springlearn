package zyd.learn.tx.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zyd.learn.tx.dao.entity.AccountDO;
import zyd.learn.tx.dao.mapper.AccountMapper;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public void deductive(Integer id, Integer money) {

        AccountDO accountDO = accountMapper.selectById(id);
    }
}
