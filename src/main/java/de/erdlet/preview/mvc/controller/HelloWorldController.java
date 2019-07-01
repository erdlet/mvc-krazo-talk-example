package de.erdlet.preview.mvc.controller;

import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
@Controller
public class HelloWorldController {

    @GET
    public String sayHello() {
        return "hello.jsp";
    }
}


