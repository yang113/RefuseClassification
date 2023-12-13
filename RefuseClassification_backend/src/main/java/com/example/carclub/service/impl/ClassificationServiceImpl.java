package com.example.carclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carclub.entity.Classification;
import com.example.carclub.service.ClassificationService;
import com.example.carclub.mapper.ClassificationMapper;
import org.springframework.stereotype.Service;

/**
* @author LENOVO
* @description 针对表【classification】的数据库操作Service实现
* @createDate 2023-12-13 22:34:55
*/
@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification>
implements ClassificationService{

}
