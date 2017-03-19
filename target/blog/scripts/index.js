(function() {
	var app = angular.module('blogApp', [ "ngRoute","ngMessages" ]);
      
	app.controller('BlogController', [
			'$http',
			function($http, $rootScope) {
				console.log("Entered BlogController");
				
				$('#logindrop').show();
				$('#logoutdrop').hide();
				
				var blog = this;
				blog.title = "HeyBuddy";
				blog.posts = {};
				blog.headtext = "Updating ..";
				blog.loading = true;
				$http.get('online/user/posts')
						.success(function(data) {
							blog.posts = data;
							blog.headtext = "Add New Post";
							blog.loading = false; 
						});

				blog.tab = 'blog';

				blog.selectTab = function(setTab) {
					blog.tab = setTab;
				};

				blog.isSelected = function(checkTab) {
					return blog.tab === checkTab;
				};

				blog.post = {};
				/*Adding Post Module*/
				blog.addPost = function() {
                 
					blog.post.createdTime = Date.now();
					blog.post.comments = [];
					blog.post.likes = 0;
					blog.post.title = blog.post.title;
					blog.post.body = blog.post.body;
					blog.post.author = blog.post.author;
					console.log("Author:"+blog.post.author);
                    

					var newPost = {
						title : blog.post.title,
						body : blog.post.body,
						likes : 0,
						createdTime : blog.post.createdTime,
						author : blog.post.author,
						imageURL : blog.post.image,
						comments : blog.post.comments
					};

					blog.posts.unshift(this.post);
					//blog.posts.push(this.post);
					blog.tab = 0;
					$http.post('online/user/posts',
							newPost).success(function(data) {
						blog.tab = 0;//blog.posts.length-1;
					});
					blog.post = {};
				};
			} ]);
	
	
	app.controller('SignupController', function($scope, $http, $location) {
		console.log(" Method called");
		$scope.name = "";
		$scope.email = "";
		$scope.password = "";
		$scope.confirmpwd = "";
		
		$scope.signup = function(isValid) {
			console.log("signin Method called");
			console.log($scope.name);
			console.log($scope.email);
			console.log($scope.password);
			console.log("ateesh");
			console.log(isValid);
			$scope.loading = true;
			console.log("change location");
			
			if(isValid){
			var user = {
				name : $scope.name,
				password : $scope.password,
				email : $scope.email
			}
			console.log(JSON.stringify(user));
			var signup = $http.post("online/user/signup", user);
			signup.success(function() {
				console.log("signup.success");
				$location.url('/login');
				$scope.loading = false;
			});
			signup.error(function() {
				console.log("signup.failure");
				$scope.loading = false;
			});			
			}
		};
	});
	
	
	
	
	app.controller('forgetController', function($scope, $http, $location) {
		console.log("  Method called");
		$scope.email = "";
		$scope.password = "";
		$scope.forget = function() {
			console.log("forget Method called");
			console.log($scope.email);
			$location.url('/login');

		};
	});
	
	
	app.controller('HeaderController', function($scope, $http, $location,$rootScope) {
		console.log("  headerController called");
		$rootScope.usrName = "Guest";
		$scope.logout = function() {
			console.log("logout Method called");
			console.log("usrName:"+$rootScope.usrName);
			$http.defaults.headers.common.Authorization = '';
			$rootScope.globals = {};
			$rootScope.showName = false;
			$rootScope.usrName = "Guest";
			console.log("usrName:"+$rootScope.usrName);
			$("#newPostAuthor").val("Guest");
			$location.url('/login');
		};
		
		
		$scope.search = function() {
		
			var result = this;
			result.posts = {};
			
			$rootScope.searchtext =$scope.searchtext;
			
			$location.url('/search');
			$scope.searchtext = " ";
		};
	});
	
	app.controller('SearchController', function($scope, $http, $location,$rootScope) {
		console.log("  search Controller called");
		var result = this;
		result.posts = {};
		result.tab = 'blog';
		 $scope.loading = true;
		 $scope.searching = "Searching ..";
		result.selectTab = function(setTab) {
			result.tab = setTab;
		};

		result.isSelected = function(checkTab) {
			return result.tab === checkTab;
		};
		 console.log(result.isSelected('blog'));
		
			//var url ="/online/user/search?searchString="+$rootScope.searchtext ;
			//console.log(url);
		$http.get('online/user/posts')
		.success(function(data) {
			result.posts = data;
			
			console.log("done");
			 $scope.loading = false;
			 $scope.searching = "Search Results";
		});
		
       console.log(result.isSelected('blog'));
		$rootScope.searchtext = "";	
		
	});

	app.controller('CommentController', [ '$http', function($http) {
		this.comment = {};
		this.addComment = function(post) {
			this.comment.createdTime = Date.now();
			console.log(JSON.stringify(post));
			console.log(post.comments);
			post.comments.push(this.comment);
			this.comment = {};
			var url = 'online/user/posts';
			console.log("post:" + JSON.stringify(post));

			var updatedPost = {
				title : post.title,
				body : post.body,
				likes : post.likes,
				createdTime : post.createdTime,
				author : post.author,
				imageURL : post.image,
				comments : post.comments
			};
			console.log("updatedPost:" + updatedPost);
			$http.put(url, updatedPost).success(function(data) {
			});
		};
	} ]);

	app.controller('LoginController', function($http, $rootScope, $location,$scope) {
		console.log("Entered LoginController1");
		$('#logindrop').show();
		$('#logoutdrop').hide();
		var credentials = this;
		
		this.login = function(credentials) {
			console.log("inside login method");
			console.log(credentials);
			console.log(JSON.stringify(credentials));
			var auth = btoa(credentials.email + ":" + credentials.password);
			console.log('Basic' + auth);
			 $scope.loading = true;
			$http.defaults.headers.common['Authorization'] = 'Basic ' + auth;
			var url = 'online/user/signin';
			var login = $http.post(url);
			
			login.success(function(data) {
				console.log("Login Sucess!"/* + JSON.stringify(data)*/);
				if (credentials.email !== undefined) {
					setCredentials(data);
				}
				$location.url('/home');
				$('#logindrop').hide();
				$('#logoutdrop').show();
				$rootScope.isloginfailed = false;
				$rootScope.usrName = data.name;
				$rootScope.showName = true;
				 $scope.loading = false;
			});
			login.error(function(data) {
				console.log("Login Failed!");
				$('#logindrop').show();
				$('#logoutdrop').hide();
				$rootScope.isloginfailed = true;
				 $scope.loading = false;
				resetData();
			});
			
		};

		function resetData() {
			$http.defaults.headers.common.Authorization = '';
			$rootScope.globals = {};
			$rootScope.showName = false;
			$rootScope.usrName = "Guest";
			$("#newPostAuthor").val("Guest");
		}
		;

		function setCredentials(data) {
			var authdata = btoa(data.username + ":" + data.password);
			$rootScope.globals = {
				currentUser : {
					'username' : data.username,
					'authdata' : data.authdata,
					'name' : data.name
				}
			};
			console.log("Credentials Set");
			// console.log("session.getAttribute('userName')"+$session.getAttribute('userName'));
			//var name = '<%= session.getAttribute("userName") %>';
		}
	});

	/*
	 * Custom Directives 
	 * siteHeader - for Header Page Display
	 * siteFooter - for Footer Page Display
	 */
	app.directive('siteHeader', function() {
		return {
			retrict : 'E',
			templateUrl : "templates/site-header.html"
		};
	});
	app.directive('siteFooter', function() {
		return {
			retrict : 'E',
			templateUrl : "templates/site-footer.html"
		};
	});

	app.directive("compareTo", function() {
		console.log("inside compare");
		return {
			require : "ngModel",
			scope : {
				otherModelValue : "=compareTo"
			},
			link : function(scope, element, attributes, ngModel) {

				ngModel.$validators.compareTo = function(modelValue) {
					return modelValue == scope.otherModelValue;
				};

				scope.$watch("otherModelValue", function() {
					ngModel.$validate();
				});
			}
		};
	});

	app.config(function($routeProvider) {

		$routeProvider.when('/', {
			templateUrl : "templates/blog.html"
		}).when('/about', {
			templateUrl : "templates/about.html"
		}).when('/login', {
			templateUrl : "templates/login.html"
		}).when('/logout', {
			templateUrl : "templates/login.html"
		}).when('/signup', {
			templateUrl : "templates/signup.html"
		}).when('/forget', {
			templateUrl : "templates/forget.html"
		}).when('/home', {
			templateUrl : "templates/blog.html"
		}).when('/not-supportedfb', {
			templateUrl : "templates/not-supportedfb.html"
		}).when('/contact-us', {
			templateUrl : "templates/contact-us.html"
		}).when('/not-supported', {
			templateUrl : "templates/not-supportedtwt.html"
		}).when('/auth-failed', {
			templateUrl : "templates/auth-failed.html"
		}).when('/search', {
			templateUrl : "templates/search.html"
		}).when('/new-post', {
			templateUrl : "templates/new-post.html"
		}).otherwise({
			redirectTo : '/home'
		});
		;
	});
})();