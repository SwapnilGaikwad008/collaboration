package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO {
	
	public void save(Friend friend);
	
	public void update(Friend friend);
	
	public void delete(String username, String friendId);
	
	public void setOnline(String username);
	
	public void setOffline(String username);
	
	public List<Friend> getMyFriend(String username);
	
	public List<Friend> getFriendRequestSentByMe(String username);
	
	public List<Friend> getNewFriendRequest(String friendId);

}
