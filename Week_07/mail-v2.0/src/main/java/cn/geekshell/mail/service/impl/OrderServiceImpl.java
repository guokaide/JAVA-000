package cn.geekshell.mail.service.impl;

import cn.geekshell.mail.mapper.IOrderMapper;
import cn.geekshell.mail.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    
    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public void insert(Map<String, Object> orderM) {
        orderMapper.insert(orderM);
    }

    @Override
    public Map<String, Object> queryById(int id) {
        return orderMapper.queryById(id);
    }
}
