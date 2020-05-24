package com.moinros.project.service.blog;

import com.moinros.project.common.annotation.TagMapping;
import com.moinros.project.common.annotation.base64.Base64Decoder;
import com.moinros.project.common.annotation.base64.Base64Encoder;
import com.moinros.project.common.annotation.base64.Base64EncoderParam;
import com.moinros.project.model.dao.blog.BlogMapper;
import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.vo.ResultStateValue;
import com.moinros.project.model.pojo.Blog;
import com.moinros.project.model.pojo.Tag;
import com.moinros.project.service.system.SystemService;
import com.moinros.project.tool.util.date.DateFormatUtil;
import com.moinros.project.tool.util.string.StringUtil;
import com.moinros.project.tool.web.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    private SystemService systemService;

    @Override
    @TagMapping
    @Base64Decoder
    public List<Blog> findBlogLi() {
        List<Blog> li = blogMapper.selectBlogLi();
        return li == null || li.size() <= 0 ? null : li;
    }

    @Override
    @TagMapping
    @Base64Decoder
    public List<Blog> findTitle() {
        List<Blog> li = blogMapper.selectBlogTitle();
        return li == null || li.size() <= 0 ? null : li;
    }

    @Override
    @TagMapping
    @Base64Decoder
    public Blog findBlogById(Integer blogId) {
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog = blogMapper.selectBlogByBlog(blog);
        if (blog != null) {
            blogMapper.updateVisitorCount(blog.getBlogId());
        }
        return blog;
    }

    @Override
    @Transactional
    @TagMapping
    @Base64Decoder
    public List<Blog> searchBlog(String value) {
        if (value == null || value == "") {
            return null;
        }
        value = StringUtil.stringToLowercase(value);
        value = value.replace("c/c++", "c-plus");
        value = value.replace("c++", "c-plus");
        value = value.replace("c#", "c-sharp");
        Tag tag = systemService.findTagByMark(value);
        List<Blog> li = null;
        if (tag != null) {
            li = blogMapper.selectBlogByType('%' + tag.getTagMark() + '%');
        } else {
            li = blogMapper.selectBlogByType('%' + value + '%');
        }
        List<Blog> item = blogMapper.selectBlogBySubject('%' + value + '%');
        if (li != null && item != null && item.size() > 0) {
            boolean f = true;
            for (int i = 0; i < item.size(); i++) {
                for (int j = 0; j < li.size(); j++) {
                    if (item.get(i).getBlogSubject().equals(li.get(j).getBlogSubject())) {
                        f = false;
                        break;
                    }
                }
                if (f) li.add(item.get(i));
            }
        } else {
            return item == null || item.size() <= 0 ? null : item;
        }
        return li == null || li.size() <= 0 ? null : li;
    }

    @Override
    @Base64Encoder(param = {
            @Base64EncoderParam(name = "content", type = String.class),
            @Base64EncoderParam(name = "intro", type = String.class),})
    public ResultValue<Boolean, Blog> saveBlog(String cover, String tags, String subject, String content, String intro, Integer blogId) {
        System.out.println();
        Blog blog = null;
        if (blogId != null) {
            Blog b = new Blog();
            b.setBlogId(blogId);
            blog = blogMapper.selectBlogByBlog(b);
        }
        ResultValue<Boolean, Blog> rv = new ResultStateValue();
        if (blog == null) {
            blog = new Blog();
            blog.setBlogCover(cover);
            blog.setBlogTags(tags);
            blog.setBlogSubject(subject);
            blog.setBlogContent(content);
            blog.setBlogIntro(intro);
            blog.setEditTime(DateFormatUtil.getDateTime());
            Integer num = blogMapper.insertBlog(blog);
            if (num != null && num.intValue() > 0) {
                rv.setState(true);
                Blog b = blogMapper.selectBlogByBlog(blog);
                rv.setValue(b);
            } else {
                rv.setState(false);
            }
        } else {
            blog.setBlogTags(tags);
            blog.setBlogCover(cover);
            blog.setBlogSubject(subject);
            blog.setBlogContent(content);
            blog.setBlogIntro(intro);
            blog.setUpdateTime(DateFormatUtil.getDateTime());
            Integer num = blogMapper.updateBlog(blog);
            if (num != null && num.intValue() > 0) {
                rv.setState(true);
                rv.setValue(blog);
            } else {
                rv.setState(false);
            }
        }
        return rv;
    }

    @Override
    @TagMapping
    @Base64Decoder
    public List<Blog> findBlogUpLimit(int number) {
        List<Blog> li = blogMapper.selectBlogByLimit(0, number);
        return li == null || li.size() <= 0 ? null : li;
    }

    @Override
    @TagMapping
    @Base64Decoder
    public List<Blog> findBlogByType(String type) {
        List<Blog> li = blogMapper.selectBlogByType(type);
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    @TagMapping
    @Base64Decoder
    public PageBean<Blog> pagingBlog(Integer pageNo, int dataSize) {
        Integer count = blogMapper.selectBlogTotalCount();
        if (count != null && count.intValue() > 0) {
            PageBean pageBean = new PageBean(pageNo, dataSize, count);
            List li = blogMapper.selectBlogByLimit(pageBean.getStartNo(), pageBean.getEndNo());
            if (li != null && li.size() > 0) {
                pageBean.setList(li);
                return pageBean;
            }
        }
        return null;
    }

}
