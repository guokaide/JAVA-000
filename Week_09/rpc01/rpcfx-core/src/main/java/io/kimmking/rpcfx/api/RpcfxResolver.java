package io.kimmking.rpcfx.api;

// 寻址：ServiceClass -> Service 实现
public interface RpcfxResolver {

    Object resolve(String serviceClass);

}
