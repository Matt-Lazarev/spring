package com.lazarev.springrest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public record UserDto(
        Integer id,

        String username,

        String password,

        @JacksonXmlElementWrapper(localName = "userDetails")
        @JacksonXmlProperty(localName = "userDetail")
        List<UserDetail> userDetails) {

    public record UserDetail(String detail) { }
}
