# springboot-distributed-transaction
## SpringBoot 2.3 + Druid + MybatisPlus + Atomikos 分布式事务

项目 pom.xml 文件如下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <artifactId>distributed-transaction</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>distributed-transaction</name>
    <description>SpringBoot 2.3 + Druid + MybatisPlus + Atomikos 分布式事务</description>

   <properties>
        <java.version>11</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <druid.version>1.1.23</druid.version>
        <mysql.version>8.0.11</mysql.version>
        <mybatis-plus-boot-starter.version>3.3.2</mybatis-plus-boot-starter.version>

    </properties>


    <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-freemarker</artifactId>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
            </dependency>
    
            <!--  swagger 3.0 开始可以自动装配了 -->
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-boot-starter</artifactId>
                <version>3.0.0</version>
            </dependency>
    
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <!--druid 目前只支持在此版本或以下使用XA事务-->
                <version>${mysql.version}</version>
                <scope>runtime</scope>
            </dependency>
            <!--druid 数据库连接池-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <!--分布式事务-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-jta-atomikos</artifactId>
            </dependency>
            <!-- mybatis-plus https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus-boot-starter.version}</version>
                <exclusions>
                    <exclusion>
                        <artifactId>tomcat-jdbc</artifactId>
                        <groupId>org.apache.tomcat</groupId>
                    </exclusion>
                </exclusions>
            </dependency>
            <!--自动代码生成依赖-->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-generator</artifactId>
                <version>${mybatis-plus-boot-starter.version}</version>
            </dependency>
    
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-configuration-processor</artifactId>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
                <exclusions>
                    <exclusion>
                        <groupId>org.junit.vintage</groupId>
                        <artifactId>junit-vintage-engine</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>jakarta.validation</groupId>
                <artifactId>jakarta.validation-api</artifactId>
                <version>3.0.0</version>
            </dependency>
        </dependencies>

    <build>
        <plugins>
            <!-- 打包时跳过测试 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skip>true</skip>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
<!--依赖仓库配置-->
    <repositories>

        <!--阿里云的 mvn 仓库镜像-->
        <repository>
            <id>aliyun maven</id>
            <url>https://maven.aliyun.com/repository/central</url>
        </repository>

        <!--阿里云的 spring 仓库镜像-->
        <repository>
            <id>aliyun spring</id>
            <url>https://maven.aliyun.com/repository/spring</url>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>

        <!--阿里云的 google 仓库镜像-->
        <repository>
            <id>aliyun google</id>
            <url>https://maven.aliyun.com/repository/google</url>
        </repository>

        <!--阿里云的 jcenter 仓库镜像-->
        <repository>
            <id>aliyum jcenter</id>
            <url>https://maven.aliyun.com/repository/jcenter</url>
        </repository>

        <!--阿里云的 grails-core 仓库镜像-->
        <repository>
            <id>aliyun grails-core</id>
            <url>https://maven.aliyun.com/repository/grails-core</url>
        </repository>

        <!--阿里云的 mapr-public 仓库镜像-->
        <repository>
            <id>aliyun mapr-public</id>
            <url>https://maven.aliyun.com/repository/mapr-public</url>
        </repository>

        <!-- mvnrepository 仓库-->
        <repository>
            <id>mvnrepository</id>
            <url>http://mvnrepository.com</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <!--maven 中央仓库-->
        <repository>
            <id>maven center repo</id>
            <url>http://repo1.maven.org/maven2</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <!--maven 中央仓库2-->
        <repository>
            <id>maven center repo 2</id>
            <url>http://central.maven.org/maven2</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>


        <!--third part repo 第三方依赖仓库-->
        <repository>
            <id>third party repo</id>
            <url>http://jaspersoft.artifactoryonline.com/jaspersoft/third-party-ce-artifacts/</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <!--    转换工具依赖仓库     -->
        <repository>
            <id>alfresco maven repo</id>
            <url>https://artifacts.alfresco.com/nexus/content/groups/public</url>
        </repository>

    </repositories>

    <!--maven插件依赖仓库-->
    <pluginRepositories>

        <!--阿里云的 mvn 仓库镜像-->
        <pluginRepository>
            <id>alimaven</id>
            <url>https://maven.aliyun.com/repository/central</url>
        </pluginRepository>

        <!--阿里云的 spring 仓库镜像-->
        <pluginRepository>
            <id>aliyumspring</id>
            <name>aliyun spring</name>
            <url>https://maven.aliyun.com/repository/spring</url>
        </pluginRepository>

        <!--阿里云的 spring-plugin 仓库镜像-->
        <pluginRepository>
            <id>aliyumspring-plugin</id>
            <url>https://maven.aliyun.com/repository/spring-plugin</url>
        </pluginRepository>

        <!--阿里云的 google 仓库镜像-->
        <pluginRepository>
            <id>aliyumgoogle</id>
            <url>https://maven.aliyun.com/repository/google</url>
        </pluginRepository>

        <!--阿里云的 jcenter 仓库镜像-->
        <pluginRepository>
            <id>aliyumjcenter</id>
            <url>https://maven.aliyun.com/repository/jcenter</url>
        </pluginRepository>

        <!--阿里云的 grails-core 仓库镜像-->
        <pluginRepository>
            <id>aliyumgrails-core</id>
            <url>https://maven.aliyun.com/repository/grails-core</url>
        </pluginRepository>

        <!--阿里云的 mapr-public 仓库镜像-->
        <pluginRepository>
            <id>aliyummapr-public</id>
            <url>https://maven.aliyun.com/repository/mapr-public</url>
        </pluginRepository>

        <!--mvnrepository 仓库-->
        <pluginRepository>
            <id>mvnrepository</id>
            <url>http://mvnrepository.com/</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>

        <!--maven 中央仓库-->
        <pluginRepository>
            <id>maven center repo</id>
            <url>http://repo1.maven.org/maven2</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>

        <!--maven 中央仓库2-->
        <pluginRepository>
            <id>central repo</id>
            <url>http://central.maven.org/maven2/</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

