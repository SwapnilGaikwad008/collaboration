app.factory('AdminService', function($http, $q, $rootScope)
{
	console.log("Admin Service")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
	return {

		listAllForums: function()
		{
			console.log("Entering Function List All Forums")
			return $http.get(BASE_URL + "viewAllForums")
			.then(function(response)
				{
//					console.log(response.data)
					console.log("Recieved All Forums "+response.status)
					return response;
				})
		},
		
		invalidateJob: function(id)
		{
			console.log("Entering Job Invalidate")
			return $http.get(BASE_URL + "invalidate-" + id)
			.then(function(response)
				{
					console.log("Job Invalidated")
					return response;
				}
			)
		},
		
		getAppliedJobs:function()
		{
			console.log("Get Applied Jobs")
			return $http.get(BASE_URL + "viewApplications")
			.then(function(response)
				{
					console.log("List Applied Jobs Recieved")
					return response;
				}
			)
		},
		
		approveBlog: function(blog)
		{
			console.log("Entering Approve Blog")
			return $http.post(BASE_URL + "admin_approveBlog", blog)
			.then(
				function(response)
				{
					console.log("Approved Blog")
					return response;
				}
			)
		},
		
		rejectBlog: function(blog)
		{
			console.log("Entering Reject Blog")
			return $http.post(BASE_URL + "admin_rejectBlog", blog)
			.then(
				function(response)
				{
					console.log("Rejected Blog")
					return response;
				}
			)
		},
		
		acceptForum: function(forum)
		{
			console.log("Accepting Forum")
			return $http.post(BASE_URL + "updateForum", forum)
			.then(
				function(response)
				{
					console.log("Forum Accepted")
					return response;
				}
			)
		},
		
		rejectForum: function(forum)
		{
			console.log("Rejecting Forum")
			return $http.post(BASE_URL + "updateForum", forum)
			.then(
				function(response)
				{
					console.log("Forum Rejected")
					return response;
				}
			)
		},
		
		removeEvent: function(id)
		{
			console.log("Removing Event "+id)
			return $http.get(BASE_URL + "deleteEvent-"+id)
			.then(
				function(response)
				{
					console.log("Event Removed")
					return response;
				}
			)
		},
		
		updateUser: function(user)
		{
			console.log("Update User Service")
			return $http.post(BASE_URL + "updateUser", user)
		}
	}
}
)