package school.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import school.support.BaseController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller//web层，注入到spring容器中
public class FileController extends BaseController {


    @RequestMapping("uploadFile")
    public void upload(HttpServletResponse response, @RequestParam MultipartFile[] imgFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", 1);
        for (MultipartFile item : imgFile) {
            if (item.isEmpty()) {
                map.put("message", "请选择文件");
                try {
                    response.getOutputStream().write(JSONUtils.toJSONString(map).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String path = saveFile(item);
                    map.put("error", 0);
                    map.put("url", path);
                    response.getOutputStream().write(JSONUtils.toJSONString(map).getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("message", e.getMessage());
                    try {
                        response.getOutputStream().write(JSONUtils.toJSONString(map).getBytes());
                    } catch (IOException e1) {
                    }
                }
            }
        }
    }


}
