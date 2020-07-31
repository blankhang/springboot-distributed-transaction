package com.blankhang.distributed.config.mybatisplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * 自定义 SqlInjector
 *
 * @author blank
 * @date 2020-4-7 下午 2:17
 */
public class MyLogicSqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList() 再add
     *
     * @param mapperClass mapperClass
     * @return java.util.List<com.baomidou.mybatisplus.core.injector.AbstractMethod>
     * @author blank
     * @date 2020-4-7 下午 12:18
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        methodList.add(new DeleteAll());
        methodList.add(new InsertAllBatch());
        methodList.add(new InsertBatchSomeColumn());
        methodList.add(new AlwaysUpdateSomeColumnById());

        return methodList;
    }

}
