package com.blankhang.distributed.config.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.blankhang.distributed.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义全局底层 mapper
 * 可添加自定义操作 如全部插入
 *
 * @author blank
 * @date 2020-4-7 下午 12:20
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 删除全部通用方法
     *
     * @return java.lang.Integer
     * @author blank
     * @date 2020-07-08 下午 3:20
     */
    Integer deleteAll();

    /**
     * 保存所有字段
     *
     * @param entity 要保存的实体
     * @return int
     * @author blank
     * @date 2020-07-08 下午 3:21
     */
    int insertAll(T entity);

    /**
     * 如果要自动填充，@{@code Param}(xx) xx参数名必须是 list/collection/array 3个的其中之一
     *
     */
    int insertAllBatch(@Param("list") List<T> batchList);


    /**
     * 分页的时候使用，当页的记录
     * @param entity
     * @return
     */
    <D extends BaseEntity>List<D> list(D entity);


    /**
     * 分页的时候使用， 总数
     * @param entity
     * @return
     */
    <D extends BaseEntity> long count(D entity);

    /**
     * 以下定义的 4个 method 其中 3 个是内置的选装件
     */
    int insertBatchSomeColumn(List<T> entityList);

    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);

    int deleteByIdWithFill(T entity);

}
