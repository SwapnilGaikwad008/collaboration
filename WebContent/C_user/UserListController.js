app.controller('UserListController', function($scope, $location, UserService, $rootScope, $cookieStore, $http)
{
		console.log("Entering User Controller")
		var self = this;
		$scope.users=[];
		
		
		listUser=function()
		{
			console.log("Entering List Users Method")
			UserService.listUser()
			.then
			(
				function(response)
				{
					console.log("List Users Successful")
					$scope.users=response
				}
			)
		}
		listUser();
})