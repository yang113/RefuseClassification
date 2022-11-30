package com.example.carclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carclub.entity.Department;
import com.example.carclub.service.DepartmentService;
import com.example.carclub.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author LENOVO
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-11-30 22:11:42
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




