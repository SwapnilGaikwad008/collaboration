app.controller('UserController', function($scope, $location, UserService, $rootScope, $cookieStore, $http)
{
		console.log("Entering User Controller")
		var self = this;
		
		$scope.users;
		$scope.user={username:'', first_name:'', last_name:'', dob:'', gender:'', mail_id:'', password:'', status:'', role:'', isOnline:'', birthdate:'', last_seen:'', errorMsg:'', errorCode:''};
		$scope.message;
		$scope.friend;
		$scope.myJobs;
		
		self.registerUser=function()
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
						alert("Your Registeration is Not Yet Approved. Please wait for some time.");
						user.setErrorCode("404");
						user.setErrorMsg("Your Registeration is Not Approved");
					}
					if($scope.user.username == null)
					{
						alert("Invalid Username or Password");
						console.log("Invalid Username or Password")
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
								birthdate: $scope.user.birthdate,
								isOnline: $scope.user.isOnline,
								last_seen: $scope.user.last_seen
							};
						$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
						$cookieStore.put('currentUser', $rootScope.currentUser)
						$location.path("/");
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
		};
		
		self.friendRequest= function(username)
		{
			console.log("Entering Send Friend Request");
			UserService.friendRequest(username)
			.then 
			(
				function(response)
				{
					console.log(response.status);
					alert('FriendRequest is Sent');
					listUser();
					$location.path("/viewUsers");
				}, function(errResponse)
				{
					console.error("Error sending Friend Request");
					$location.path("/viewUsers");
				}
			)
		};
		
		self.applyJob= function(job)
		{
			console.log("Entering Job Apply")
			UserService.applyJob(job)
			.then
			(
				function(response)
				{
					console.log("Job Applied")
					alert("You applied for the Job")
					$location.path("/viewJobs")
				}
			)
		};
		
		$scope.getProfile = function(username)
		{
			console.log("Entering View Friend")
			UserService.getProfile(username)
			.then
			(
				function(response)
				{
					console.log("Friend Retrieved")
					$location.path("/viewProfile")	
				}
			)
		};
		
		getAppliedJobs = function()
		{
			console.log("Getting Applied Jobs")
			UserService.getAppliedJobs()
			.then
			(
				function(response)
				{
					console.log("Applied Jobs Recieved")
					$scope.myJobs = response.data;
				}
			)
		}
		getAppliedJobs();		
})