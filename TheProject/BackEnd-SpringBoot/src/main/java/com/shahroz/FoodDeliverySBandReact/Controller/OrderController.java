package com.shahroz.FoodDeliverySBandReact.Controller;

import com.shahroz.FoodDeliverySBandReact.Services.OrdersService;
import com.shahroz.FoodDeliverySBandReact.Services.UserService;
import com.shahroz.FoodDeliverySBandReact.entities.User;
import com.shahroz.FoodDeliverySBandReact.entities.orders;
import com.shahroz.FoodDeliverySBandReact.entities.payment;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrdersService ordersService;
//    @PutMapping("/{orderid}/{total}/update-subtotal")
//    public RequestEntity<?> UpdateSubtotal(@PathVariable String orderid,@PathVariable String total)
//    {
//
//        Long order_id = Long.valueOf(orderid);
//        Double totals = Double.valueOf(total);
//        try{
//
//            System.out.println("Updating subtotal");
//
//
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//    }


    @PutMapping("/updatePayment/{orderid}")
    public ResponseEntity<orders> UpdatePayment(@PathVariable String orderid, @RequestBody payment payment) {
        Long order_id = Long.valueOf(orderid);

        orders order = ordersService.findById(order_id);

        if (order != null) {
            order.setPayment(payment);
            order.setStatus("In Progress");
            ordersService.createOrder(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/updateStatus/{orderid}")
    public ResponseEntity<orders> UpdateStatus(@PathVariable String orderid) {
        Long order_id = Long.valueOf(orderid);

        orders order = ordersService.findById(order_id);
        System.out.println("Updating order status");
        if (order != null) {
            order.setStatus("Dispatched");
            ordersService.createOrder(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{orderid}")
public orders getOrderByID(@PathVariable String orderid){
        Long order_id = Long.valueOf(orderid);

        return ordersService.findById(order_id);
    }
    @Autowired
    UserService userService;

    @GetMapping("previousorders/{userid}")
    public List<orders> findByUser(@PathVariable String userid)
    {

        System.out.println("Finding orders by user id " + userid);
        Long user_id = Long.valueOf(userid);
        User user = userService.findById(user_id);


        return ordersService.findByUser(user);
    }

}
