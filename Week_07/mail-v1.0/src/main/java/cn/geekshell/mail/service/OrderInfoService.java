package cn.geekshell.mail.service;

import java.util.Map;

public interface OrderInfoService {
    void insert(Map<String,Object> orderInfo);
    Map<String,Object> queryByInfoId(int infoId);
}
