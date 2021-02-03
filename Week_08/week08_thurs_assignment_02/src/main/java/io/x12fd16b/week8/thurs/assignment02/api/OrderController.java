package io.x12fd16b.week8.thurs.assignment02.api;

import io.x12fd16b.week8.thurs.assignment02.common.OrderDTO;
import io.x12fd16b.week8.thurs.assignment02.common.enums.OrderStatus;
import io.x12fd16b.week8.thurs.assignment02.common.vo.CancelOrderReqVo;
import io.x12fd16b.week8.thurs.assignment02.common.vo.DeleteOrderReqVo;
import io.x12fd16b.week8.thurs.assignment02.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Order Controller
 *
 * @author David Liu
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    
    @PostMapping("create")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
    }
    
    @PostMapping("prepareDAOResource")
    public void prepareDAOResource() {
        orderService.prepareDAOResource();
    }
    
    @PostMapping("destroyDAOResource")
    public void destroyDAOResource() {
        orderService.destroyDAOResource();
    }
    
    @PostMapping("cancel")
    public void cancelOrder(@RequestBody CancelOrderReqVo reqVo) {
        orderService.updateOrderStatus(reqVo.getUserId(), reqVo.getOrderId(), OrderStatus.CANCEL);
    }
    
    @PostMapping("delete")
    public void deleteOrder(@RequestBody DeleteOrderReqVo reqVo) {
        orderService.deleteOrder(reqVo.getUserId(), reqVo.getOrderId());
    }
    
    @GetMapping("userOrders")
    public List<OrderDTO> getUserOrders(@RequestParam("userId") Long userId) {
        return orderService.getUserOrders(userId);
    }
    
}
