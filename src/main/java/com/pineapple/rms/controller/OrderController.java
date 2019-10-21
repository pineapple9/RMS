package com.pineapple.rms.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pineapple.rms.bean.Order;
import com.pineapple.rms.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @RequestMapping(value = "/order", method = RequestMethod.GET)
//    @ResponseBody
//    public String initialOrderPage(HttpServletRequest request) {
//        //Principal principal = request.getUserPrincipal();
//        return "Order Page";
//    }

//    @GetMapping("/order")
//    public ModelAndView initialOrderPage() {
//        ModelAndView model = new ModelAndView("order");
//        return model;
//    }

//    @GetMapping("/order")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView initialOrderManagementPage(
//            @RequestParam(name = "name", required = false, defaultValue = "")
//                    String name,
            HttpServletRequest request
    ) {
        Principal principal = request.getUserPrincipal();

//
//        String sql = "SELECT order_id as orderId, order_date as orderDate FROM rms.rms_order";
//
//
//        List<Order> orders = jdbcTemplate.query(
//                sql,
//                new BeanPropertyRowMapper(Order.class));
//
//        System.out.println("Order List : "+orders);
//        System.out.println("Order Size : "+orders.size());
//        model.addAttribute("message", principal.getName());
//        model.addAttribute("orderList",orders);

        ModelAndView model = new ModelAndView("order");

//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "spring-beans.xml");
////
//        OrderDAO orderDAO = (OrderDAO) context.getBean("orderDAO");
//        Collection<Order> orderList = orderDAO.findAllOrders();
////
//        System.out.println("orderList.size() ==> "+orderList.size());
////
//        Gson gson = new Gson();
//        model.addObject("order", gson.toJson(orderList));

        return model; //view
    }

    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    @ResponseBody
//    public Collection<Order> getAllOrder(
    public String getAllOrder(
//            @RequestParam(name = "name", required = false, defaultValue = "")
//                    String name,
            HttpServletRequest request
    ) {

//        ModelAndView model = new ModelAndView("order");

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-beans.xml");

        OrderDAO orderDAO = (OrderDAO) context.getBean("orderDAO");
        Collection<Order> orderList = orderDAO.findAllOrders();

        System.out.println("orderList.size() ==> " + orderList.size());

        for (Order ord : orderList) {
            System.out.println("orderId : " + ord.getOrderId());
            System.out.println("orderDate : " + ord.getOrderDate());
            System.out.println("orderTotalAmount : " + ord.getOrderTotalAmount());
        }

        Gson gson = new Gson();
//        model.addObject("order", gson.toJson(orderList));

//        System.out.println(gson.toJson(orderList));

        return gson.toJson(orderList);
//        return orderList;

//        return "{ demo:[[First Name,Last Name,Address1,Address2],[First Name,Last Name,Address1,Address2]]}";

//        String json = "{ \"demo\":[[\"First Name\",\"Last Name\","+
//                +\"Address1\",\"Address2\"],[\"First Name\",\"Last Name\",\"Address1\",\"Address2\"]]}";
//       return orderList.toString();


//        String json = "{ \"demo\":[[\"First Name\",\"Last Name\","+
//                +\"Address1\",\"Address2\"],[\"First Name\",\"Last Name\",\"Address1\",\"Address2\"]]}";


//        String json = "{ 'order' : [" +
//                "{" +
//                "'orderId': 1," +
//                "'orderDate': 'Oct 10, 2019 10:00:00 AM'," +
//                "'orderTotalAmount': 100" +
//                "}" +
//                "] }";
//        return json;

    }
    @RequestMapping(value = "/getOrderListByParameter", method = RequestMethod.GET)
    public ModelAndView getOrderListByParameter(
            @RequestParam(name = "orderId", required = false) int orderId,
//            @RequestParam(name = "orderDate", required = false) Date orderDate,
            HttpServletRequest request
    ) {
        ModelAndView model = new ModelAndView("order");

//        String sql = "SELECT ord.order_id as orderId" +
////                " , ord.order_date as orderDate " +
////                " FROM rms.rms_order order " +
////                " where 1=1 "+
////                //" [ORDER_ID_CONDITION] "+
////                " [ORDER_DATE_CONDITION] ";
////        MapSqlParameterSource parameter = new MapSqlParameterSource();
////
////                System.out.println("orderId : "+orderId);
////                if(null != orderId) {
////                    sql = sql.replace("[ORDER_ID_CONDITION]"," and ord.order_id = :orderId");
////                    parameter.addValue("orderId",orderId);
////                }else{
////                    sql = sql.replace("[ORDER_ID_CONDITION]"," ");
////                }
////
////                System.out.println("orderDate : "+orderDate);
////                if(null != orderDate) {
////                    sql = sql.replace("[ORDER_DATE_CONDITION]"," and ord.order_date = :orderDate");
////                    parameter.addValue("orderDate",orderDate);
////                }else{
////                    sql = sql.replace("[ORDER_DATE_CONDITION]"," ");
////                }
////
////        System.out.println("sql : "+sql);
////        System.out.println("parameter : "+parameter);
////
////        List<Order> orders = jdbcTemplate.query(
////                sql
////                ,parameter
////                ,BeanPropertyRowMapper.newInstance(Order.class)
////                );
////
////        System.out.println("Order List : "+orders);
////        System.out.println("Order Size : "+orders.size());
//        model.addAttribute("message", principal.getName());
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-beans.xml");

        OrderDAO orderDAO = (OrderDAO) context.getBean("orderDAO");
        Order order = orderDAO.findById(orderId);

        model.addObject("order",order);

        return model; //view
    }

}
