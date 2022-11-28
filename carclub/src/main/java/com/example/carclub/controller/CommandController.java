package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Command;
import com.example.carclub.entity.Money;
import com.example.carclub.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/command")
public class CommandController {

    @Autowired
    private CommandService commandService;


    @PostMapping
    public boolean save(@RequestBody Command command){
        return commandService.saveOrUpdate(command);
    }


    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return commandService.removeByIds(ids);
    }


    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return commandService.removeById(id);
    }


    @GetMapping("/page")
    public IPage<Command> findpage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String ServeID,
                                   @RequestParam(defaultValue = "") String VIPnum,
                                   @RequestParam(defaultValue = "") String Ctime)
    {
        System.out.println(pageSize);
        IPage<Command> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Command> queryWrapper = new QueryWrapper<>();
        if (!"".equals(ServeID))
            queryWrapper.like("ServeID",ServeID);
        if (!"".equals(VIPnum))
            queryWrapper.like("VIPnum",VIPnum);
        if (!"".equals(Ctime))
            queryWrapper.like("Ctime",Ctime);
        return commandService.page(page,queryWrapper);
    }
}
