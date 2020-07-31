package com.blankhang.distributed.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author blank
 * @date 2020-07-29 上午 10:23
 */
@Api(tags = "index 接口")
@RestController
public class IndexController {


    /**
     * 首页访问提示
     *
     * @return org.springframework.http.ResponseEntity<?>
     * @author blank
     * @date 2020-07-29 上午 10:24
     */
    @ApiOperation(value = "首页访问提示")
    @GetMapping("/")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("welcome !");
    }

}
