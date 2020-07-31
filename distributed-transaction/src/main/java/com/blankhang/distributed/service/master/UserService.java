package com.blankhang.distributed.service.master;

import com.blankhang.distributed.entity.master.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author blank
 * @since 2020-07-29
 */
public interface UserService extends IService<User> {

    public void testTX();
}
