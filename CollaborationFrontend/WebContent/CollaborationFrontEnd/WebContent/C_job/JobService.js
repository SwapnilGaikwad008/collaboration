app.factory('JobService', function($http)
{
	console.log("Entering JobService")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
		return{
		
	listJob: function()
	{
		console.log("Entering Job List Service")
		return $http.get(BASE_URL + "getAllJobs")
		.then(function(response) 
		{
			console.log(response.data)
			console.log(response.status)
			return response.data
		})
	},
	
	addJob : function(job)
	{
		console.log("Entering Service Add Job")
		return $http.post(BASE_URL + "addJob", job)
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