</xml>
```

application.yml 数据库 及事务部分配置如下
```yaml
#开发模式
debug: false

logging:
  level:
    root: info
    com:
      blankhang: debug
      atomikos:
        datasource:
          xa:
            # 事务日志级别
            XAResourceTransaction: debug

server:
  # 服务监听端口
  port: 8700
  servlet:
    # 系统接口前缀
    context-path: /api

spring:
  jta:
    # 事务管理器唯一标识符
    transaction-manager-id: txManager
    log-dir: transaction-logs
    atomikos:
      datasource:
        borrow-connection-timeout: 10000
        min-pool-size: 5
        max-pool-size: 10
      properties:
        # 事务超时时间 300 0000ms 默认10 000ms
        default-jta-timeout: 300000
        max-actives: 50
        max-timeout: 300000
        enable-logging: true
        logBaseDir: transaction-logs
  datasource:
    type: com.alibaba.druid.pool.xa.DruidXADataSource
    druid:
      master:
        name: master
        url: jdbc:mysql://192.168.1.101:39091/master?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&noDatetimeStringSync=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai
        username: root
        # druid 链接密码 加密 需要同时配置 connection-properties filters: config
        password: Z2wvS1kokb4QwXp5vW1BukhEkK+AwOqhGNouGf0eHkw2Q1QL4FqZnhnNrAllzQ6Er4cicAcIP/yj50Pc8F2uIg==
        connection-properties: config.decrypt=true;config.decrypt.key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI+7x/MxFWgNSk2saE3iSoBwdpTbjozCtnvhh/Fk4UF/1tG7S11/uBR7kGnQqfo27ytkb1wJqsmtZ4ImQqzNVosCAwEAAQ==
        initialSize: 10
        minIdle: 10
        maxActive: 100
        maxWait: 60000
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        validationQueryTimeout: 10000
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        statViewServlet:
          enabled: true
          urlPattern: /druid/*
          #login-username: admin
          #login-password: admin
        # 如果是加密密码 则必须配置 filters: config 否则链接会失败
        filters: config,stat,wall,log4j2
      second:
        name: second
        url: jdbc:mysql://192.168.1.101:39091/second?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&noDatetimeStringSync=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai
        username: root
        password: 123456
        initialSize: 10
        minIdle: 10
        maxActive: 100
        maxWait: 60000
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        validationQueryTimeout: 10000
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        statViewServlet:
          enabled: true
          urlPattern: /druid/*
          #login-username: admin
          #login-password: admin
        filters: stat,wall,log4j2
```

DataSourceProperties 数据库配置文件类
```java
package com.blankhang.distributed.config.datasource;

import lombok.Data;

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
```

master 数据源配置参数类
```java
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
```

second 数据源配置参数类
```java
package com.blankhang.distributed.config.datasource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * second数据源参数
 *
 * @author blank
 * @date 2020-07-22 下午 5:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.second")
public class DataSourceSecondProperties extends DataSourceProperties {

}
```

master 主数据源配置
```java
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

```

second 数据源配置
```java
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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * second数据源配置
 *
 * @author blank
 * @date 2020-07-08 下午 7:12
 */
