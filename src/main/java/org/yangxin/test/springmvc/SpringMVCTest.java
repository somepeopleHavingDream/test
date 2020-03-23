package org.yangxin.test.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangxin
 * 2020/03/23 17:21
 */
@Slf4j
@Controller
public class SpringMVCTest {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hello";
    }
}
