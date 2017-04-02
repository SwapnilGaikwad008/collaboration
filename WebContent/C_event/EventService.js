app.factory('EventService', function($http, $q, $rootScope)
{
	console.log("Entering Event Service")
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd/"
	return{
		
	listEvents: function()
	{
		console.log("Entering Event List")
		return $http.get(BASE_URL + "getEvents")
		.then
		(
			function(response) 
			{
				console.log(response.data)
				console.log(response.status)
				return response
			}
		)
	},
	
	addEvent : function(event)
	{
		console.log("Entering Service Add Event")
		return $http.post(BASE_URL + "addEvent", event)
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