package school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import school.mapper.HouseMapper;
import school.service.HouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.support.pojo.HousePojo;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseMapper houseMapper;

	@Override
	public PageInfo<HousePojo> selectReByExample(Example example, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(houseMapper.selectReByExample(example), 5);
	}
}