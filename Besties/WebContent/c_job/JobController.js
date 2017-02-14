

	
'use strict';

app
		.controller(
				'JobController',
				[
						
						'JobService','UserService',
						'$location',
						'$rootScope',
						
						function(JobService,UserService, $location, $rootScope) {
						
							console.log("jobController.....js")
							var self = this;
							
							self.job = {
								jobid : '',
								title : '',
								qualification : '',
								created_date : '',
								description : '',
								status : '',
								errorCode : '',
								errorMessage : '',
							};
							self.jobs = [];
							
							self.applyForJob = applyForJob
							
							
							/*APPLY FOR JOB*/
							
							function applyForJob(jobId){
								console.log("*****applyForJob***in jobcontroller.js");
								var currentUser = $rootScope.currentUser
								console.log("currentUser.id:"+currentUser.userid)
								//if (current user) ->>>not null,not empty and defined
								if(typeof currentUser.userid == 'undefined')
									{
									alert("please login to apply for job")
									console.log("user is not logged in***in job controller.js**")
									return
									}
								console.log("**userId::"+currentUser.jobId+"applying for job.."+jobId)
								JobService
										.applyForJob(jobId)
										.then(
												function(d){
														self.job=d;
														alert(self.job.errorMessage)
												},
												function(errResponse){
													console.log('error while applying for job request')
												});
														
							}						
										
											 
							


							/* get my applied jobS LIST........ .................................*/
							self.getMyAppliedJobs = function() {
								JobService
										.getMyAppliedJobs()
										.then(
												function(d) {
													self.jobs = d;
													console.log("get all my jobs in jobcontroller.js....")
												},
												function(errResponse) {
													console
															.error('Error while fetching jobs');
												});
							};
							

							/* CREATE job ....... ................................................*/
							self.rejectJobApplication = function(userId) {
								var jobID=$rootScope.selectedJob.jobId;
								JobService
										.rejectJobApplication(userId,jobId)
										.then(
												function(d) {
													self.jobs = d;
													alert("u hv successfully rejected appli.. jobcontroller.js...."+userId+"..."+jobId)
												},
												
												function(errResponse) {
													console
															.error('Error while rejectJobApplication job...in job controller,js...');
												});
								
							};

							self.callForInterview = function() {
								var jobID=$rootScope.selectedJob.jobId;
								console.log("callForInterview...")
								JobService
										.callForInterview()
										.then(
												function(d) {
													self.job = d;
													alert("application stats changed to call for interview")

												},
												function(errResponse) {
													console
															.error('Error while changing status call for intervie.');
												});
							};

							self.selectUser = function(userId) {
								var jobID=$rootScope.selectedJob.jobId;
								console.log("selectUser...")
								JobService
										.selectUser(userId,jobID)
										.then(
												function(d) {
													self.job = d;
													self.getMyAppliedJobs
													alert("application stats changed to selected user..")
													//alert(self.job.errorMessage)

												},

												function(errResponse) {
													console
															.error('Error while selecting user status.. job.');
												});
							};
							
							
							self.getAllJobs = function() {
								
								console.log("getAllJobs......in job controller.js")
								JobService
										.getAllJobs()
										.then(
												function(d) {
													self.jobs = d;
													
													
												},

												function(errResponse) {
													console
															.error('Error while fetching all the jobs... jobcontroller.js.');
												});
							};


							self.getAllJobs();
							
							self.submit = function() {
								{
									console.log('Saving New job', self.job);
									self.postAJob(self.job);
								}
								self.reset();
							};
							
							self.postAJob = function(job) {
								console.log("submit a jobt...",self.job);
								
								JobService.postAJob(job).then(
										function(d) {
											
											alert(self.job.errorMessage)

										}, function(errResponse) {
											console
											.error('Error while posting a job.. job.');
								});
							};

							
							
							self.getJobDetails = getJobDetails
							
							function getJobDetails(jobId)
							{
								console.log('get job details of the id',jobId)
								JobService.getJobDetails(jobId)
											.then(function(d)
													{
												self.jobs=d;
												$location.path('/view_job_details');
													},
													 function(errResponse) {
														console
														.error('Error while getting  job details.... jobcontroller.');
													});
							};
							

							self.reset = function() {
								console.log('reset job....',self.job);
								self.job = {
										jobid : '',
										title : '',
										qualification : '',
										created_date : '',
										description : '',
										status : '',
										errorCode : '',
										errorMessage : '',
								};
								//$scope.myForm.$setPristine(); // reset Form
							};

						} ]);

