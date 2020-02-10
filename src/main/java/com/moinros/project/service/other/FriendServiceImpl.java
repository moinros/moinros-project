package com.moinros.project.service.other;

import com.moinros.project.model.dao.other.FriendMapper;
import com.moinros.project.model.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/5 23:05
 * @Verison 1.0
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<Friend> findFriend() {
        List li = friendMapper.selectAllFriend();
        return li != null && li.size() > 0 ? li : null;
    }

    @Override
    public boolean addFriend(Friend friend) {
        Integer i = friendMapper.insertFriend(friend);
        return i != null && i.intValue() > 0 ? true : false;
    }
}
