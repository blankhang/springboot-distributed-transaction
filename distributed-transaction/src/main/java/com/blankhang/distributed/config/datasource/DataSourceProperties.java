package com.blankhang.distributed.config.datasource;

import lombok.Data;

/**
 * 数据库参数类
 *
 * @author blank
 * @date 2020-07-23 上午 8:44
 */
@Data
public class DataSourceProperties {

    private String name;
    private String url;
    private String username;
    private String password;
    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxWait;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Integer validationQueryTimeout;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private String filters;
    private String connectionProperties;

}
