app.controller('UserController', function($scope, $location, UserService, $rootScope, $cookieStore, $http)
{
		console.log("Entering User Controller")
		var self = this;
		$scope.users;
		$scope.user={username:'', first_name:'', last_name:'', dob:'', gender:'', mail_id:'', password:'', status:'', role:'', isOnline:'', birthdate:'', errorMsg:'', errorCode:''};
		$scope.message;
		$scope.message1;
		$scope.registerUser=function()
		{
			console.log("Entering Register User")
			UserService.registerUser($scope.user)
			.then
			(
				function(response)
				{
					console.log("Registeration Successful "+response.status)
					$location.path("/home")
				}
			);
		};
		
		self.authenticate = function(user)
		{
			console.log("Authenticate Function");
			UserService.authenticate(user)
			.then 
			(
				function(d)
				{
					$scope.user = d;
					console.log("User ErrorCode - "+$scope.user.errorCode)
					if($scope.user.status == 'R')
						{
							alert("Your Registeration is Rejected. Please Contact ADMIN");
							user.setErrorCode("404");
							user.setErrorMsg("Your Registeration is Rejected");
						}
					if($scope.user.status == 'N')
					{
						alert("Your Registeration is Not Approved.");
						user.setErrorCode("404");
						user.setErrorMsg("Your Registeration is Not Approved");
					}
					if($scope.user.username == null)
					{
						alert("Status is NULL.");
						console.log("Status is Null")
						$location.path("/login");
					}
					
					else
					{
						console.log("Valid Credentials, Navigating to home page "+$scope.user.status)
						$scope.message1="Successfully Logged in as ";
						$rootScope.currentUser = 
							{
								username: $scope.user.username,
								first_name: $scope.user.first_name,
								last_name: $scope.user.last_name,
								dob: $scope.user.dob,
								gender: $scope.user.gender,
								mail_id: $scope.user.mail_id,
								status: $scope.user.status,
								role: $scope.user.role,
								password: $scope.user.password,
								birthdate: $scope.user.birthdate,
								isOnline: $scope.user.isOnline
							};
						$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
						$cookieStore.put('currentUser', $rootScope.currentUser)
						$location.path("/home");
					}
				}, 	function(errResponse)
				{
					console.error("Error Authenticating User");
					$scope.message = "Invalid username or password.";
					$location.path("/login");
				}
			);
		};
		
		self.login= function()
		{
			console.log("Validating Login "+$scope.user);
			self.authenticate($scope.user);
		};
		
		self.logout= function()
		{
			console.log("Entering Logout Function");
			$rootScope.currentUser = {};
			$cookieStore.remove('currentUser');
			
			console.log("Calling Session Logout");
			UserService.logout()
			$location.path('/login');
		}
	
		listUser=function()
		{
			console.log("Entering List Users Method")
			UserService.listUser()
			.then
			(
				function(response)
				{
					console.log("List Users Successful"+response.status)
					$scope.users=response
				}
			)
		}
		listUser();
})