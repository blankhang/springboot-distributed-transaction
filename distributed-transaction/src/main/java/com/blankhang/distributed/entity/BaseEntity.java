package com.blankhang.distributed.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 对象基类
 * 自带 pageIndex
 * pageSize
 *
 * @author blank
 * @since 2018/5/21 上午11:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8425421111701739962L;

    /**
     * 页码
     */
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageIndex;
    /**
     * 页面大小
     */
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "页面大小", example = "10")
    private Integer pageSize;
    /**
     * 总计
     */
    @TableField(exist = false)
    @JsonIgnore
    @ApiModelProperty(value = "总计")
    private Integer totalCount;


    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
