package plus.fs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GMsure
 * @since 2021/11/26 0026 10:38
 */
@Controller
public class PageController {
    @RequestMapping("/fileClient")
    public String fileClient() {
        return "fileClient";
    }
}
