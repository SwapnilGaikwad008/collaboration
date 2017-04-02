
app.controller('FriendController', function($scope, $location, FriendService)
	{
		$scope.friends= [];
		$scope.pendingRequest= [];
		$scope.sentRequest= [];
		
		$scope.friends= 
			FriendService.getAllFriends()
			.then
			(
				function(response)
				{
					console.log("Entering Get My Friends")
					console.log(response.status)
					$scope.friends = response.data;
				}, function(errResponse)
				{
					console.log("Error getting friend list")
					console.log(errResponse.data)
				}
			)
		
		$scope.pendingRequest = 
			FriendService.getPendingRequest()
			.then
			(
					function(response)
					{
						console.log("Entering Get Pending Request")
						console.log(response.status)
						$scope.pendingRequest = response.data;
					}, function(errResponse)
					{
						console.log("Error getting friend list")
						console.log(errResponse.data)
					}
			)
			
		$scope.sentRequest = 
			FriendService.getSentRequest()
			.then
			(
					function(response)
					{
						console.log("Entering Get Sent Request")
						console.log(response.status)
						$scope.sentRequest = response.data;
					}, function(errResponse)
					{
						console.log("Error getting sent friend list")
						console.log(errResponse.data)
					}
			)		
			
		$scope.acceptRequest = function(userID)
		{
			FriendService.acceptRequest(userID)
			.then
			(
					function(response)
					{
						console.log("Entering Accepting Request "+userID)
						console.log(response.status)
						alert('Friend Accepted')
						$location.path("/myFriends")
					}, function(errResponse)
					{
						console.log("Error accepting friend")
						console.log(errResponse.data)
						$location.path("/viewPendingRequest")
					}	
			)
		}	
					
		$scope.rejectRequest = function(userID)
		{
			FriendService.rejectRequest(userID)
			.then
			(
					function(response)
					{
						console.log("Entering Reject Request "+userID)
						console.log(response.status)
						alert('Friend Rejected')
						$location.path("/myFriends")
					}, function(errResponse)
					{
						console.log("Error rejecting friend")
						console.log(errResponse.data)
						$location.path("/viewPendingRequest")
					}	
			)
		}
		
		$scope.cancelRequest = function(friendID)
		{
			FriendService.cancelRequest(friendID)
			.then
			(
					function(response)
					{
						console.log("Entering Cancel Request "+friendID)
						console.log(response.status)
						alert('Friend Request Cancelled')
						$location.path("/viewSentRequests")
					}, function(errResponse)
					{
						console.log("Error rejecting friend")
						console.log(errResponse.data)
						$location.path("/")
					}
			)
		}
		
		$scope.removeFriend = function(friendID)
		{
			FriendService.removeFriend(friendID)
			.then
			(
					function(response)
					{
						console.log("Entering Remove Friend "+friendID)
						console.log(response.status)
						alert('Friend Removed')
						$location.path("/myFriends")
					}, function(errResponse)
					{
						console.log("Error removing friend")
						console.log(errResponse.data)
						$location.path("/")
					}
			)
		}
	}
)