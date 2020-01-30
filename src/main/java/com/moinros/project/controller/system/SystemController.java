package com.moinros.project.controller.system;

import com.moinros.project.common.annotation.system.MenuMark;
import com.moinros.project.common.annotation.system.MenuTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/17 0:16
 * @Verison 1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @GetMapping("/index")
    @MenuTable(value = {
            @MenuMark(name = "side_bar_meun", mark = "system")
    })
    public String index() {
        return "plugin/system/system-index";
    }

}
