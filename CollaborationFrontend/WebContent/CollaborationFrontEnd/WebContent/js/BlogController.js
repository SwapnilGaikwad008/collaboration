app.controller('BlogController', function($scope,$location,BlogService)
{
	console.log("Entering Blog Controller")
	$scope.blogs;
	$scope.blog={blog_id:'', blog_title:'', description:'', username:'', status:'', date_time:'', rejected:''};
	$scope.message;
	
	listBlog=function()
	{
		console.log("Entering List Blog Method")
		BlogService.listBlog()
		.then
		(
				function(response)
				{
					console.log("List Blogs Successful "+response.status)
					$scope.blogs=response
				}
		)}
		listBlog();
		
	$scope.addBlog=function()
	{
		console.log("Entering Add Blog")
		BlogService.addBlog($scope.blog)
		.then
		(
				function(response)
				{
					console.log("Add Blog Success "+response.status)
					$location.path("/viewBlogs")
				}
		);
	}
})
    
    
    
   