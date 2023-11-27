package com.example.carclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carclub.entity.Information;
import com.example.carclub.service.InformationService;
import com.example.carclub.mapper.InformationMapper;
import org.springframework.stereotype.Service;

/**
* @author LENOVO
* @description 针对表【information】的数据库操作Service实现
* @createDate 2023-11-27 21:59:24
*/
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information>
implements InformationService{

}
