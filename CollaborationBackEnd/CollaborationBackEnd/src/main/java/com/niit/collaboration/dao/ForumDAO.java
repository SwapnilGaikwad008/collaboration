package com.niit.collaboration.dao;


import java.util.List;

import com.niit.collaboration.model.Forum;

public interface ForumDAO
{

	public boolean addForum(Forum forum);
	
	public boolean deleteForum(Forum forum);
	
	public Forum getForum(int id);
	
	public List<Forum> getForumList();
}
