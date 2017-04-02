app.controller('EventController', function($scope, $location, EventService)
{
	console.log("Entering Event Controller")
	var self = this;
	$scope.events;
	$scope.event;
	$scope.message;
	
	listEvents = function()
	{
		console.log("Entering List all events")
		EventService.listEvents()
		.then
		(
				function(response)
				{
					console.log("List Events Success")
					$scope.events = response.data;
				}
		)
	}
	listEvents();
	
	self.addEvents=function()
	{
		console.log("Add Event Started")
		EventService.addEvent($scope.event)
		.then
		(
				function(response)
				{
					console.log("Add Event Success "+response.status)
					alert("Event has been added")
					$location.path("/admin")
				}
		);
	}
}
		

)