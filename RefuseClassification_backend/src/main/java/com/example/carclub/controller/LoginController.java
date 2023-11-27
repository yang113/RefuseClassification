package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.common.Result;
import com.example.carclub.entity.Information;
import com.example.carclub.entity.Money;
import com.example.carclub.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/information")
@RestController
public class LoginController {


    @Autowired
    private InformationService informationService;

    @GetMapping("/getinfo")
    public IPage<Information> findpage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String city)
    {
        System.out.println(pageSize);
        IPage<Information> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        if (!"".equals(city))
            queryWrapper.like("city",city);
        return informationService.page(page,queryWrapper);
    }

    @PostMapping
    public boolean save(@RequestBody Information information){
        return informationService.saveOrUpdate(information);
    }

    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return informationService.removeById(id);
    }


}
