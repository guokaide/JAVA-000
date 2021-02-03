package cn.geekshell.mail.service;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    void insert(Map<String,Object> orderM);

    Map<String,Object> queryById(int id);
}
