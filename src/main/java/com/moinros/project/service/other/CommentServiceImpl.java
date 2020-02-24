package com.moinros.project.service.other;

import com.moinros.project.common.annotation.base64.Base64Decoder;
import com.moinros.project.common.annotation.base64.Base64Encoder;
import com.moinros.project.common.annotation.base64.Base64EncoderParam;
import com.moinros.project.model.dao.other.CommentMapper;
import com.moinros.project.model.pojo.Comment;
import com.moinros.project.tool.util.date.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 20:27
 * @Verison 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper mapper;

    @Override
    @Base64Decoder
    public List<Comment> findListByMark(String mark) {
        List li = mapper.selectCommentByMark(mark);
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    @Base64Decoder
    public List<Comment> findCommentByReply(Integer replyId) {
        List li = mapper.selectCommentByReply(replyId);
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    @Base64Encoder(param = {@Base64EncoderParam(name = "comment", type = Comment.class)})
    public boolean saveComment(Comment comment) {
        comment.setReplyTime(DateFormatUtil.getDateTime());
        Integer num = mapper.insertComment(comment);
        return num != null && num.intValue() > 0 ? true : false;
    }

    @Override
    public boolean modifyComment(Comment comment) {
        return false;
    }
}
