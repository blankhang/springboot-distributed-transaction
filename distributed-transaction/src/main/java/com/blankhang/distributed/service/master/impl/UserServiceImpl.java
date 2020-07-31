package com.blankhang.distributed.service.master.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blankhang.distributed.entity.master.User;
import com.blankhang.distributed.entity.second.UserOrder;
import com.blankhang.distributed.mapper.master.UserMapper;
import com.blankhang.distributed.mapper.second.UserOrderMapper;
import com.blankhang.distributed.service.master.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author blank
 * @since 2020-07-29
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserOrderMapper userOrderMapper;

    @Override
    public void testTX(){

        log.error(" tx test starting ------->");
        User user = new User();
        user.setUserName("blank-2");
        user.setAge(22);
        user.setBirthday(LocalDate.of(1990, Month.JANUARY,22));
        user.setRemark("222");
        save(user);

        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(user.getId());
        userOrder.setAmount(BigDecimal.valueOf(222));
        userOrder.setRemark("order 222 mark");
        userOrderMapper.insert(userOrder);

        int te = 1/0;

        log.error(" tx test end ------->");
    }

}
