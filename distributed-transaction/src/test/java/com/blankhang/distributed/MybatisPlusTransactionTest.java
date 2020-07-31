package com.blankhang.distributed;

import com.blankhang.distributed.entity.master.User;
import com.blankhang.distributed.entity.second.UserOrder;
import com.blankhang.distributed.mapper.master.UserMapper;
import com.blankhang.distributed.mapper.second.UserOrderMapper;
import com.blankhang.distributed.service.master.UserService;
import com.blankhang.distributed.service.second.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 * 测试方法中运行的所有事务都会回滚
 *
 * @author blank
 * @date 2020-07-31 下午 2:35
 */
@Slf4j
@SpringBootTest
@Transactional(rollbackOn = Throwable.class)
public class MybatisPlusTransactionTest {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    UserService userService;
    @Autowired
    UserOrderService orderService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserOrderMapper userOrderMapper;

    @Test
    public void sqlSessionTemplateTest() {
        User user = sqlSessionTemplate.selectOne("com.blankhang.distributed.mapper.master.UserMapper.selectById", 1);
        log.info("user->: {}", user);
    }

    @Test
    public void userServiceTest() {
        User user = userService.getById(1);
        log.info("user->: {}", user);
    }

    @Test
    public void userMapperTest() {
        User user = userMapper.selectById(1);
        log.info("user->: {}", user);
    }

    @Test
    public void userSave() {
        User user = new User();
        user.setUserName("blank");
        user.setAge(29);
        user.setBirthday(LocalDate.of(1991, Month.JANUARY, 17));
        user.setRemark(RandomStringUtils.randomAlphanumeric(4));
        userService.save(user);
    }


    @Test
    public void sqlSessionTemplateTest2() {
        UserOrder userOrder = sqlSessionTemplate.selectOne("com.blankhang.distributed.mapper.second.UserOrderMapper.selectById", 1);
        log.info("userOrder->: {}", userOrder);
    }

    @Test
    public void orderServiceTest() {
        UserOrder userOrder = orderService.getById(1);
        log.info("userOrder->: {}", userOrder);
    }

    @Test
    public void orderMapperTest() {
        UserOrder userOrder = userOrderMapper.selectById(1);
        log.info("userOrder->: {}", userOrder);
    }

    @Test
    public void testTX() {
        log.error(" tx test starting ------->");
        User user = new User();
        user.setUserName("blank-2");
        user.setAge(22);
        user.setBirthday(LocalDate.of(1990, Month.JANUARY, 22));
        user.setRemark("222");
        userService.save(user);

        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(user.getId());
        userOrder.setAmount(BigDecimal.valueOf(222));
        userOrder.setRemark("order 222 mark");
        userOrderMapper.insert(userOrder);

        int te = 1 / 0;

        log.error(" tx test end ------->");
    }
}
