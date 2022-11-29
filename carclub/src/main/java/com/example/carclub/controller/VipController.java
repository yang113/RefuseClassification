package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Travel;
import com.example.carclub.entity.Vip;
import com.example.carclub.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private VipService vipService;

    @PostMapping
    public boolean save(@RequestBody Vip vip){
        return vipService.saveOrUpdate(vip);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return vipService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return vipService.removeByIds(ids);
    }


    @GetMapping("/page")
    public IPage<Vip> findpage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String vipnum,
                               @RequestParam(defaultValue = "") String vname,
                               @RequestParam(defaultValue = "") String vipid)
    {
        System.out.println(pageSize);
        IPage<Vip> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Vip> queryWrapper = new QueryWrapper<>();
        if (!"".equals(vipnum))
            queryWrapper.like("vipnum",vipnum);
        if (!"".equals(vname))
            queryWrapper.like("vname",vname);
        if (!"".equals(vipid))
            queryWrapper.like("vipid",vipid);
        return vipService.page(page,queryWrapper);
    }
}
