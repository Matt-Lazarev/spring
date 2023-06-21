package com.lazarev.springswagger.controller;

import com.lazarev.springswagger.dto.GetDto;
import com.lazarev.springswagger.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/test")
@Tag(name = "Test Controller", description = "V2")
public class TestControllerV2 {

    @GetMapping
    @Operation(summary = "Get operation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "FAIL")
    })
    public GetDto getTest(){
        return new GetDto("Hello GET!");
    }

    @PostMapping
    @Operation(summary = "Post operation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "FAIL")
    })
    public PostDto postTest(@RequestBody PostDto postDto){
        return postDto;
    }

    @GetMapping("/filter")
    public Object[] filter(@ParameterObject Pageable pageable) {
        return new Object[]{
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort()};
    }

    @GetMapping("/sort")
    public Object[] sort(@ParameterObject Sort sort) {
        return new Object[]{sort};
    }
}
