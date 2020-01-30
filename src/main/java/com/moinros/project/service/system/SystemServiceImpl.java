package com.moinros.project.service.system;

import com.moinros.project.model.dao.system.MenuMapper;
import com.moinros.project.model.dao.system.SystemMapper;
import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.vo.ResultStateValue;
import com.moinros.project.model.pojo.Tag;
import com.moinros.project.model.pojo.system.Access;
import com.moinros.project.model.pojo.system.Menu;
import com.moinros.project.model.pojo.system.MenuSon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 15:01
 * @Verison 1.0
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper mapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Access[] findAllAccess() {
        List li = mapper.selectAllAccess();
        if (li == null || li.size() <= 0) {
            return null;
        } else {
            Access[] as = new Access[li.size()];
            li.toArray(as);
            return as;
        }
    }

    @Override
    @Transactional
    public ResultValue addAccess(Access access) {
        Access ac = mapper.selectAccessByIP(access.getClientIp());
        ResultValue rv = new ResultStateValue();
        if (ac == null) {
            Integer i = mapper.insertAccess(access);
            if (i > 0) {
                rv.setState(true);
                rv.setValue("添加成功！");
            } else {
                rv.setState(false);
                rv.setValue("添加失败！");
            }
        } else {
            rv.setState(false);
            rv.setValue("添加失败！该IP地址已经存在");
        }
        return rv;
    }

    @Override
    public boolean modifyAccess(Access access) {
        return false;
    }

    @Override
    public boolean removeAccess(String ip) {
        Integer i = mapper.deleteAccessByIP(ip);
        return i == null || i <= 0 ? false : true;
    }

    @Override
    public List<Tag> findTagList() {
        List<Tag> li = mapper.selectAllTag();
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    public Tag findTagById(Integer id) {
        Tag t = new Tag();
        t.setTid(id);
        Tag tag = mapper.selectTag(t);
        return tag;
    }

    @Override
    public Tag findTagByMark(String mark) {
        Tag t = new Tag();
        t.setTagMark(mark);
        Tag tag = mapper.selectTag(t);
        return tag;
    }

    @Override
    @Transactional
    public ResultValue<Boolean, Object> addTag(Tag tag) {
        ResultValue rv = new ResultStateValue();
        Tag tf = mapper.selectTag(tag);
        if (tf == null) {
            Integer i = mapper.insertTag(tag);
            if (i != null && i.intValue() > 0) {
                Tag t = mapper.selectTag(tag);
                rv.setState(true);
                rv.setValue(t);
            } else {
                rv.setState(false);
                rv.setValue("添加失败！");
            }
        } else {
            rv.setState(false);
            rv.setValue("标签已经存在！");
        }
        return rv;
    }

    @Override
    @Transactional
    public ResultValue modifyTag(Tag tag) {
        ResultValue rv = new ResultStateValue();
        if (tag != null && tag.getTid() != null && tag.getTid().intValue() > 0) {
            Tag t1 = new Tag();
            t1.setTid(tag.getTid());
            Tag t2 = mapper.selectTag(t1);
            if (t2 != null) {
                Integer i = mapper.updateTag(tag);
                if (i != null && i.intValue() > 0) {
                    rv.setState(true);
                    rv.setValue("修改成功！");
                } else {
                    rv.setState(false);
                    rv.setValue("修改失败！");
                }
            } else {
                rv.setState(false);
                rv.setValue("标签不存在！");
            }
        }
        return rv;
    }

    @Override
    public List<MenuSon> findMenuSon(String mark) {
        List li = menuMapper.selectMenuList(mark);
        if (li != null && li.size() > 0) {
            List<MenuSon> list = sort(li);
            return list;
        }
        return null;
    }

    public static List<MenuSon> sort(List<Menu> li) {
        List<MenuSon> sons = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).getIsSon() != null && li.get(i).getIsSon().booleanValue()) {
                if (li.get(i).getLevel() != null && li.get(i).getLevel().intValue() <= 1) {
                    sort(new MenuSon(), li.get(i), sons, li);
                }
            }
        }
        return sons;
    }

    public static void sort(MenuSon son, Menu menu, List<MenuSon> sons, List<Menu> list) {
        son.setMenu(menu);
        sons.add(son);
        if (menu.getIsSon() != null && menu.getIsSon().booleanValue()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMfid() != null && list.get(i).getMfid().equals(menu.getMid())) {
                    if (son.getSon() == null) {
                        son.setSon(new ArrayList<MenuSon>());
                    }
                    sort(new MenuSon(), list.get(i), son.getSon(), list);
                }
            }
        }
    }

    @Override
    public List<Menu> findMenuList(String mark) {
        List li = menuMapper.selectMenuList(mark);
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    public Menu findMenuById(Integer mid) {
        return null;
    }

    @Override
    public boolean addMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean modifyMenu(Menu menu) {
        return false;
    }

    @Override
    public String findNotice() {
        return mapper.selectNotice();
    }

    @Override
    public boolean modifyNotice(String notice) {
        Integer num = mapper.updateNotice(notice);
        return num != null && num > 0 ? true : false;
    }

}
