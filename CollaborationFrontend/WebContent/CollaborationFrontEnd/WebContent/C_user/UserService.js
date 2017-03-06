app.factory('UserService', function($http, $q, $rootScope)
{
	console.log("Entering UserService")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
	//var userService = this;
	
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
	
	authenticate: function(user)
	{
		console.log("Entering Function Validate User")
		return $http.post(BASE_URL + "login", user)
		.then(
				function(response)
				{
					if(response.data.errorMessage == "Success")
						{
							$rootScope.currentUser = 
								{
									username: response.data.username,
									first_name: response.data.first_name,
									last_name: response.data.last_name,
									dob: response.data.user.dob,
									gender: response.data.gender,
									mail_id: response.data.mail_id,
									status: response.data.status,
									role: response.data.role,
									password: response.data.password,
									birthdate: response.data.birthdate,
									isOnline: response.data.isOnline
								};
						}
					return response.data;
				},
				function(errResponse)
				{
					$rootScope.userLoggedIn = false;
					console.error(errResponse.status);
					console.error("Error while validating");
					return $q.reject(errResponse);
				});
	},
	
	logout: function()
	{
		console.log("Entering Logout")
		return $http.get(BASE_URL + "logout")
		.then
		(
			function(response)
			{
				return response.data;
			},
			function (errResponse)
			{
				console.log("Error Logging out");
				return $q.reject(errResponse);
			}
		);
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
