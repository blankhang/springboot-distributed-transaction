package com.blankhang.distributed.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * mybatis plus 自动填充默认值
 * <p>
 * 填充处理器MyMetaObjectHandler在Spring Boot中需要声明@Component或@Bean注入
 * strictInsertFill和strictUpdateFill方法第二个参数写的是实体类里的属性名，不是对应数据库字段名。
 *
 * @author blank
 * @date 2020-07-27 下午 3:57
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("填充默认时间 :{}",LocalDateTime.now());
      this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("填充更新时间 :{}",LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
