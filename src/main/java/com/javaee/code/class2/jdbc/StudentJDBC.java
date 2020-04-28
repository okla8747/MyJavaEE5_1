package com.javaee.code.class2.jdbc;

import com.javaee.code.class2.entity.Student;
import com.javaee.code.class2.entity.StudentHomeork;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class  StudentJDBC {

    public  boolean addStudent(Student student){//老师向数据库中添加新的学生信息
        //查询语句
        String sqlString = "insert into s_student value("+student.getId()+","+"\""+student.getName()+"\""+")";

        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try  {//执行插入语句
                    st.executeUpdate(sqlString);
                    conn.commit();//提交事务
                    return true;
                }catch (SQLException e){
                    conn.rollback();//事务回滚
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public  List<Student> selectAll(){//获得学生全部信息
        String sqlString = "select * from s_student";
        List<Student> list =new ArrayList<Student>();
        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sqlString)) {//执行查询语句
                    //打印查询结果
                    while (rs.next()) {
                        Student s=new Student();
                        s.setId(rs.getLong(1));
                        s.setName(rs.getString(2));
                        list.add(s);
                        System.out.println(s.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public  Student selectById(long id){//根据学号查找学生信息
        //查询语句
        String sqlString = "select * from s_student where id="+id;
        Student s=new Student();
        try (Connection conn = DatabasePool.getHikariDataSourse().getConnection()) {//获得数据库连接
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sqlString)) {//执行查询语句
                    //打印查询结果
                    while (rs.next()) {
                        s.setId(rs.getLong(1));
                        s.setName(rs.getString(2));
                        System.out.println(s.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}
