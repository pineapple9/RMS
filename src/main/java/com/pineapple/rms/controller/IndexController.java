package com.pineapple.rms.controller;

import com.pineapple.rms.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    @ResponseBody
    public String index2(HttpServletRequest request) {
        //Principal principal = request.getUserPrincipal();
        return "Index";
    }

    @RequestMapping(value = "/3", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        int result = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM rms.rms_order;", Integer.class);
        System.out.println("result : "+result);

        return principal.getName();
    }

    @GetMapping("/")
    public ModelAndView index(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name,
    HttpServletRequest request
    ) {
        Principal principal = request.getUserPrincipal();



        String sql = "SELECT order_id as orderId, order_date as orderDate FROM rms.rms_order";

//        List<Order> orders = jdbcTemplate.query(
//                sql,
//                org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper.newInstance(Order.class));

//        System.out.println("Order List : "+orders);
//        System.out.println("Order Size : "+orders.size());
//        model.addAttribute("message", principal.getName());
//        model.addAttribute("orderList",orders);

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
//        model.addObject("order", orders);

        return model; //view
    }

    @GetMapping("/datepicker")
    public ModelAndView index(
    ) {
        ModelAndView model = new ModelAndView("datepicker");
        return model; //view
    }

}
