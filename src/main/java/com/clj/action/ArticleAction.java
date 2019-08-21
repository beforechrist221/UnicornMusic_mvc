package com.clj.action;

import com.clj.model.Article;
import com.clj.util.DB;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/article")
public class ArticleAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "remove":
                remove(req, resp);
                break;
            case "edit":
                edit(req,resp);
            case "queryArticleByKeyword":
                queryArticleByKeyword(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "queryAllArticles":
                queryAllArticles(req, resp);
                break;
            default:
                break;
        }
    }

    //add
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content");

        PreparedStatement preparedStatement=null;
        try {
            Connection connection = DB.getConnection();
            String sql = "insert into unicorn_db.article value(null,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);

            preparedStatement.executeUpdate();
            resp.sendRedirect("/article?action=queryAllArticles");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }

    //remove
    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        PreparedStatement preparedStatement=null;
        try {
            Connection connection = DB.getConnection();
            String sql = "delete from unicorn_db.article where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            resp.sendRedirect("/article?action=queryAllArticles");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }

    //edit
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql = "select * from unicorn_db.article where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Article article = new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content")
            );

            req.getSession().setAttribute("article",article);
            resp.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }
    }

        //queryArticleByKeyword
    private void queryArticleByKeyword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*List<Article> articles1 = new ArrayList<>();
        String keyword = req.getParameter("keyword");
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql="select * from unicorn_db.article where title like ?";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+keyword+"%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Article article = new Article(id, title, content);
                articles1.add(article);
            }
            req.getSession().setAttribute("articles1",articles1);
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement;
        }*/
    }

    //update
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql = "update unicorn_db.article set "+
                    "title = ?,"+
                    "content = ?"+
                    "where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/article?action=queryAllArticles");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }

    //queryAllArticles
    private void queryAllArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articles = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DB.getConnection();
            String sql="select * from unicorn_db.article;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Article article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                );
                articles.add(article);
            }
            req.getSession().setAttribute("articles",articles);
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }
    }
}
