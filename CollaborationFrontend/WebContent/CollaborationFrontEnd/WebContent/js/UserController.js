app.controller('UserController', function($scope,$location,UserService)
{
		console.log("Entering User Controller")
		$scope.users;
		$scope.user={username:'', first_name:'', last_name:'', dob:'', gender:'', mobile_no:'', place:'', mail_id:'', password:'', status:'', role:'', work_location:'', position:''};
		$scope.message;
		
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
		}
		
		$scope.login=function()
		{
			console.log("Entering Login Function")
			UserService.validate($scope.user)
			.then
			(
				function(response)
				{
					console.log("Login Success "+response.status)
					$scope.message = "Successfully Logged in"
					$location.path("/home")
				}
			);
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
					$scope.message = "Successfully Retrived list"
				}
			)
		}
		listUser();
})