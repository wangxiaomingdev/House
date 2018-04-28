package school.service;

import com.github.pagehelper.PageInfo;
import school.support.pojo.CommentPojo;
import tk.mybatis.mapper.entity.Example;

public interface CommentService {
	PageInfo<CommentPojo> selectReByExample(Example example, Integer pageSize, Integer pageNum);
}