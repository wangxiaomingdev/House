package school.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.domain.User;
import school.mapper.UserMapper;
import school.support.BaseController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "admin/index";
    }


    /**
     * 修改密码
     *
     * @param pwd
     * @param pwd2
     * @return
     */
    @RequestMapping("modify")
    public String modify(String pwd, String pwd2, HttpSession session, RedirectAttributes model) {
        User user = (User) session.getAttribute("sessionUser");
        if (!user.getPassword().equals(pwd)) {
            model.addAttribute("errorMsg", "原密码错误");
            return refresh();
        }
        user.setPassword(pwd2);
        userMapper.updateByPrimaryKeySelective(user);
        session.setAttribute("sessionUser", user);
        return "redirect:/index.do";
    }


    @RequestMapping("info")
    public String info(Model model) {
        User user = userMapper.selectByPrimaryKey(sessionUser().getId());
        model.addAttribute(user);
        return "admin/info";
    }

    @RequestMapping("update")
    public String update(User user) {
        user.setId(sessionUser().getId());
        userMapper.updateByPrimaryKeySelective(user);
        return refresh();
    }

}
