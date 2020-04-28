package com.javaee.code.class2.Utils;

import com.javaee.code.class2.entity.Student;
import com.javaee.code.class2.jdbc.HomeworkJDBC;
import com.javaee.code.class2.jdbc.StudentHomeworkJDBC;
import com.javaee.code.class2.jdbc.StudentJDBC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@ComponentScan("com.javaee.code.class2.*")
@EnableAspectJAutoProxy
//测试
public class SpringContextUtils  {
    private static ApplicationContext context;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext co=new AnnotationConfigApplicationContext(SpringContextUtils.class);
        HomeworkJDBC hd = co.getBean("homeworkJDBC",HomeworkJDBC.class);
        hd.selectAll();
    }
}
