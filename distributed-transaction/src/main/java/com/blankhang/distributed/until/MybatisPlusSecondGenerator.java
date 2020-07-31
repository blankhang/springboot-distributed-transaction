package com.blankhang.distributed.until;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis plus  自动代码生成 Second DB 使用
 * 自动基于指定表生成 model mapper controller service
 *
 * @author blank
 * @date 2020-7-29 下午 12:19
 */
public class MybatisPlusSecondGenerator {


    /**
     * 是否生成代码到项目位置
     * 如要生成到D盘这里改成 false 即可
     */
    private static final boolean WRITE_FILE_TO_PROJECT = true;
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
         mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //文件生成目录
        String projectPath = System.getProperty("user.dir");

        if (WRITE_FILE_TO_PROJECT) {
            // 设置代码直接生成在项目中 警告 会覆盖旧有代码!
            gc.setOutputDir(projectPath + "/distributed-transaction/src/main/java");
        } else {
            // 设置代码生成在 D盘
             gc.setOutputDir("D:\\java");
        }
        //生成完成后是否打开目录
        gc.setOpen(false);
        gc.setFileOverride(true);
        //启用swagger2注解
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // .setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor("blank");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sMapper");
        // gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUrl("jdbc:mysql://192.168.2.11:39091/sp_salary_calculator_dev?characterEncoding=utf8&useSSL=false");
//        dsc.setUsername("root");
//        dsc.setPassword("rCgRgLjH99Xvg5BN");

        dsc.setUrl("jdbc:mysql://192.168.1.101:39091/second?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
        dsc.setUsername("root");
        dsc.setPassword("rCgRgLjH99Xvg5BN");
        dsc.setDbQuery(new MySqlQuery(){
            /**
             * 重写父类预留查询自定义字段<br>
             * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
             * 模板中调用：  table.fields 获取所有字段信息，
             * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
             */
            @Override
            public String[] fieldCustom() {
                return new String[]{"DEFAULT"};
            }
        });
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //生成 lombok Model
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);

        //生成 restController
        strategy.setRestControllerStyle(true);
        // 全局大写命名 ORACLE 注意
        // strategy.setCapitalMode(true);
        // 此处可以修改为您的表前缀
        // strategy.setTablePrefix(new String[]{"tlog_", "tsys_"});
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //链式调用
        strategy.setChainModel(false);
        // 需要生成的表

        strategy.setInclude("user_order");

        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
         strategy.setSuperMapperClass("com.blankhang.distributed.config.mybatisplus.MyBaseMapper");
        // 自定义 service 父类
//         strategy.setSuperServiceClass("com.storlead.sc.web.service.BaseService");
        // 自定义 service 实现类父类
//         strategy.setSuperServiceImplClass("com.storlead.sc.web.service.impl.BaseServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        //可自定义修改包名
        pc.setController("com.blankhang.distributed.controller");
        pc.setService("com.blankhang.distributed.service.second");
        pc.setServiceImpl("com.blankhang.distributed.service.second.impl");
        pc.setXml("com.storlead.distributed.mapper.second");
        pc.setMapper("com.blankhang.distributed.mapper.second");
        pc.setEntity("com.blankhang.distributed.entity.second");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarkero
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                if(WRITE_FILE_TO_PROJECT) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/distributed-transaction/src/main/resources/mapper/second/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                } else {
                    //生成到D盘\java 目录
                    return "D:\\java\\com\\blankhang\\mapper\\second\\" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            }
        });

        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */

        //mapper.xml输出路径
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 关闭默认 xml 生成，调整生成 至 自定义目录
        templateConfig.setXml(null);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        //　指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // templateConfig.setController("...");
        // templateConfig.setEntity("");
        // templateConfig.setMapper("...");
        // templateConfig.setXml("...");
        // templateConfig.setService("...");
        // templateConfig.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
         mpg.setTemplate(templateConfig);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        //　System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
