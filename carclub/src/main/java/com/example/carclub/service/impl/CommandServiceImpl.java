package com.example.carclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carclub.entity.Command;
import com.example.carclub.service.CommandService;
import com.example.carclub.mapper.CommandMapper;
import org.springframework.stereotype.Service;

/**
* @author LENOVO
* @description 针对表【command】的数据库操作Service实现
* @createDate 2022-11-24 22:26:56
*/
@Service
public class CommandServiceImpl extends ServiceImpl<CommandMapper, Command>
    implements CommandService{

}




