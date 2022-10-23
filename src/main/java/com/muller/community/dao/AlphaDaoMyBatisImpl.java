package com.muller.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("MyBatis")
@Primary//优先执行
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String select() {

        return "MyBatis";
    }
}
