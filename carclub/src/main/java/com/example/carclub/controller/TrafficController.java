package com.example.carclub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Command;
import com.example.carclub.entity.Trafficinfo;
import com.example.carclub.service.TrafficinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic")
public class TrafficController {

    @Autowired
    private TrafficinfoService trafficinfoService;

    @PostMapping
    public boolean save(@RequestBody Trafficinfo trafficinfo){
        return trafficinfoService.saveOrUpdate(trafficinfo);
    }

    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return trafficinfoService.removeById(id);
    }
    @GetMapping("/page")
    public IPage<Trafficinfo> findpage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String serveid,
                                       @RequestParam(defaultValue = "") String servetime)
    {
        System.out.println(pageSize);
        IPage<Trafficinfo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Trafficinfo> queryWrapper = new QueryWrapper<>();
        if (!"".equals(serveid))
            queryWrapper.like("ServeID",serveid);
        if (!"".equals(servetime))
            queryWrapper.like("servetime",servetime);
        return trafficinfoService.page(page,queryWrapper);
    }
}
