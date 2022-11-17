package com.muller.community;

import com.muller.community.service.AlphaService;
import com.muller.community.dao.AlphaDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;//applicationContext是String容器类型
    }

    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext);

        //调用接口或实现类的bean都可以，用接口可以解耦，就算增加Bean，调用这里不用改动
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void testBeanManagement() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired
    @Qualifier("AlphaDaoHibernate")
    private AlphaDao alphaDao;

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI() {
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);
    }
}
