package com.blankhang.distributed.controller;


import com.blankhang.distributed.entity.second.UserOrder;
import com.blankhang.distributed.service.second.UserOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author blank
 * @since 2020-07-29
 */
@Api(tags = "订单 接口")
@RestController
@RequestMapping("//user-order")
public class UserOrderController {

    @Resource
    private UserOrderService orderService;

    @GetMapping()
    @ApiOperation("获取订单列表")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(orderService.list());
    }


    @PostMapping()
    @ApiOperation("保存订单")
    public ResponseEntity<?> save(@RequestBody UserOrder userOrder){
        return ResponseEntity.ok(orderService.save(userOrder));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除订单")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.removeById(id));
    }
}
