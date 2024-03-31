package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    /**
     * 在Java中，@Resource是Java的一个注解，用于在没有明确指定bean名称的情况下，按名称或类型自动装配bean。在这个例子中，@Resource注解被用于自动装配PayMapper类的实例。  private PayMapper payMapper;这行代码定义了一个私有的PayMapper类型的变量payMapper。PayMapper可能是一个接口，用于定义与数据库交互的方法，例如查询、插入、更新和删除支付记录。  所以，@Resource private PayMapper payMapper;这行代码的作用是创建一个PayMapper的实例，然后可以在PayServiceImpl类中使用这个实例来调用定义在PayMapper接口中的方法，以实现与数据库的交互。
     */
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
