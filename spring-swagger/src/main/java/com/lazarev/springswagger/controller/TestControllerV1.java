package com.lazarev.springswagger.controller;

import com.lazarev.springswagger.dto.GetDto;
import com.lazarev.springswagger.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@Tag(name = "Test Controller", description = "V1")
public class TestControllerV1 {

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
}
