package com.lazarev.springsolid.spring;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SR { }

@Slf4j
@RestController
class Controller {

    @GetMapping("/calculate/mul")
    public MathResult multiply(@RequestParam("p1") int p1,
                               @RequestParam("p2") int p2){

        int result = p1 * p2;
        log(p1, p2, "*", result);
        saveInDb(p1, p2,  result, "*");

        return new MathResult(p1, p2, result, "*");
    }

    private void log(int p1, int p2, String operation, int result){
        log.info("Math result: {} {} {} = {}", p1, p2, operation, result);
    }

    @SneakyThrows
    private void saveInDb(int p1, int p2, int result, String operation){
        Connection connection = DriverManager.getConnection("");

        String sql = "INSERT INTO math_results values (null, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, p1);
        statement.setInt(2, p2);
        statement.setInt(3, result);
        statement.setString(4, operation);

        statement.executeUpdate();
    }
}

@AllArgsConstructor
class MathResult {
    private Integer p1;
    private Integer p2;
    private Integer result;
    private String operation;
}

