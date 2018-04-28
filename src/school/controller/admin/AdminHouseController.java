package school.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.domain.House;
import school.domain.Orders;
import school.mapper.HouseMapper;
import school.mapper.OrdersMapper;
import school.service.HouseService;
import school.support.BaseController;
import school.support.pojo.HousePojo;
import school.support.pojo.OrdersPojo;
import school.support.util.ParamUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Controller
@RequestMapping("admin/house")
public class AdminHouseController extends BaseController {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private HouseService houseService;
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 房屋列表
     *
     * @param house
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(House house, @RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Example example = new Example(House.class);
        Example.Criteria criteria = example.createCriteria();
        if (house.getTitle() != null) {
            criteria.andLike("title", "%" + house.getTitle() + "%");
        }
        if (house.getType() != null && !house.getType().equals("")) {
            criteria.andCondition("h.type='" + house.getType() + "'");
        }
        if ("user".equals(sessionUser().getType()))
            criteria.andEqualTo("userid", sessionUser().getId());
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<HousePojo> pageInfo = houseService.selectReByExample(example, pageSize, pageNum);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "admin/house/list";
    }


    /**
     * 房屋详情
     *
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        House house = houseMapper.selectByPrimaryKey(id);
        model.addAttribute(house);
        return "admin/house/detail";
    }

    /**
     * 修改房屋信息
     *
     * @param house
     * @return
     */
    @RequestMapping("update")
    public String update(House house) {
        houseMapper.updateByPrimaryKeySelective(house);
        return refresh();
    }

    /**
     * 删除房屋
     *
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        houseMapper.deleteByPrimaryKey(id);
        return refresh();
    }

    /**
     * 到添加页面
     *
     * @return
     */
    @GetMapping("add")
    public String add() {
        return "admin/house/add";
    }


    /**
     * 添加房屋
     *
     * @param house
     * @return
     */
    @PostMapping("add")
    public String add(House house, RedirectAttributes model) {
        house.setAddtime(new Date());
        house.setStatus("1");
        house.setUserid(sessionUser().getId());
        houseMapper.insert(house);
        model.addAttribute("type",house.getType());
        model.addAttribute("tip","添加成功");
        return "redirect:list";
    }

    /**
     * 房屋交易详情
     *
     * @param id
     * @return
     */
    @RequestMapping("info/{id}")
    public String info(@PathVariable Integer id, Model model) {
        OrdersPojo ordersPojo = houseMapper.selectOrders(id);
        model.addAttribute("ordersPojo", ordersPojo);
        return "admin/house/info";
    }

    /**
     * 审核
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("verify")
    public String verify(Integer id, int status) {
        House house = houseMapper.selectByPrimaryKey(id);
        house.setStatus(status + "");
        houseMapper.updateByPrimaryKeySelective(house);
        return refresh();
    }

    /**
     * 交易
     *
     * @param orders
     * @return
     */
    @RequestMapping("buy")
    public String buy(Orders orders) {
        orders.setAddtime(new Date());
        orders.setUserid(sessionUser().getId());
        ordersMapper.insert(orders);
        House house = houseMapper.selectByPrimaryKey(orders.getHouseid());
        house.setStatus("4");
        houseMapper.updateByPrimaryKeySelective(house);
        return "redirect:/index";
    }
}
