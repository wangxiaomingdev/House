package school.mapper;

import school.domain.Comment;
import school.support.pojo.CommentPojo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
/**
 * comment
 * @author Ghost
 *
 */
public interface CommentMapper extends Mapper<Comment> {
    List<CommentPojo> selectReByExample(Example example);

}
