package com.example.demo.controllers.sqlPage;

import com.example.demo.models.Sale;
import com.example.demo.models.SqlRequest;
import com.example.demo.models.TableContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SqlPageController {
    SqlRequest mainSqlRequest;
    TableContent mainTableContent;
    private static Connection connection;

    @GetMapping({"/sqlPage/index"})
    public String sqlPageGet(Model model) {
        if (mainSqlRequest == null) {
            mainSqlRequest = new SqlRequest();
            connection = getNewConnection();
        }
        if(mainTableContent == null){
            mainTableContent = new TableContent();
        }
        model.addAttribute("tableContent", mainTableContent);
        model.addAttribute("sqlRequest", mainSqlRequest);
        return "sqlPage/index";
    }

    @PostMapping({"/sqlPage/index"})
    public String sqlPagePost(Model model, @ModelAttribute("sqlRequest") SqlRequest sqlRequest, @ModelAttribute("TableContent") TableContent tableContent) {
        mainSqlRequest = sqlRequest;
        executeUpdate(mainSqlRequest.getSqlString(), tableContent);
        mainTableContent=tableContent;
        System.out.println(mainTableContent.getTableHeader());
        model.addAttribute("tableContent", mainTableContent);
        model.addAttribute("sqlRequest", mainSqlRequest);
        return "redirect:/sqlPage/index";
    }

    private Connection getNewConnection() {
        String url = "jdbc:postgresql://localhost:5432/testlib";
        String user = "postgres";
        String passwd = "123";
        try {
            return DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeUpdate(String query, TableContent tableContent) {

        try {
            Statement statement = connection.createStatement();
            if (statement.execute(query)) {
                ResultSet resultSet = statement.getResultSet();

                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    tableContent.getTableHeader().add(resultSet.getMetaData().getColumnName(i));
                }

                while (resultSet.next()) {
                    List<String> tableColumnContent = new ArrayList<>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        tableColumnContent.add(resultSet.getString(i));
                        System.out.println(resultSet.getString(i));
                    }
                    System.out.println();
                    tableContent.getTableRowContent().add(tableColumnContent);
                }
                mainSqlRequest.setError("");
            } else {
                System.out.println("Успешно выполнено");
                mainSqlRequest.setError("Успешно выполнено");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            mainSqlRequest.setError(e.getMessage());
        }
//            System.out.println(result);
//            return result;

    }
}