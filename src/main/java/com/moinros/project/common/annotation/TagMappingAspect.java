package com.moinros.project.common.annotation;

import com.moinros.project.model.pojo.Blog;
import com.moinros.project.model.pojo.Tag;
import com.moinros.project.service.blog.BlogService;
import com.moinros.project.service.system.SystemService;
import com.moinros.project.tool.web.PageBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class TagMappingAspect {

    @Autowired
    private BlogService blogService;

    @Autowired
    private SystemService systemService;

    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.TagMapping)")
    public void typePointCut() {
    }

    @AfterReturning(value = "typePointCut()", returning = "result")
    public void afterInMethod(JoinPoint joinPoint, Object result) {
        if (result != null) {
            Class<?> clazz = result.getClass();

            if (List.class.isAssignableFrom(clazz)) {
                List<?> list = (List<?>) result;
                for (int i = 0; i < list.size(); i++) {
                    typeMapping((Blog) list.get(i));
                }
            } else if (clazz == Blog.class) {
                typeMapping((Blog) result);
            } else if (PageBean.class.isAssignableFrom(clazz)) {
                PageBean bean = (PageBean)result;
                for (int i = 0; i < bean.getList().size(); i++) {
                    typeMapping((Blog) bean.getList().get(i));
                }
            }
        }
    }

    public void typeMapping(Blog blog) {
        if (blog.getBlogTags() != null && blog.getTagMarks() != null) {
            String[] arr = new String[blog.getTagMarks().length];
            List<Tag> tli = systemService.findTagList();
            for (int i = 0; i < blog.getTagMarks().length; i++) {
                for (int j = 0; j < tli.size(); j++) {
                    if (blog.getTagMarks()[i].equals(tli.get(j).getTagMark())) {
                        arr[i] = tli.get(j).getTagName();
                    }
                }
            }
            blog.setTagNames(arr);
        }
    }

}
