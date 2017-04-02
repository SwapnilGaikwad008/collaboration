app.factory('BlogService', function($http, $q, $rootScope)
{
	console.log("Entering BlogService")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
		
	listBlog: function()
	{
		console.log("Entering Blog List")
		return $http.get(BASE_URL + "admin_getAllBlogs")
		.then(function(response) 
		{
			console.log(response.data)
			console.log(response.status)
			return response.data
		})
	},
	
	addBlog: function(blog)
	{
		console.log("Entering Function Add Blog")
		return $http.post(BASE_URL + "addBlog", blog)
		.then(function(response)
			{
				console.log(response.status)
				return response.status
			},function(response)
			{
				console.log(response.status)
				return response.status
			});
	},
	
	getBlog: function(blog_title)
	{
		console.log("Get Blog in Service: "+BASE_URL + "getBlog-"+blog_title)
		return $http.get(BASE_URL + "getBlog-"+blog_title)
		.then(function(response)
		{
			console.log(response.status)
			console.log(response.data.errorCode)
			if(response.data.errorCode != 100)
				$rootScope.gBlog = response.data
			console.log(gBlog)
			return response
		}, function(response)
		{
			console.log(response.status)
			return response
		});
	},
	
	getComments: function(blog_title)
	{
		console.log("Get Comments for "+BASE_URL + "getComments-"+blog_title)
		return $http.get(BASE_URL + "getComments-"+blog_title)
		.then(
		function(response)
		{
			console.log(response.status)
			console.log(response)
			$rootScope.blogComments = response.data
			return response;
		});
	},
	
	addComments: function(blog_title, blogComment)
	{
		console.log("Adding Comments")
		return $http.post(BASE_URL + "addBlogComment", blogComment)
		.then(
		function(response)
		{
			console.log(response.status)
			console.log("Added Blog")
			return response;
		});
	}
	
	}
})