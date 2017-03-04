app.controller('JobController', function($scope,$location,JobService)
{
	console.log("Entering Job Controller")
	$scope.jobs;
	$scope.job={job_id:'', title:'', username:'', status:'', position:'', company:'', description:'', salary:'', location:'', q_10:'', q_12:'', q_ug:'', date:'', vacancy:''};
	$scope.message;
	
	listJob=function()
	{
		console.log("Entering List Job Method")
		JobService.listJob()
		.then
		(
				function(response)
				{
					console.log("List Job Success "+response.status)
					$scope.jobs=response
				}
		)}
		listJob();
	
	$scope.addJob=function()
	{
		console.log("Add Job Started")
		JobService.addJob($scope.job)
		.then
		(
				function(response)
				{
					console.log("Add Job Success "+response.status)
					$location.path("/")
				}
		);
	}
})