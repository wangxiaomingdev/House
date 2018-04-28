package school.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.domain.House;
import school.domain.Orders;
import school.mapper.HouseMapper;
import school.mapper.OrdersMapper;
import school.support.BaseController;
import school.support.pojo.Order;
import school.support.util.ParamUtils;

@Controller
@RequestMapping("admin/order")
public class AdminOrderController extends BaseController {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private HouseMapper houseMapper;

    @RequestMapping("list")
    public String list(String flag, @RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Order> pageInfo = new PageInfo<>(ordersMapper.selectList(flag), 5);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "admin/order/list";
    }

    @RequestMapping("ok/{id}")
    public String ok(@PathVariable Integer id) {
        Orders order = ordersMapper.selectByPrimaryKey(id);
        order.setStatus("已确认");
        ordersMapper.updateByPrimaryKeySelective(order);

        //House house = houseMapper.selectByPrimaryKey(order.getHouseid());


        return refresh();
    }


    @RequestMapping("cancel/{id}")
    public String cancel(@PathVariable Integer id) {
    	Orders order = ordersMapper.selectByPrimaryKey(id);
        order.setStatus("已取消");
        

        House house = houseMapper.selectByPrimaryKey(order.getHouseid());
        house.setStatus("2");
        houseMapper.updateByPrimaryKeySelective(house);
        
        ordersMapper.deleteByPrimaryKey(id);

        return refresh();
    }
}
