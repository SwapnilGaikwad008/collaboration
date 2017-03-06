var app = angular.module('Collaboration',['ngRoute', 'ngCookies']);

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
		.when('/logout',
	{
		templateUrl : 'views/home.html',
		controller : 'UserController'
	})
		.when('/register',
	{
		templateUrl : 'C_user/register.html',
		controller : 'UserController'
	})
		.when('/viewUsers',
	{
		templateUrl : 'C_user/userList.html',
		controller : 'UserController'	
	})
		.when('/viewBlogs',
	{
		templateUrl : 'C_blog/blogs.html',
		controller : 'BlogController'	
	})
		.when('/viewJobs',
	{
		templateUrl : 'C_job/jobs.html',
		controller : 'JobController'	
	})
	
	.otherwise({redirectTo: '/'});
});

app.run( function ($rootScope, $location, $cookieStore, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		
		 var userPages = ['/viewUsers']
		 var adminPages = ["/viewBlogs","/viewJobs"]
		 
		 var currentPage = $location.path()
		 
		 var isUserPage = $.inArray(currentPage, userPages) ==1;
		 var isAdminPage = $.inArray(currentPage, adminPages) ==1;
		 
		 var isLoggedIn = $rootScope.currentUser.username;
	        
	     console.log("isLoggedIn:" +isLoggedIn)
	     console.log("isUserPage:" +isUserPage)
	     console.log("isAdminPage:" +isAdminPage)
	        
	        if(!isLoggedIn)
	        	{
	        	
	        	 if (isUserPage || isAdminPage) {
		        	  console.log("Navigating to login page:")
		        	  alert("You need to loggin to do this operation")

						            $location.path('/');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 
				 if(isAdminPage && role!='ADMIN' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/login');
					 
					 }
	        	}
	 }
	       );
	 
	 
	 // keep user logged in after page refresh
    $rootScope.currentUser = $cookieStore.get('currentUser') || {};
    if ($rootScope.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
    }

});