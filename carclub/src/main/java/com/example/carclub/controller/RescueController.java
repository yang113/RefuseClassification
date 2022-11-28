package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Rescuerecords;
import com.example.carclub.entity.Trafficinfo;
import com.example.carclub.service.RescuerecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rescue")
public class RescueController {

    @Autowired
    private RescuerecordsService rescuerecordsService;


    @PostMapping
    public boolean save(@RequestBody Rescuerecords rescuerecords){
        return rescuerecordsService.saveOrUpdate(rescuerecords);
    }

    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return rescuerecordsService.removeById(id);
    }


    @GetMapping("/page")
    public IPage<Rescuerecords> findpage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize)
    {
        System.out.println(pageSize);
        IPage<Rescuerecords> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Rescuerecords> queryWrapper = new QueryWrapper<>();
        return rescuerecordsService.page(page,queryWrapper);
    }
}
