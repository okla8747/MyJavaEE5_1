package com.javaee.code.class2.jdbc;

import com.javaee.code.class2.entity.Homework;
import com.javaee.code.class2.entity.Student;
import com.javaee.code.class2.entity.StudentHomeork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class HomeworkJDBC {

    public boolean addHomework(Homework hw){//向数据库中插入新的作业
        //查询语句
        String sqlString = "insert into s_homework value("+hw.getId()+","+"'"+hw.getTitle()+"'"+","+"'"+hw.getContent()+"'"+","+"'"+hw.getCreateTime()+"'"+","+"'"+hw.getDeadline()+"'"+")";

        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try  {//执行插入语句
                    st.executeUpdate(sqlString);
                    conn.commit();
                    return true;
                }catch (SQLException e){
                    conn.rollback();
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public  List<Homework> selectAll(){//获得全部的作业列列表
        //查询语句
        String sqlString = "select * from s_homework";

        List<Homework> list =new ArrayList<Homework>();
        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sqlString)) {//执行查询语句
                    //打印查询结果
                    while (rs.next()) {
                        Homework hw=new Homework();
                        hw.setId(rs.getLong(1));
                        hw.setTitle(rs.getString(2));
                        hw.setContent(rs.getString(3));
                        hw.setCreateTime(rs.getString(4));
                        hw.setDeadline(rs.getString(5));
                        list.add(hw);
                        System.out.println(hw.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public  Homework selectById(Long id){//根据作业ID获得作业信息
        //查询语句
        String sqlString = "select * from s_homework where id="+id;
        Homework  hw=new Homework();
        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sqlString)) {//执行查询语句
                    //打印查询结果
                    while (rs.next()) {
                        hw.setId(rs.getLong(1));
                        hw.setTitle(rs.getString(2));
                        hw.setContent(rs.getString(3));
                        hw.setCreateTime(rs.getString(4));
                        hw.setDeadline(rs.getString(5));
                        return hw;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hw;
    }
}
