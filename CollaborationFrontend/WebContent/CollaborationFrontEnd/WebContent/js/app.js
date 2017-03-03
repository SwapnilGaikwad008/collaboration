var app = angular.module('Collaboration',['ngRoute']);

app.config(function($routeProvider)
	{
	$routeProvider
	
	.when('/home',
		{
			templateUrl : 'views/home.html',
			//controller : 'HomeController'
		})
	
	.when('/login',
	{
		templateUrl : 'views/login.html',
		controller : 'UserController'
	})
	
		.when('/register',
	{
		templateUrl : 'views/register.html',
		controller : 'UserController'
	})
		.when('/viewUsers',
	{
		templateUrl : 'views/userList.html',
		controller : 'UserController'	
	})
		.when('/viewBlogs',
	{
		templateUrl : 'views/blogs.html',
		controller : 'BlogController'	
	})
		.when('/viewJobs',
	{
		templateUrl : 'views/jobs.html',
		controller : 'JobController'	
	})
	
	.otherwise({redirectTo: '/'});
});