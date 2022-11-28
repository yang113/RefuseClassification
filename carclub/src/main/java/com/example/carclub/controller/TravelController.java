package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Employee;
import com.example.carclub.entity.Money;
import com.example.carclub.entity.Travel;
import com.example.carclub.service.TrafficinfoService;
import com.example.carclub.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private TravelService travelService;


    @PostMapping
    public boolean save(@RequestBody Travel travel){
        return travelService.saveOrUpdate(travel);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return travelService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return travelService.removeByIds(ids);
    }


    @GetMapping("/page")
    public IPage<Travel> findpage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String travelid,
                                  @RequestParam(defaultValue = "") String vipnum,
                                  @RequestParam(defaultValue = "") String carnum)
    {
        System.out.println(pageSize);
        IPage<Travel> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Travel> queryWrapper = new QueryWrapper<>();
        if (!"".equals(travelid))
            queryWrapper.like("travelid",travelid);
        if (!"".equals(vipnum))
            queryWrapper.like("vipnum",vipnum);
        if (!"".equals(carnum))
            queryWrapper.like("carnum",carnum);
        return travelService.page(page,queryWrapper);
    }

}
