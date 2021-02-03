package cn.geekshell.mail.service.impl;

import cn.geekshell.mail.mapper.IOrderInfoMapper;
import cn.geekshell.mail.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private IOrderInfoMapper orderInfoMapper;


    @Override
    public Map<String, Object> queryByInfoIdAndMaster(int infoId) {
        return orderInfoMapper.queryByInfoId(infoId);
    }

    @Override
    public Map<String, Object> queryByInfoIdAndslave(int infoId) {
        return orderInfoMapper.queryByInfoId(infoId);
    }
}
