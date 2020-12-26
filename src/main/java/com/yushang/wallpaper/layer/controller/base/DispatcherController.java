package com.yushang.wallpaper.layer.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * springboot类似于springmvc的前端控制器，进行页面跳转使用。
 * 因为springboot默认访问不到templates下的页面，所以需要进行设置，跳转
 */
@Controller
public class DispatcherController {


    @GetMapping(value = "skip/{page}/{firstPage}")
    public String skipPage(@PathVariable(value = "page") String page, @PathVariable(value = "firstPage") String firstPage) {
        StringBuilder builder = new StringBuilder();
        builder.append(page).append("/").append(firstPage);
        return builder.toString();
    }

    @GetMapping(value = "skip/{page}/{firstPage}/{secondPage}")
    public String skipPage(@PathVariable String page, @PathVariable String firstPage, @PathVariable String secondPage) {
        StringBuilder builder = new StringBuilder();
        builder.append(page).append("/").append(firstPage);
        builder.append("/").append(secondPage);
        return builder.toString();
    }

    @GetMapping(value = "skip/{page}/{firstPage}/{secondPage}/{id}")
    public String skipPage(@PathVariable String page, @PathVariable String firstPage, @PathVariable String secondPage,
                           @PathVariable String id, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(page).append("/").append(firstPage);
        builder.append("/").append(secondPage);
        request.setAttribute("id", id);
        return builder.toString();
    }

    @GetMapping(value = {"/", "/common/login"})
    public String skipPage() {
        return "common/login";
    }

}
