package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Department;
import com.example.carclub.entity.Employee;
import com.example.carclub.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public boolean save(@RequestBody Department department){
        return departmentService.saveOrUpdate(department);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return departmentService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return departmentService.removeByIds(ids);
    }




    @GetMapping("/page")
    public IPage<Department> findpage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String Depnum,
                                    @RequestParam(defaultValue = "") String Minister)
    {
        System.out.println(pageSize);
        IPage<Department> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        if (!"".equals(Depnum))
            queryWrapper.like("Depnum",Depnum);
        if (!"".equals(Minister))
            queryWrapper.like("Minister",Minister);
        return departmentService.page(page,queryWrapper);
    }
}
