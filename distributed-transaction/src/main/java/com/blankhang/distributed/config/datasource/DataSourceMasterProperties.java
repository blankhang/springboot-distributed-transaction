package com.blankhang.distributed.config.datasource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * master 数据配置参数
 *
 * @author blank
 * @date 2020-07-22 下午 5:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.master")
public class DataSourceMasterProperties extends DataSourceProperties {

}
