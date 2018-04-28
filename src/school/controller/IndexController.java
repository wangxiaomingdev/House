package school.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.domain.Comment;
import school.domain.House;
import school.domain.Notice;
import school.domain.User;
import school.mapper.HouseMapper;
import school.mapper.NoticeMapper;
import school.mapper.UserMapper;
import school.service.CommentService;
import school.service.UserService;
import school.support.BaseController;
import school.support.pojo.CommentPojo;
import school.support.util.ParamUtils;
import school.support.util.VerifyCodeUtil;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private CommentService commentService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping({"index", "/"})
    public String index(Model model) {
        Example example = new Example(House.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 2)
                .andEqualTo("type", "出租");
        PageHelper.startPage(1, 3);
        List<House> czList = houseMapper.selectByExample(example);

        example = new Example(House.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("status", 2)
                .andEqualTo("type", "出售");
        PageHelper.startPage(1, 3);
        List<House> csList = houseMapper.selectByExample(example);

        example = new Example(House.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("status", 2)
                .andEqualTo("type", "求租");
        PageHelper.startPage(1, 3);
        List<House> qzList = houseMapper.selectByExample(example);

        example = new Example(House.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("status", 2)
                .andEqualTo("type", "求售");
        PageHelper.startPage(1, 3);
        List<House> qsList = houseMapper.selectByExample(example);


        PageHelper.startPage(1,4);
        List<Notice> noticeList = noticeMapper.selectAll();
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("czList", czList);
        model.addAttribute("csList", csList);
        model.addAttribute("qzList", qzList);
        model.addAttribute("qsList", qsList);
        return "index";
    }

    /**
     * 搜索列表页
     *
     * @param model
     * @param pageNum
     * @param title
     * @param type
     * @return
     */
    @RequestMapping({"list"})
    public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum, String title, String type) {
        Example example = new Example(House.class);
        Example.Criteria criteria = example.createCriteria();
        if (title != null) {
            criteria.andLike("title", "%" + title + "%");
        }
        if (type != null && !type.equals("")) {
            criteria.andEqualTo("type", type);
        }
        criteria.andEqualTo("status", 2);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<House> pageInfo = new PageInfo<>(houseMapper.selectByExample(example), 5);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "list";
    }

    /**
     * 到登录页面
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "admin/login";
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password,String code) {
        if(!code.equalsIgnoreCase((String) session.getAttribute("verifyCode"))){
            request.setAttribute("msg", "验证码错误");
            return "admin/login";
        }
        User user = userService.login(username, password);
        if (null == user) {
            request.setAttribute("msg", "用户名或者密码错误");
            return "admin/login";
        }
        session.setAttribute("sessionUser", user);
        if ("admin".equals(user.getType())) return "redirect:/admin/index";
        return "redirect:index";
    }


    /**
     * 到注册页面
     *
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "admin/register";
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public String register(User user, String password2,String code) {
        if(!code.equalsIgnoreCase((String) session.getAttribute("verifyCode"))){
            request.setAttribute("msg", "验证码错误");
            return "admin/register";
        }
        if (!user.getPassword().equals(password2)) {
            request.setAttribute("msg", "两次密码不一样");
            return "admin/register";
        }
        //判断用户是否存在
        User temp = new User();
        temp.setName(user.getName());
        int size = userMapper.select(temp).size();
        if (size > 0) {
            request.setAttribute("msg", "用户名已经存在");
            return "admin/register";
        }

        user.setAddtime(new Date());
        user.setType("user");
        userMapper.insertSelective(user);
        return "redirect:index";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        session.removeAttribute("sessionUser");
        return "redirect:index";
    }


    /**
     * 公告详情
     *
     * @param id
     * @return
     */
    @RequestMapping("notice/{id}")
    public String notice(@PathVariable Integer id, Model model) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        model.addAttribute(notice);
        return "notice";
    }

    /**
     * 房屋详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        House house = houseMapper.selectByPrimaryKey(id);
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("houseid", id);
        PageInfo<CommentPojo> pageInfo = commentService.selectReByExample(example, Integer.MAX_VALUE, 1);
        model.addAttribute(house);
        model.addAttribute("commentList", pageInfo.getList());
        return "detail";
    }


    @RequestMapping("notice/list")
    public String noticeList(Model model){
        List<Notice> noticeList = noticeMapper.selectAll();
        model.addAttribute("noticeList", noticeList);
        return "notice-list";
    }

    /**
     * 验证码
     * @Date 2016年8月23日下午2:31:11
     * @param response
     * @throws IOException
     */
    @RequestMapping("verify")
    public void verify(HttpServletResponse response) throws IOException {
        String code = VerifyCodeUtil.outputVerifyImage(144, 42, response.getOutputStream(), 5);
        request.getSession().setAttribute("verifyCode", code);
    }
}
