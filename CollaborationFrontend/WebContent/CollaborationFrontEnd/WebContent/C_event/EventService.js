app.factory('EventService', function($http){
	console.log('Entered Event Service')
	var BASE_URL = "http://localhost:8081/CollaborationBackEnd"
		return{
		registerEvent : function(event){
			return $http.post(BASE_URL + "addEvent/", event).then(
					function(response){
						console.log(response.status)
						console.log(response.headers.location)
						return response.status
					},
					function(response){
						console.log(response.status)
						return response.status
					});
		},
		
		getAllEvents : function(){
			console.log('Entering getAllEvents')
			return $http.get(BASE_URL + "listEvent")
		}
	}
})