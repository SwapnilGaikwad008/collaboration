app.controller('JobController', function($scope,$location,JobService){
	console.log("Executing Job Controller")
	$scope.jobs;
	$scope.job={job_id:'', title:'', username:'', status:'', position:'', company:'', description:'', salary:'', location:'', q_10:'', q_12:'', q_ug:'', date:'', vacancy:''};
    $scope.message;
    
    listJob=function()
    {
    	console.log("Executing List Job")
    	JobService.listJob()
    	.then
    	(
    			function(response)
    			{
    				console.log("List Job Success"+response.status)
    				$scope.jobs=response
    			})
    }
    listJobs();
    $scope.addJob=function()
    {
    	console.log("Executing Add Job")
    	Jobservice.addJob($scope.job)
    	.then
    	(
    			function(response){
    				console.log("Add Job successfully" +response.status)
    				$location.path("/")
    			});
    }
})