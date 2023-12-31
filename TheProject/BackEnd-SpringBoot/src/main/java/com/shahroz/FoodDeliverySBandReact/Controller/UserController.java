package com.shahroz.FoodDeliverySBandReact.Controller;

import com.shahroz.FoodDeliverySBandReact.Services.CategoryService;
import com.shahroz.FoodDeliverySBandReact.Services.FooditemService;
import com.shahroz.FoodDeliverySBandReact.Services.OrdersService;
import com.shahroz.FoodDeliverySBandReact.Services.UserService;
import com.shahroz.FoodDeliverySBandReact.entities.Category;
import com.shahroz.FoodDeliverySBandReact.entities.User;
import com.shahroz.FoodDeliverySBandReact.entities.fooditem;
import com.shahroz.FoodDeliverySBandReact.entities.orders;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FooditemService fooditemService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/users")
    public List<User> getUser(){
        return userService.getUsers();
    }

    @PostMapping("/current-user")
    public User getLoggedInUser(Principal principal){

        System.out.println("Returning the user");

        return userService.findByEmail(principal.getName());
    }

    // this will find the user by his username
    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable String email ){
        System.out.println("CHECKING YES " + email);
        return userService.findByEmail(email);
    }
    //Getting all the food items a cateogyr holds
    @GetMapping("/home/FoodItems/{category_name}/ShowAll")
    public List<fooditem> getAllFoodItems( @PathVariable String category_name){

        System.out.println("Getting food items of " + category_name);

        System.out.println("Getting food items");
        System.out.println("Finding by " + category_name);
        Long categoryid= categoryService.FindByName(category_name);
        Category category = categoryService.findById(categoryid);

        System.out.println(category.getName());


        return fooditemService.findByCategory(category);
    }

    @PostMapping("/home/FoodItems/AddItem")


    // Generating all the cateogures
    @GetMapping("/home/CategoriesUser")
    public List<Category> getAllCategories(){
        System.out.println("Finding categories from User");
        return categoryService.getAllCategory();
    }



// Generating an order id as soon as user logs in:
    @PostMapping("/home/GenerateOrder/{username}")
    public ResponseEntity<orders> createOrder(@AuthenticationPrincipal Principal principal,@PathVariable String username){
  //      if(principal!=null){
            orders neworder = new orders();
            System.out.println("We are creating order for this user : " + username);
            neworder.setUser(userService.findByEmail(username));
            neworder.setStatus("Pending");
            neworder.setDatetime(new Date());

            // Attempt to create the order
            orders createdOrder = ordersService.createOrder(neworder);

            if (createdOrder != null) {
                // Order was successfully created

                return ResponseEntity.ok(createdOrder);
            } else {
                // Handle the case where order creation failed
                System.out.println("Error in creating a new order");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
      //  }
   //     else{
   //         System.out.println("Principle not found!!");
   //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //    }

    }
    @Transactional
    @PutMapping("/update-user/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable String userid){
        Long user_id = Long.valueOf(userid);

        return userService.updateUser(user_id,user);


    }


}
