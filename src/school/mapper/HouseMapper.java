package school.mapper;

import school.support.pojo.HousePojo;
import school.support.pojo.OrdersPojo;
import tk.mybatis.mapper.common.Mapper;
import school.domain.House;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * house
 * @author Ghost
 *
 */
public interface HouseMapper extends Mapper<House>{

    List<HousePojo> selectReByExample(Example example);

    OrdersPojo selectOrders(Integer id);
}
