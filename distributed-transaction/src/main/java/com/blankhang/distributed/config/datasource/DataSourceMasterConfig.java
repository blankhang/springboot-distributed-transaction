package com.blankhang.distributed.config.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.blankhang.distributed.config.mybatisplus.MyLogicSqlInjector;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * master数据源配置
 *
 * @author blank
 * @date 2020-07-08 下午 7:12
 */
@Configuration
@MapperScan(basePackages = "com.blankhang.distributed.mapper.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class DataSourceMasterConfig {

    @Value("${spring.datasource.type:com.alibaba.druid.pool.xa.DruidXADataSource}")
    private String xaDataSourceClassName;

    @SneakyThrows
    @Primary
    @Bean(name = "masterDataSource")
    @DependsOn({"txManager"})
    public DataSource masterDataSource(DataSourceMasterProperties  properties) {

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        //DataSource不能直接使用Druid提供的DruidDataSource, 需要使用atomikos来包装一下Druid提供的DruidXADataSource，来支持XA规范
        //see https://juejin.im/post/5e186601e51d4530591783ec
        DruidXADataSource druid = new DruidXADataSource();

        String connectionProperties = properties.getConnectionProperties();
        if (StringUtils.isNotBlank(connectionProperties)) {
            //不为空说明密码是加密的需要配置否则加密密码无法解析
            druid.setConnectionProperties(connectionProperties);
        }
        druid.setName(properties.getName());
        druid.setUrl(properties.getUrl());
        druid.setUsername(properties.getUsername());
        druid.setPassword(properties.getPassword());
        druid.setInitialSize(properties.getInitialSize());
        druid.setMinIdle(properties.getMinIdle());
        druid.setMaxActive(properties.getMaxActive());
        druid.setMaxWait(properties.getMaxWait());
        druid.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        druid.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
        druid.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druid.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druid.setValidationQuery(properties.getValidationQuery());
        druid.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druid.setTestWhileIdle(properties.getTestWhileIdle());
        druid.setTestOnBorrow(properties.getTestOnBorrow());
        druid.setTestOnReturn(properties.getTestOnReturn());
        druid.setFilters(properties.getFilters());


        atomikosDataSourceBean.setXaDataSource(druid);
        atomikosDataSourceBean.setXaDataSourceClassName(xaDataSourceClassName);
        atomikosDataSourceBean.setUniqueResourceName(properties.getName());
        atomikosDataSourceBean.setPoolSize(10);
        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(10);
        return atomikosDataSourceBean;
    }


    @Primary
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // 数据库下划线转驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        factoryBean.setConfiguration(configuration);
        //指定xml路径.
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master/*Mapper.xml"));
        factoryBean.setPlugins(
                //分页插件
                new PaginationInterceptor(),
                //乐观锁插件
                new OptimisticLockerInterceptor()
        );

        GlobalConfig globalConfig = new GlobalConfig();
        // 自定义底层mapper通用方法
        globalConfig.setSqlInjector(masterMyLogicSqlInjector());
        factoryBean.setGlobalConfig(globalConfig);

        return factoryBean.getObject();
    }

    @Primary
    @Bean("masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    /**
//     * master事务管理器
//     */
//    @Primary
//    @Bean(name = "masterTransactionManager")
//    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }


    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法
     */
    @Primary
    @Bean("masterMyLogicSqlInjector")
    public MyLogicSqlInjector masterMyLogicSqlInjector() {
        return new MyLogicSqlInjector();
    }

}
