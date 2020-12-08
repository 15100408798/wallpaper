package com.yushang.wallpaper.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * springboot类似于springmvc的前端控制器，进行页面跳转使用。
 * 因为springboot默认访问不到templates下的页面，所以需要进行设置，跳转
 */
@Controller
public class DispatcherController {

    @GetMapping(value = "skip/{page}/{pageDetails}")
    public String skipPage(Object row, @PathVariable String page, @PathVariable String pageDetails, HttpServletRequest request) {
        if (Objects.nonNull(row)) {
            request.setAttribute("row", row);
        }
        return page + "/" + pageDetails;
    }

    @GetMapping(value = {"/", "/common/login"})
    public String skipPage() {
        return "common/login";
    }

}
