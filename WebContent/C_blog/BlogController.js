app.controller('BlogController', function($scope,$location,BlogService)
{
	console.log("Entering Blog Controller")
	var self = this;
	$scope.blogs = [];
	$scope.blogComments = [];
	$scope.blogComment={id:'', blog_id:'', comment:'', postedAt:'', rating:'', username:''};
	$scope.blog={blog_id:'', blog_title:'', description:'', username:'', status:'', date_time:'', rejected:'', errorMsg:'', errorCode:''};
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
					alert("Blog Added. Waiting for admin approval")
					console.log("Add Blog Success "+response.status)
					$location.path("/viewBlogs")
				}
		);
	}
	
	$scope.getBlog = getSelectedBlog
	function getSelectedBlog(blog_title)
	{
		console.log("Entering Get blog "+blog_title)
		BlogService.getBlog(blog_title)
		.then
		(
				function(response)
				{
					console.log("Get Blog Success "+response.status)
					console.log(response)
					$scope.blog=response;
				}
		)
		BlogService.getComments(blog_title)
		.then
		(
				function(response)
				{
					console.log("Get comments for "+blog_title)
					console.log(response.data)
					console.log(response)
					$scope.blogComments = response;
				}
		);
		$location.path("/viewBlog")
	}
	
	this.addComment = addComment
	function addComment(blog_title)
	{
		console.log(blog_title)
		console.log($scope.blogComment.rating)
		$scope.blogComment.blog_id = blog_title;
		BlogService.addComments(blog_title, $scope.blogComment)
		.then
		(
			function(response)
			{
				console.log("Add Blog Comment "+response.status)
			}
		);
		BlogService.getBlog(blog_title)
		.then
		(
				function(response)
				{
					console.log("Get Blog Success "+response.status)
					console.log(response)
					$scope.blog=response;
				}
		)
		BlogService.getComments(blog_title)
		.then
		(
				function(response)
				{
					console.log("Get comments for "+blog_title)
					console.log(response.data)
					console.log(response)
					$scope.blogComments = response;
				}
		);
		$location.path("/cmred")
	}
})