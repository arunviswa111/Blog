

'job strict'; /* strictly follows the case i.e--case sensitive */

app.service('JobService', [
		'$http',
		'$q',
		'$rootScope',
		function($http, $q, $rootScope) {
			console.log("job SERvices.... in job service//")
			var BASE_URL = 'http://localhost:9080/Bestiesb'
			return {
								
				applyForJob:function(jobId){
					return $http.post(BASE_URL+'/applyForJob/'+jobId)
					.then(
							function(response){
								console.log('Calling job in job service....... ')
								return response.data;
							},
							function(errResponse){
								console.error('Error while applying job');
								return $q.reject(errResponse);
							}				
			);
		},
		getJobDetails:function(jobId){
			return $http.get(BASE_URL+'/getJobDetails/'+jobId)
			.then(
					function(response){
						console.log('calling getjobdetails in jobService......js');
						$rootScope.selectedJob=response.data;
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting job details');
						return $q.reject(errResponse);
					}				
		);
		},
				

		getMyAppliedJobs : function() {
					console.log("   /getMyAppliedJobs/.... in job service//")
					return $http.get(BASE_URL + '/getMyAppliedJobs/' ).then(
							function(response) {
								return response.data;
							},function(errResponse){
								console.error('Error while getting getMy Applied jobs in sevice..js');
								return $q.reject(errResponse);
							}	

					);
				},

				postAJob : function(job) {
					console.log("   calling reject....in job service//")
					return $http
							.post(BASE_URL + '/postAJob/',job)
							.then(function(response) {
								return response.data;
							},function(errResponse){
								console.error('Error while posting a job in...sevice..js');
								return $q.reject(errResponse);
							}	


							);
				},

			

				rejectJobApplication : function(userId,jobId) {
					console.log("   reject jobr....in job service//")
					return $http.put(BASE_URL + '/rejectJobApplication/'+userId+"/"+ jobId).then(
							function(response) {
								console.error(' successss rejecting a job in...sevice..js');
								return response.data;
							},function(errResponse){
								console.error('Error while rejecting a job in...sevice..js');
								return $q.reject(errResponse);
							}	

					);
				},

				callForInterview : function(userId,jobId) {
					console.log("   call for interview jobr....in job service//")
					return $http.put(BASE_URL + '/callForInterview/'+userId, jobId).then(
							function(response) {
								return response.data;
							}, null

					);
				},

				selectUser : function(userId,jobId) {
					console.log("   calling the authenticate jobr....in job service//")
					return $http.put(BASE_URL + '/selectUser/'+userId, jobId).then(
						
							function(response) {
								console.log("//select user in job service .js....")
								return response.data;
							}, function(errResponse) {
								console.error('Error while selecting user ..in job service');
								return $q.reject(errResponse);
							}

					);
				},

				
				getAllJobs : function() {
					console.log("   /getAllJobs/....in job service//")
					return $http.get(BASE_URL + '/getAllJobs/').then(
							function(response) {
								console.error(' successss /getAllJobs/ a job in...sevice..js');
								return response.data;
							},function(errResponse){
								console.error('Error while /getAllJobs/ in...sevice..js');
								return $q.reject(errResponse);
							}	

					);
				},
			}
		}
		
		]

);

