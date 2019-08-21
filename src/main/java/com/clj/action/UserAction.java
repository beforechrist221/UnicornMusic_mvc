package com.clj.action;

import com.clj.model.User;
import com.clj.model.UserInfo;
import com.clj.util.DB;
import com.mysql.jdbc.Driver;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {

    private static final String URL = "jdbc:mysql:///?user=root&password=root";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("reg")){
            reg(req,resp);
        }
        if(action.equals("checkEmail")){
            checkEmail(req,resp);
        }
        if(action.equals("login")){
            login(req,resp);
        }
        if(action.equals("logoff")){
            logoff(req,resp);
        }
        if(action.equals("queryUserById")){
            queryUserById(req,resp);
        }
        if(action.equals("updateUserInfo")){
            updateUserInfo(req,resp);
        }

    }

    private void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = user.getId();
        String birthday = null;
        String gender = null;
        String avatar = "default.png";
//        String birthday = req.getParameter("birthday");
//        String gender = req.getParameter("gender");

        ////////////////////////////
        //File Upload
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletContext servletContext = req.getServletContext();//application
        String attribute = "javax.servlet.context.tempdir";
        File repository = (File) servletContext.getAttribute(attribute);
        diskFileItemFactory.setRepository(repository);

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for(FileItem fileItem : fileItems){
                //fileItem 是表单提交的普通数据
                if(fileItem.isFormField()){
                    switch (fileItem.getFieldName()){
                        case "birthday":
                            birthday = fileItem.getString("UTF-8");
                            break;
                        case "gender":
                            gender = fileItem.getString("UTF-8");
                            break;
                        default:
                            break;
                    }
                    System.out.println(fileItem.getFieldName()+":"+fileItem.getString("UTF-8"));
                }else{
//                    System.out.println(fileItem.getFieldName());
//                    System.out.println(fileItem.getName());
                    //存入本地

//                    fileItem.getContentType();

                    String originName = fileItem.getName();
                    String extension = originName.substring(originName.lastIndexOf("."));
                    String fileName = System.currentTimeMillis() + extension;
                    avatar = fileName;
                    System.out.println(avatar);
                    File file = new File(servletContext.getRealPath("/avatar") +"/"+avatar);
                    fileItem.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////////////////////////////

        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql = "update unicorn_db.userinfo set "+
                    "avatar = ?,"+
                    "birthday = ?,"+
                    "gender = ?"+
                    "where userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,avatar);
            preparedStatement.setString(2,birthday);
            preparedStatement.setString(3,gender);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/article?action=queryAllArticles");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }

        private void queryUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = user.getId();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql = "select * from unicorn_db.userinfo where userId = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            UserInfo userInfo = new UserInfo(
                    resultSet.getInt("id"),
                    resultSet.getString("birthday"),
                    resultSet.getString("gender"),
                    resultSet.getString("avatar"),
                    resultSet.getString("createTime"),
                    resultSet.getString("updateTime"),
                    resultSet.getInt("id")
            );

            req.getSession().setAttribute("userInfo",userInfo);
            resp.sendRedirect("userInfo.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }
    }

    private void logoff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password");
        User user = queryUserByEmail(email);
        if(user != null){
            StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
            if (strongPasswordEncryptor.checkPassword(password, user.getPassword())) {
                user.setPassword(null);//
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/article?action=queryAllArticles");
                return;
            }
        }
        req.setAttribute("message","Invalid Email or Password.");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

        //校验注册邮箱
    private void checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        resp.setContentType("application/json;charset = utf-8");
        String json = "{\"isEmailExisted\":true}";

        if(queryUserByEmail(email) == null){
            json = "{\"isEmailExisted\":false}";
        }
        resp.getWriter().write(json);
    }

    private User queryUserByEmail(String email){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = DB.getConnection();
            String sql = "select * from unicorn_db.user where email = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }
        return null;
    }
    private void reg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        if(queryUserByEmail(email) != null){
            req.setAttribute("message","Email is already existed.");
            req.getRequestDispatcher("reg.jsp").forward(req,resp);
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            preparedStatement = null;

            String username = req.getParameter("username").trim();
            String password = req.getParameter("password");

            //密码加密
            StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
            String encryPassword = strongPasswordEncryptor.encryptPassword(password);

            String sql = "insert into unicorn_db.user value(null,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,username);
                preparedStatement.setString(3,encryPassword);
                preparedStatement.executeUpdate();
                resp.sendRedirect("login.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  finally {
            DB.close(null,preparedStatement);
        }

    }
}
