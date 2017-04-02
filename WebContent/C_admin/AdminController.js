app.controller('AdminController', function($scope, $location, AdminService, UserService, BlogService, ForumService, JobService, EventService, $rootScope, $http)
{
	console.log("Admin Controller")
	var self=this;
	
	$scope.users;
	$scope.blogs;
	$scope.jobs;
	$scope.forums;
	$scope.events;
	$scope.appliedJobs;
	
	getUsers = function()
	{
		console.log("Entering Get Users")
		UserService.listUser()
		.then
		(
			function(response)
			{
				console.log("Users Recieved")
				$scope.users=response
			}
		)
	}
	getUsers();
	
	getJobs = function()
	{
		console.log("Entering Get Jobs")
		JobService.listJob()
		.then
		(
			function(response)
			{
				console.log("Jobs Recieved")
				$scope.jobs=response
			}
		)
	}
	getJobs();
	
	getForums = function()
	{
		console.log("Entering Get Forums")
		AdminService.listAllForums()
		.then
		(
			function(response)
			{
				console.log("Forums Recieved")
				$scope.forums=response.data
			}
		)
	}
	getForums();
	
	getBlogs = function()
	{
		console.log("Entering Get Blogs")
		BlogService.listBlog()
		.then
		(
			function(response)
			{
				console.log("Blogs Recieved")
				$scope.blogs=response
			}
		)
	}
	getBlogs();
	
	getEvents = function()
	{
		console.log("Entering Get Events")
		EventService.listEvents()
		.then
		(
			function(response)
			{
				console.log("Events Recieved")
				$scope.events=response.data
			}
		)
	}
	getEvents();
	
	getAppliedJobs = function()
	{
		console.log("Entering Applied Jobs")
		AdminService.getAppliedJobs()
		.then
		(
			function(response)
			{
				console.log("Applied Jobs Recieved")
				$scope.appliedJobs = response.data;
			}
		)
	}
	getAppliedJobs();
	
	self.invalidateJob = function(id)
	{
		console.log("Entering invalidate "+id)
		AdminService.invalidateJob(id)
		.then
		(
			function(response)
			{
				console.log("Job Invalidated")
				$location.path("/jred")
			}
		)		
	}
	
	self.acceptUser = function(user)
	{
		console.log("Accepting User")
		user.status = 'A'
		AdminService.updateUser(user)
		.then
		(
			function(response)
			{
				console.log("Accepted User")
				$location.path("/manageUsers")
			}
		)
	}
	
	self.rejectUser = function(user)
	{
		console.log("Rejecting User")
		user.status = 'R'
		AdminService.updateUser(user)
		.then
		(
			function(response)
			{
				console.log("Rejected User")
				$location.path("/manageUsers")
			}
		)
	}
	
	self.acceptBlog = function(blog)
	{
		console.log("Entering Approve Blog")
		blog.status = 'Approved'
		AdminService.approveBlog(blog)
		.then(
			function(response)
			{
				console.log("Blog Accepted "+response)
				$location.path("/manageBlogs")
			}
		)
	}
	
	self.rejectBlog = function(blog)
	{
		console.log("Entering Reject Blog")
		blog.status = 'Rejected'
		AdminService.rejectBlog(blog)
		.then(
			function(response)
			{
				console.log("Blog Rejected "+response)
				$location.path("/manageBlogs")
			}
		)
	}
	
	self.acceptForum = function(forum)
	{
		console.log("Entering Accept Forum")
		forum.status = 'A'
		AdminService.acceptForum(forum)
		.then(
			function(response)
			{
				console.log("Forum Accepted")
				$location.path("/manageForums")
			}
		)
	}
	
	self.rejectForum = function(forum)
	{
		console.log("Entering Reject Forum")
		forum.status = 'R'
		AdminService.rejectForum(forum)
		.then(
			function(response)
			{
				console.log("Forum Rejected")
				$location.path("/manageForums")
			}
		)
	}
	
	self.removeEvent = function(id)
	{
		console.log("Removing Event")
		AdminService.removeEvent(id)
		.then(
			function(response)
			{
				console.log("Event Removed")
				$location.path("/ered")
			}
		)
	}
}
)