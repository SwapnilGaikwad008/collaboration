app.factory('BlogService', function($http){
	
	console.log("Executing BlogService")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
		
		listBlog: function()
		{
			console.log("Executing list BlogsService")
			return $http.get(BASE_URL + "admin_getallBlogs")
			.then(function(response){
				console.log(response.data)
				console.log(response.status)
				return response.data
			})
		},
		
		addBlog: function(blog)
		{
			console.log("Executing Add Blog")
			return $http.post(BASE_URL + "addBlog", blog)
			.then(function(response){
				console.log(response.status)
				return response.status
			},
			function(response){
				console.log(response.status)
				return response.status
			});
		}
	}
})