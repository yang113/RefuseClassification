package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.common.Result;
import com.example.carclub.entity.Classification;
import com.example.carclub.entity.Information;
import com.example.carclub.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/classification")
@RestController
public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;

    @GetMapping("/getclass")
    public IPage<Classification> findpage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "") String min_class,
                                       @RequestParam(defaultValue = "") String classname,
                                       @RequestParam(defaultValue = "") String handle)
    {
        IPage<Classification> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Classification> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name))
            queryWrapper.like("name",name);
        if (!"".equals(min_class))
            queryWrapper.like("min_class",min_class);
        if (!"".equals(classname))
            queryWrapper.like("classname",classname);
        if (!"".equals(handle))
            queryWrapper.like("handle",handle);
        return classificationService.page(page,queryWrapper);
    }

    @PostMapping
    public boolean save(@RequestBody Classification classification){
        return classificationService.saveOrUpdate(classification);
    }

    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return classificationService.removeById(id);
    }


}
