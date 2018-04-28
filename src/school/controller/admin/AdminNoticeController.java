package school.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.domain.Notice;
import school.mapper.NoticeMapper;
import school.support.BaseController;
import school.support.util.ParamUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Controller
@RequestMapping("admin/notice")
public class AdminNoticeController extends BaseController {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 公告列表
     *
     * @param notice
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Notice notice, @RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Example example = new Example(Notice.class);
        if (notice.getTitle() != null) {
            example.createCriteria()
                    .andLike("title", "%" + notice.getTitle() + "%");
        }

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeMapper.selectByExample(example), 5);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "admin/notice/list";
    }

    /**
     * 公告详情
     *
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        model.addAttribute(notice);
        return "admin/notice/detail";
    }

    /**
     * 修改公告信息
     *
     * @param notice
     * @return
     */
    @RequestMapping("update")
    public String update(Notice notice) {
        noticeMapper.updateByPrimaryKeySelective(notice);
        return refresh();
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        noticeMapper.deleteByPrimaryKey(id);
        return refresh();
    }

    /**
     * 到添加页面
     *
     * @return
     */
    @GetMapping("add")
    public String add() {
        return "admin/notice/add";
    }


    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    @PostMapping("add")
    public String add(Notice notice) {
        notice.setAddtime(new Date());
        noticeMapper.insert(notice);
        return "redirect:list";
    }

}
