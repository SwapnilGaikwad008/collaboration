package com.niit.collaboration.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@RestController
public class FriendController {

	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	@PostMapping("/addFriend/")
	public ResponseEntity<Friend> addFriend(@RequestBody Friend friend)
	{
		friendDAO.save(friend);
		friend.setErrorCode("200");
		friend.setErrorMsg("Successful");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/myFriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session){
	
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		List<Friend> myFriends = friendDAO.getMyFriend(loggedInUserID);
		if(loggedInUserID == null){
			friend.setErrorCode("404");
			friend.setErrorMsg("Please Login");
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);	
	}
		if(myFriends.isEmpty()){
			friend.setErrorCode("404");
			friend.setErrorMsg("You don't have Friend");
			myFriends.add(friend);
		}
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}		
	
	
	@RequestMapping(value = "/addFriend/{friendId}", method = RequestMethod.POST)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId, HttpSession session){
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUsername(loggedInUser.getUsername());
		friend.setFriendId(friendId);
		friend.setStatus("N");
		friend.setIsOnline("Y");
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
}
