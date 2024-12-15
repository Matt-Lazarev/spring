package com.lazarev.springrest.controller.jsonxml;

import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class JsonXmlController {
    private static final UserDto USER = new UserDto(
            1,
            "Mike",
            "12345",
            List.of(
                    new UserDto.UserDetail("birth_year: 2000"),
                    new UserDto.UserDetail("birth_month: January"),
                    new UserDto.UserDetail("birth_day: 1"),
                    new UserDto.UserDetail("job: Programmer")
            )
    );

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto json() {
        return USER;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public UserDto xml() {
        return USER;
    }
}
