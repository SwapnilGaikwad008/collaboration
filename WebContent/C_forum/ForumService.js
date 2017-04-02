app.factory('ForumService', function($http, $q, $location, $rootScope)
{
	console.log("Entering Forum Service")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
		
	listForum: function()
	{
		console.log("Entering Forum List")
		return $http.get(BASE_URL + "viewApprovedForums")
		.then(function(response) 
		{
			console.log(response.data)
			console.log(response.status)
			return response;
		})
	},
	
	addForum: function(forum)
	{
		console.log("Entering add Forum - Service")
		return $http.post(BASE_URL + "addForum", forum)
		.then
		(
			function(response)
			{
				console.log(response)
				return response;
			}
		)
	},
	
	getForum: function(id)
	{
		console.log("Entering Get Forum "+id)
		console.log(BASE_URL + "getForum-" +id)
		return $http.get(BASE_URL + "getForum-" +id)
		.then(function(response)
		{
			console.log("Forum Recieved")
			if(response.data != null)
				$rootScope.gforum=response.data
			return response
		})
	},
	
	getForumReply: function(id)
	{
		console.log("Getting Forum Comments "+BASE_URL + "getForumReplies-"+id)
		return $http.get(BASE_URL + "getForumReplies-"+id)
		.then(
			function(response)
			{
				console.log(response)
				$rootScope.forumReplies=response.data;
				return response;
			});
	},
	
	addForumReply: function(id, forumReply)
	{
		console.log("Adding Forum Reply")
		return $http.post(BASE_URL + "addReply", forumReply)
		.then(
			function(response)
			{
				console.log(response)
				console.log("Comments Added")
				return response;
			}
		)
	}
}
})