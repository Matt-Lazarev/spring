package com.lazarev.springtransactional.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class PersonRepository {
    private final Connection connection;

    public PersonRepository() {
        connection = JdbcConnection.getConnection();
    }

    public boolean savePerson(Person person) throws SQLException {
        String SQL = "INSERT INTO people (name, age) VALUES (?, ?)";


        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            boolean success = statement.executeUpdate() > 0;
            connection.commit();
            return success;

        } catch (SQLException sqlException) {
            connection.rollback();
            return false;
        }
    }
}
