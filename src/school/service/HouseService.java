package school.service;

import com.github.pagehelper.PageInfo;
import school.support.pojo.HousePojo;
import tk.mybatis.mapper.entity.Example;

public interface HouseService {

	PageInfo<HousePojo> selectReByExample(Example example, Integer pageSize, Integer pageNum);
}