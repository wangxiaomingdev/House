package school.mapper;

import org.apache.ibatis.annotations.Param;
import school.support.pojo.Order;
import tk.mybatis.mapper.common.Mapper;
import school.domain.Orders;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * orders
 * 
 * @author Ghost
 *
 */
public interface OrdersMapper extends Mapper<Orders> {
	// 通过使用注解@Param，实现传递多个参数
	List<Order> selectList(@Param("flag") String flag);
}
