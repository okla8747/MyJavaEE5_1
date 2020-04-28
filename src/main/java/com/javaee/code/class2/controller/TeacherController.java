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
 * 老师操作控制器
 * 17301092 符永乐
 */
//@ComponentScan("com.javaee.code.class2.*")
@Controller
@RequestMapping("/teacher/")
public class TeacherController {
    ApplicationContext context=new ClassPathXmlApplicationContext("application.xml");

    HomeworkJDBC homeworkJDBC =(HomeworkJDBC)context.getBean(HomeworkJDBC.class);
    StudentJDBC studentJDBC=(StudentJDBC)context.getBean(StudentJDBC.class);
    StudentHomeworkJDBC studentHomeworkJDBC=(StudentHomeworkJDBC)context.getBean(StudentHomeworkJDBC.class);

    @RequestMapping("tLoginPage")//跳转到教师登录界面接口
    public String teacherLoginPage(){
        return "/index.jsp";
    }

    @RequestMapping("tLogin")//教师登录处理接口
    public void teacherLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String email =req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(context.getDisplayName());
        if(email.equals("123@qq.com")&&password.equals("123")){
            req.getSession().setAttribute("login","1");
            req.getRequestDispatcher( "/index/index.jsp").forward(req,resp);
            System.out.println("登陆成功");
        }else {
            req.setAttribute("error","账号密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            System.out.println("登陆失败");
        }
    }

    @RequestMapping("addStudentPage")//跳转到添加学生界面接口
    public void addStudentPage(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.getRequestDispatcher("/addStudent.jsp").forward(req,resp);
    }

    @RequestMapping("addStudent")//处理添加学生
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String name=req.getParameter("StudentName");
        String id=req.getParameter("StudentId");
        Student s=new Student();
        s.setName(name);
        s.setId(Long.valueOf(id));
        System.out.println(s.toString());
        //StudentJDBC Sjd=new StudentJDBC();
        if( studentJDBC.addStudent(s)){
            req.setAttribute("message","添加成功");
        }else {
            req.setAttribute("message","添加失败");
        }
        req.getRequestDispatcher("/addStudent.jsp").forward(req,resp);
    }

    @RequestMapping("addHomeworkPage")//跳转发布作业界面
    public void addHomeworkPage(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.getRequestDispatcher("/addHomework.jsp").forward(req,resp);
    }

    @RequestMapping("addHomework")//发布作业处理
    private void addHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//老师添加作业
        String title=req.getParameter("hwTitle");
        String content=req.getParameter("hwContent");
        String deadline=req.getParameter("deadline");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long createTime=System.currentTimeMillis();
        Date date=new Date(createTime);
        String create=simpleDateFormat.format(date);
        System.out.println(create);
        Homework hw=new Homework(0,title,content,create,deadline);
        System.out.println(hw.toString());
        if(homeworkJDBC.addHomework(hw)){
            req.setAttribute("message","添加成功");
        }else {
            req.setAttribute("message","添加失败");
        }
        req.getRequestDispatcher("/addHomework.jsp").forward(req,resp);
    }

    @RequestMapping("listHomework")//跳转展示作业列表界面
    public void listHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<Homework> list=homeworkJDBC.selectAll();
        req.setAttribute("hList",list);
        req.getRequestDispatcher("/listHomework.jsp").forward(req,resp);
    }

    @RequestMapping("listStudentHomework")//根据选中作业查看学生提交情况
    public void listStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("hwID");
        long sid=Long.parseLong(id);
        List<StudentHomeork> list= studentHomeworkJDBC.selectByHwid(sid);
        req.setAttribute("shList",list);
        req.getRequestDispatcher("/listStudenthw.jsp").forward(req,resp);
    }
}
