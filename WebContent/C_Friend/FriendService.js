app.factory('FriendService', function($http)
	{
		console.log("Entering UserService")
		var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		var friendService = this;
		
		friendService.getAllFriends = function()
		{
			console.log("Getting My Friends");
			return $http.get(BASE_URL+"myFriends");			
		}
		
		friendService.getPendingRequest = function()
		{
			console.log("Getting Pending Requests");
			return $http.get(BASE_URL + "viewPendingRequest");
		}
		
		friendService.getSentRequest = function()
		{
			console.log("Getting Sent Requests");
			return $http.get(BASE_URL + "viewSentRequest");
		}
		
		friendService.acceptRequest = function(userID)
		{
			console.log("Accepting Request");
			return $http.get(BASE_URL + "acceptRequest-"+userID)
			.then
			(
				function(response)
				{
					return response.data;
				},
				function(errResponse)
				{
					console.log("Error Adding Friend");
					return $q.reject(errResponse);
				}
			);
		}
		
		friendService.rejectRequest = function(userID)
		{
			console.log("Rejecting Request");
			return $http.get(BASE_URL + "rejectFriend-"+userID)
			.then
			(
				function(response)
				{
					return response.data;
				},
				function(errResponse)
				{
					console.log("Error Rejecting Friend");
					return $q.reject(errResponse);
				}
			);
		}
		
		friendService.cancelRequest = function(friendID)
		{
			console.log("Cancel Request "+friendID)
			return $http.get(BASE_URL + "cancelRequest-"+friendID)
			.then
			(
				function(response)
				{
					return response.data;
				},
				function(errResponse)
				{
					console.log("Error Canceling Friend Request");
					return $q.reject(errResponse);
				}
			);
		}
		
		friendService.removeFriend = function(friendID)
		{
			console.log("Remove Friend "+friendID)
			return $http.get(BASE_URL + "removeFriend-"+friendID)
			.then
			(
				function(response)
				{
					return response.data;
				},
				function(errResponse)
				{
					console.log("Error Removing Friend");
					return $q.reject(errResponse);
				}
			);
		}
		
		return friendService;
	}
)