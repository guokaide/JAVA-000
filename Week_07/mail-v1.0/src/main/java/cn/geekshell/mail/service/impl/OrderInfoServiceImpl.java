package cn.geekshell.mail.service.impl;

import cn.geekshell.mail.annotation.ReadOnly;
import cn.geekshell.mail.mapper.IOrderInfoMapper;
import cn.geekshell.mail.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private IOrderInfoMapper orderInfoMapper;


    @Override
    public  void insert(Map<String,Object> orderInfo) {
        orderInfoMapper.insert(orderInfo);
    }

    @ReadOnly(name = "slave")
    @Override
    public Map<String, Object> queryByInfoId(int infoId) {
        return orderInfoMapper.queryByInfoId(infoId);
    }
}
