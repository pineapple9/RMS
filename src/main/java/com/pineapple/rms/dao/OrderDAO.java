package com.pineapple.rms.dao;

import java.util.Collection;
import java.util.List;
import com.pineapple.rms.bean.Order;

public interface OrderDAO {

    public Collection<Order> findAllOrders();
    public Collection<Order> findByIds(List<String> ids);
    public Order findById(int id);

}