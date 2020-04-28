package com.javaee.code.class2.controller;

import com.javaee.code.class2.entity.Homework;
import com.javaee.code.class2.entity.Student;
import com.javaee.code.class2.entity.StudentHomeork;
import com.javaee.code.class2.jdbc.HomeworkJDBC;
import com.javaee.code.class2.jdbc.StudentHomeworkJDBC;
import com.javaee.code.class2.jdbc.StudentJDBC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学生操作控制器
 * 17301092 符永乐
 */

//@ComponentScan("com.javaee.code.class2.*")
@Controller
@RequestMapping("/student/")
public class StudentController {
    ApplicationContext context=new ClassPathXmlApplicationContext("application.xml");

    HomeworkJDBC homeworkJDBC =(HomeworkJDBC)context.getBean(HomeworkJDBC.class);
    StudentJDBC studentJDBC=(StudentJDBC)context.getBean(StudentJDBC.class);
    StudentHomeworkJDBC studentHomeworkJDBC=(StudentHomeworkJDBC)context.getBean(StudentHomeworkJDBC.class);

    @RequestMapping("sLoginPage")//跳转到学生登录界面
    public String toStudentLogin(){
        return "/studentLogin.jsp";
    }

    @RequestMapping("sLogin")//根据学号的姓名登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");
        String name = req.getParameter("name");
        System.out.println(id+name);
        Student s= studentJDBC.selectById(Long.valueOf(id));

        if(s.getId()==Long.valueOf(id)&&s.getName().equals(name)){
            req.getSession().setAttribute("login","1");
            List<Homework> list= homeworkJDBC.selectAll();
            req.setAttribute("hList", list);
            req.setAttribute("student",s);
            req.getRequestDispatcher( "/student/listhomework.jsp").forward(req,resp);
            System.out.println("登陆成功");
        }else {
            req.setAttribute("error","请重新输入学号姓名");
            req.getRequestDispatcher("/studentLogin.jsp").forward(req,resp);
            System.out.println("登陆失败");
        }
    }

    @RequestMapping("submitPage")//跳转到学生提交作业页面
    public void submitPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hwid=req.getParameter("hwID");
        Homework hw=homeworkJDBC.selectById(Long.valueOf(hwid));
        req.setAttribute("ddl",hw.getDeadline());
        req.setAttribute("title",hw.getTitle());
        req.getRequestDispatcher("/student/submitPage.jsp").forward(req,resp);
    }

    @RequestMapping("submit")//学生提交作业
    public void submit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//学生提交作业
        String sid=req.getParameter("sID");
        String content=req.getParameter("hwContent");
        String hwID=req.getParameter("hwID");
        String title=req.getParameter("hwTitle");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long createTime=System.currentTimeMillis();
        Date date=new Date(createTime);
        String submitTime=simpleDateFormat.format(date);


        StudentHomeork shw=new StudentHomeork(0,Long.valueOf(sid),Long.valueOf(hwID),title,content,submitTime);
        if(studentHomeworkJDBC.submit(shw)){
            req.setAttribute("message","提交成功");
        }else {
            req.setAttribute("message","提交失败");
        }
        Student s= studentJDBC.selectById(Long.valueOf(sid));
        List<Homework> list= homeworkJDBC.selectAll();
        req.setAttribute("hList", list);
        req.setAttribute("student",s);
        req.getRequestDispatcher("/student/listhomework.jsp").forward(req,resp);
    }
}
