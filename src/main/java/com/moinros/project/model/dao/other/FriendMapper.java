package com.moinros.project.model.dao.other;

import com.moinros.project.model.pojo.Friend;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 *
 * @说明: 自动升成Dao层Friend的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据JavaBean生成的mMapper接口
 *
 * @Title: FriendMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface FriendMapper {

	@SelectProvider(method = "selectAllFriendSQL", type = FriendSqlProvider.class)
	public List<Friend> selectAllFriend();

	@SelectProvider(method = "selectFriendSQL", type = FriendSqlProvider.class)
	public List<Friend> selectFriend(Friend friend);

	@InsertProvider(method = "insertFriendSQL", type = FriendSqlProvider.class)
	public Integer insertFriend(Friend friend);

	@UpdateProvider(method = "updateFriendSQL", type = FriendSqlProvider.class)
	public Integer updateFriend(Friend friend);

}