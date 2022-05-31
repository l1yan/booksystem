package com.example.client.feign;

import com.example.client.entity.Order;
import com.example.client.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order")
public interface OrderFeign {

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);

    @GetMapping("/order/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAllByUid(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("uid") long uid);

    @GetMapping("/order/findAllByState/{index}/{limit}")
    public OrderVO findAllByState(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/order/updateState/{id}")
    public void updateState(@PathVariable("id") long id);
}
