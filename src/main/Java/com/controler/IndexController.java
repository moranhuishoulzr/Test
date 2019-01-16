package com.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: comit
 * @description:
 * @author: liangzr
 * @create: 2019-01-02 16:30
 */
@Controller
@RequestMapping("/home")
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "indexConfilct";
    }
}
