app.factory('UserService', function($http)
{
	console.log("Entering UserService")
	var BASE_URL = "http://localhost:8081/CollaborationBackend/"
		return{
	
	registerUser: function(user)
	{
		console.log("Entering Function Register User")
		return $http.post(BASE_URL + "add_User", user)
		.then(function(response)
			{
				console.log(response.status)
				return response.status
			},function(errResponse)
			{
				console.log(errResponse.status)
				return errResponse.status
			});
	},
	
	validate: function(user)
	{
		console.log("Entering Function Validate User")
		return $http.post(BASE_URL + "login", user)
		.then(function(response)
				{
					console.log(response.status)
					return response.status
				},function(errResponse)
				{
					console.error(errResponse.status)
					console.error("Error while validating")
					return errResponse.status
				});
	},
	
	listUser: function()
	{
		console.log("Entering Function List User")
		return $http.get(BASE_URL + "getUserList")
		.then(function(response)
			{
				console.log(response.data)
				console.log(response.status)
				return response.data
			})
	}
	}
})