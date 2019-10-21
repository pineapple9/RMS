package com.pineapple.rms.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.pineapple.rms.bean.Order;

public class OrderDAOImpl implements OrderDAO {

    private DataSource dataSource;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                this.dataSource);
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public Collection<Order> findAllOrders() {

        List<Order> orderList = jdbcTemplate.query(
                "SELECT order_id as orderId \n" +
                        ", order_date as orderDate \n" +
                        ", order_total_amount as orderTotalAmount \n" +
                        " FROM rms_orders ",
                ParameterizedBeanPropertyRowMapper.newInstance(Order.class));

        return orderList;

    }

    @Override
    public Collection<Order> findByIds(List<String> ids) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);

        List<Order> orderList = namedParameterJdbcTemplate.query(
                "SELECT * FROM rms_order where order_id IN (:ids)",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Order.class));

        return orderList;

    }

    @Override
    public Order findById(int id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<Order> orderList = namedParameterJdbcTemplate.query(
                "SELECT order_id as orderId \n" +
                        ", order_date as orderDate \n" +
                        ", order_total_amount as orderTotalAmount \n" +
                        " FROM rms_orders where order_id = :id ",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Order.class));

        System.out.println("orderList :"+orderList);

        return orderList.size()<0?null:orderList.get(0);

    }

}