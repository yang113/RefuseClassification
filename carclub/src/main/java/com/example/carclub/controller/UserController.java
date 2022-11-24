package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Employee;
import com.example.carclub.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public boolean save(@RequestBody Employee employee){
        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return employeeService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return employeeService.removeByIds(ids);
    }




    @GetMapping("/page")
    public IPage<Employee> findpage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String Enum,
                                    @RequestParam(defaultValue = "") String Ename,
                                    @RequestParam(defaultValue = "") String Depnum)
    {
        System.out.println(pageSize);
        IPage<Employee> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (!"".equals(Enum))
            queryWrapper.like("Enum",Enum);
        if (!"".equals(Ename))
            queryWrapper.like("Ename",Ename);
        if (!"".equals(Depnum))
            queryWrapper.like("Depnum",Depnum);
        return employeeService.page(page,queryWrapper);
    }
}
