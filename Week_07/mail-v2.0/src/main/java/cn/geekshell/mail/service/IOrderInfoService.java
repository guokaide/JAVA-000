package cn.geekshell.mail.service;

import java.util.Map;

public interface IOrderInfoService {
    Map<String,Object> queryByInfoIdAndMaster(int infoId);
    Map<String,Object> queryByInfoIdAndslave(int infoId);
}
