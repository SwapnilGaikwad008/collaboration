app.factory('BlogService', function($http)
{
	console.log("Entering BlogService")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
		
	listBlog: function()
	{
		console.log("Entering Blog Service")
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
	}
	
	}
})