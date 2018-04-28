package school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.mapper.CommentMapper;
import school.service.CommentService;
import school.support.pojo.CommentPojo;
import tk.mybatis.mapper.entity.Example;

//使用注解@Service，标注业务层组件,表示定义一个bean，自动根据bean的类名实例化一个首写字母为小写的bean
//注册到spring容器中
@Service
// 事务注解
@Transactional
public class CommentServiceImpl implements CommentService {
	// 自动装配，自动去spring容器中寻找CommentMapper
	// 缺点：如果匹配多个类型一致的对象，将无法选择具体注入那一个对象
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public PageInfo<CommentPojo> selectReByExample(Example example, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(commentMapper.selectReByExample(example), 5);
	}
}