@Configuration
@MapperScan(basePackages = "com.blankhang.distributed.mapper.second", sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class DataSourceSecondConfig {

    @Value("${spring.datasource.type:com.alibaba.druid.pool.xa.DruidXADataSource}")
    private String xaDataSourceClassName;

    @SneakyThrows
    @Bean(name = "secondDataSource")
    @DependsOn({"txManager"})
    public DataSource masterDataSource(DataSourceSecondProperties  properties) {

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


    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // 数据库下划线转驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        factoryBean.setConfiguration(configuration);
        //指定xml路径.
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/second/*Mapper.xml"));
        factoryBean.setPlugins(
                // 分页插件
                new PaginationInterceptor(),
                // 乐观锁插件
                new OptimisticLockerInterceptor()
        );

        GlobalConfig globalConfig = new GlobalConfig();
        // 自定义底层mapper通用方法
        globalConfig.setSqlInjector(secondMyLogicSqlInjector());
        factoryBean.setGlobalConfig(globalConfig);

        return factoryBean.getObject();
    }

    @Bean("secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


//    /**
//     * second事务管理器
//     *
//     */
//    @Bean(name = "secondTransactionManager")
//    public DataSourceTransactionManager secondDataSourceTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法
     */
    @Bean("secondMyLogicSqlInjector")
    public MyLogicSqlInjector secondMyLogicSqlInjector() {
        return new MyLogicSqlInjector();
    }

}

```

druid 及 atomikos 事务配置 
```java
package com.blankhang.distributed.config.datasource;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * druid 及 atomikos 事务配置
 *
 * @author blank
 * @date 2020-07-08 下午 7:11
 */
@EnableTransactionManagement
@Configuration
public class DataSourceConfig {


    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager")
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "txManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
//        servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
        //控制台管理用户，加入下面2行 进入druid后台就需要登录
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin@2020");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    /**
     * spring监控
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
        return druidStatInterceptor;
    }

    /**
     * 使用正则表达式配置切点
     */
    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("com.blnakhang.service.*");
        return pointcut;
    }

    /**
     * DefaultPointcutAdvisor类定义advice及 pointcut 属性。advice指定使用的通知方式，也就是druid提供的DruidStatInterceptor类，pointcut指定切入点
     */
    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setLogSlowSql(true);
        //SQL合并配置
        statFilter.setMergeSql(true);
        //slowSqlMillis的缺省值为3000，也就是3秒。
        statFilter.setSlowSqlMillis(3000);
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }
}
```

当前2张表数据都是空的
```shell script
mysql> select * from master.user;
Empty set (0.00 sec)

mysql> select * from second.user_order;
Empty set (0.00 sec)
```

测试事务
```java
package com.blankhang.distributed.controller;


import com.blankhang.distributed.entity.master.User;
import com.blankhang.distributed.service.master.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author blank
 * @since 2020-07-29
 */
