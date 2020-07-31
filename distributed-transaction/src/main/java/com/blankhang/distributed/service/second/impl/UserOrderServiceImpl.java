package com.blankhang.distributed.service.second.impl;

import com.blankhang.distributed.entity.second.UserOrder;
import com.blankhang.distributed.mapper.second.UserOrderMapper;
import com.blankhang.distributed.service.second.UserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author blank
 * @since 2020-07-29
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

}
