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

    @GetMapping()
    @ApiOperation("获取用户列表")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("tx")
    @ApiOperation("测试分布式事务")
    public ResponseEntity<?> testTX(){
        userService.testTX();
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    @ApiOperation("保存用户")
    public ResponseEntity<?> save(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.removeById(id));
    }


}