@Api(tags = "用户 接口")
@RestController
@RequestMapping("//user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("tx")
    @ApiOperation("测试分布式事务")
    public ResponseEntity<?> testTX(){
        userService.testTX();
        return ResponseEntity.ok().build();
    }
}
```
```java
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
```
我们可以通过调用 http://localhost:8700/api/user/tx 接口  
或进入 swagger http://localhost:8700/api/swagger-ui/ 展开用户接口 点击测试分布式事务  Try it out => Execute
运行这个测试方法  
其中相关日志如下
```log
2020-07-31 14:37:56.257 ERROR 3904 --- [nio-8700-exec-1] c.b.d.s.master.impl.UserServiceImpl      :  tx test starting ------->
2020-07-31 14:37:56.575  INFO 3904 --- [nio-8700-exec-1] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1,master} inited
2020-07-31 14:37:56.579  INFO 3904 --- [nio-8700-exec-1] c.a.d.xa.XATransactionalResource         : master: refreshed XAResource
2020-07-31 14:37:56.615 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.mapper.master.UserMapper.insert    : ==>  Preparing: INSERT INTO user ( user_name, birthday, age, remark ) VALUES ( ?, ?, ?, ? ) 
2020-07-31 14:37:56.619 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.start ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D31 , XAResource.TMNOFLAGS ) on resource master represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@3e52c6f2
2020-07-31 14:37:56.691 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.mapper.master.UserMapper.insert    : ==> Parameters: blank-2(String), 1990-01-22(LocalDate), 22(Integer), 222(String)
2020-07-31 14:37:56.694 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.mapper.master.UserMapper.insert    : <==    Updates: 1
2020-07-31 14:37:56.788  INFO 3904 --- [nio-8700-exec-1] com.alibaba.druid.pool.DruidDataSource   : {dataSource-2,second} inited
2020-07-31 14:37:56.788  INFO 3904 --- [nio-8700-exec-1] c.a.d.xa.XATransactionalResource         : second: refreshed XAResource
2020-07-31 14:37:56.790 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.m.second.UserOrderMapper.insert    : ==>  Preparing: INSERT INTO user_order ( user_id, amount, remark ) VALUES ( ?, ?, ? ) 
2020-07-31 14:37:56.790 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.start ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D32 , XAResource.TMNOFLAGS ) on resource second represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@45a3aa2
2020-07-31 14:37:56.794 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.m.second.UserOrderMapper.insert    : ==> Parameters: 3(Long), 222(BigDecimal), order 222 mark(String)
2020-07-31 14:37:56.796 DEBUG 3904 --- [nio-8700-exec-1] c.b.d.m.second.UserOrderMapper.insert    : <==    Updates: 1
2020-07-31 14:37:56.799 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.end ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D31 , XAResource.TMSUCCESS ) on resource master represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@3e52c6f2
2020-07-31 14:37:56.801 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.end ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D32 , XAResource.TMSUCCESS ) on resource second represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@45a3aa2
2020-07-31 14:37:56.806 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.rollback ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D31 ) on resource master represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@3e52c6f2
2020-07-31 14:37:56.814 DEBUG 3904 --- [nio-8700-exec-1] c.a.datasource.xa.XAResourceTransaction  : XAResource.rollback ( 3139322E3136382E312E3130302E746D313539363137373437363234373030303031:3139322E3136382E312E3130302E746D32 ) on resource second represented by XAResource instance com.mysql.cj.jdbc.MysqlXAConnection@45a3aa2
2020-07-31 14:37:56.840 ERROR 3904 --- [nio-8700-exec-1] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/api] threw exception [Request processing failed; nested exception is java.lang.ArithmeticException: / by zero] with root cause
```
### 可以看到 出错后 日志中有 2条 rollback 的事务回滚
我们再次查询数据库  
可以看到 2 张表仍然都是空的 数据按预期出错后全部回滚
```shell script
mysql> select * from master.user;
Empty set (0.00 sec)

mysql> select * from second.user_order;
Empty set (0.00 sec)
```
至此事务配置完成  
源码在  https://github.com/blankhang/springboot-distributed-transaction