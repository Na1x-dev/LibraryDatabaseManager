package com.example.demo.controllers.sqlPage;

import com.example.demo.models.Sale;
import com.example.demo.models.SqlRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SqlPageController {
    SqlRequest mainSqlRequest;

    @GetMapping({"/sqlPage/index"})
    public String sqlPageGet(Model model) {
        if(mainSqlRequest==null)
        mainSqlRequest = new SqlRequest();
        System.out.println(mainSqlRequest.getSqlString() + "<");
        model.addAttribute("sqlRequest", mainSqlRequest);
        return "sqlPage/index";
    }

    @PostMapping({"/sqlPage/index"})
    public String sqlPagePost(Model model, @ModelAttribute("sqlRequest") SqlRequest sqlRequest) {
        mainSqlRequest = sqlRequest;
        System.out.println(mainSqlRequest.getSqlString() + "<");
        model.addAttribute("sqlRequest", mainSqlRequest);
        return "redirect:/sqlPage/index";
    }
}
