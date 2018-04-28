package school.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.domain.Comment;
import school.mapper.CommentMapper;
import school.service.CommentService;
import school.support.BaseController;
import school.support.pojo.CommentPojo;
import school.support.util.ParamUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Controller
@RequestMapping("admin/comment")
public class AdminCommentController extends BaseController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    /**
     * 评论列表
     *
     * @param comment
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Comment comment, @RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Example example = new Example(Comment.class);
        if ("user".equals(sessionUser().getType()))
            example.createCriteria().andEqualTo("userid", sessionUser().getId());
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CommentPojo> pageInfo = commentService.selectReByExample(example, pageSize, pageNum);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "admin/comment/list";
    }


    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        commentMapper.deleteByPrimaryKey(id);
        return refresh();
    }

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @RequestMapping("send")
    public String send(Comment comment, RedirectAttributes model) {
        comment.setAddtime(new Date());
        comment.setUserid(sessionUser().getId());
        commentMapper.insertSelective(comment);
        model.addAttribute("tip","添加成功");
        return refresh() ;
    }

}
