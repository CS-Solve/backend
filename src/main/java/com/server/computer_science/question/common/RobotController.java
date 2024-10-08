package com.server.computer_science.question.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RobotController {
    private final String ROBOT_TXT = "# *\n" +
            "User-agent: *\n" +
            "Allow: /\n" +
            "\n" +
            "# Host\n" +
            "Host: http://comssa.site\n";

    @GetMapping("/robots.txt")
    @ResponseBody
    public String robots(){
        return ROBOT_TXT;
    }
}
