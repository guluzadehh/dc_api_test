package com.dc.stud_api.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteRepositoryContext {
    private final String _connectionString;

    protected SqliteRepositoryContext(String connectionString) {
        _connectionString = connectionString;
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(_connectionString);
    }
}
