package com.example.demo.models;

public class SqlRequest {
    private String sqlString;

    public SqlRequest(){
        sqlString = "";
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }
}
