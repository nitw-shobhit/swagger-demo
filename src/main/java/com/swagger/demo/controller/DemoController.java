package com.swagger.demo.controller;

import com.swagger.demo.resource.DemoResource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
 
@RestController
public class DemoController {
 
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
 
    @ApiOperation(value = "Test API", nickname = "test", notes = "This is a test API demonstrating the Swagger API doc interface")
    @RequestMapping(method = RequestMethod.GET, path="/sayHello", produces = {"application/json", "application/xml"})
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Shobhit")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = DemoResource.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    public DemoResource sayHello(@RequestParam(value="name", defaultValue="World") String name) {
        return new DemoResource(counter.incrementAndGet(),
                String.format(template, name));
    }
}