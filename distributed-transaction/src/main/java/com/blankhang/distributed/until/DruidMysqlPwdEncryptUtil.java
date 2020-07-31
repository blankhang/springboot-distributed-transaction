package com.blankhang.distributed.until;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;

/**
 * mysql druid 连接密码加密密钥生成工具
 *
 * @author blank
 * @date 2020-06-16 下午 12:00
 */
@Slf4j
public class DruidMysqlPwdEncryptUtil {


    public static void main(String[] args) throws Exception {
        ConfigTools.main(new String[]{"pS4Jy3bG6kdLDTbDLYVj"});
    }
}
