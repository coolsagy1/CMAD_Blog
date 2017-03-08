(function () {
	var app = angular.module('blogApp',['ngRoute']);  

	
	app.controller('BlogController', ['$http', function($http){
		console.log("Entered BlogController");
		$('#logindrop').show();
		$('#logoutdrop').hide();
		
		var blog = this;
		blog.title = "HeyBuddy";
		blog.posts = {};
		$http.get('http://localhost:8080/blog/online/user/posts').success(function(data){
			blog.posts = data;
		});

		blog.tab = 'blog';

		blog.selectTab = function(setTab){
			blog.tab = setTab;
		};

		blog.isSelected = function(checkTab){
			return blog.tab === checkTab;
		};

		blog.post = {};


		/*Adding Post Module*/
		blog.addPost = function(){

			blog.post.createdTime = Date.now();
			blog.post.comments = [];
			blog.post.likes = 0;
			//alert(blog.post.title)
			blog.post.title	=	blog.post.title; 
			blog.post.body	=	blog.post.body;
			blog.post.imageURL	=	blog.post.image;



			var newPost = {					
					title 		: blog.post.title,
					body 		: blog.post.body,
					likes 		: 0,
					createdTime : blog.post.createdTime,
					author	: blog.post.author,
					imageURL 	: blog.post.image,
					comments	: []
			};

			console.log(JSON.stringify(blog.post));
			console.log(JSON.stringify(newPost));
			// $http.put('data/postData.json',blog.post);
			blog.posts.unshift(this.post);
			//blog.posts.push(this.post);
			blog.tab = 0;
			console.log(blog.post);
			$http.post('http://localhost:8080/blog/online/user/posts',newPost).success(function(data){
				alert(JSON.stringify(data));
				console.log(JSON.stringify(data));
			});
			blog.post ={};
		};   
	}]);
  app.controller('SigninController', function($scope, $http,$location) {
	  console.log(" Method called");
	  $scope.name="";
	  $scope.email="";
	  $scope.password="";
		$scope.signin = function() {
			console.log("signin Method called");
			console.log($scope.name);
			console.log($scope.email);
			console.log($scope.password);
			console.log("change location");
			var user = {
					name: $scope.name,
					password: $scope.password,
					email: $scope.email
			}
			console.log(JSON.stringify(user));
			 $location.url('/login');
			var signup = $http.post("online/user/signup",user);
			signup.success(function() {
				console.log("signup.success");
				$location.url('/login');
			});
			signup.error(function() {
				console.log("signup.failure");
			});
			
		};
//		$scope.emitLogoutStatus = function(data) {
//			console.log(data);
//			if (data) {
//				$rootScope.$emit("logout");
//			} else {
//				$rootScope.$emit("logout");
//			}
//      };
		
//		function resetData() {
//          $rootScope.globals = {};
//          $cookieStore.remove('globals');
//          $http.defaults.headers.common.Authorization = '';
//      };
	});
  app.controller('forgetController', function($scope, $http,$location) {
	  console.log("  Method called");
	  $scope.email="";
	  $scope.password="";
		$scope.forget = function() {
			console.log("forget Method called");
		
			console.log($scope.email);
			
			console.log("change location");
			 $location.url('/login');

		};

	});
	app.controller('CommentController',['$http', function($http){
		this.comment = {};
		this.addComment = function(post){
			this.comment.createdOn = Date.now();
			post.comments.push(this.comment);
			this.comment ={};
			var url = 'http://localhost:8080/blog/online/user/posts';
			console.log(JSON.stringify(post));
			$http.put(url,post).success(function(data){
			});
		};
	}]);
		
	app.controller('LoginController', function($http,$rootScope,$location){
		console.log("Entered LoginController1");
		$('#logindrop').show();
		$('#logoutdrop').hide();
		var credentials = this;
		this.login = function(credentials){
			console.log("inside login method");
			console.log(credentials);
			console.log(JSON.stringify(credentials));
			var auth = btoa(credentials.email + ":" + credentials.password);
			console.log('Basic' + auth);
			$http.defaults.headers.common['Authorization'] = 'Basic ' + auth;
			var url = 'http://localhost:8080/blog/online/user/signin';			
			var login = $http.post(url);
			
			login.success(function(data){
				console.log("Login Sucess!"+JSON.stringify(data));
				if (credentials.email !== undefined) {
					setCredentials(data);
				}
				$location.url('/home');
				$('#logindrop').hide();
				$('#logoutdrop').show();
			});
			login.error(function(data){
				console.log("Login Failed!");
				$('#logindrop').show();
				$('#logoutdrop').hide();
				resetData();
			});
		};
		
		function resetData() {
            $http.defaults.headers.common.Authorization = '';
            $rootScope.globals = {};
        };
		
		function setCredentials(data) {
            var authdata = btoa(data.username + ":" + data.password); 
            $rootScope.globals = {
                currentUser: {
                    'username': data.username,
                    'authdata': data.authdata,
                    'name':	data.name
                }
            };
            console.log("Credentials Set");
            //var name = '<%= session.getAttribute("userName") %>';
        }
	});
	
	app.directive('siteHeader', function() {
		return {
			retrict: 'E',
			templateUrl: "templates/site-header.html"
		};
	});
	app.directive('siteFooter', function() {
		return {
			retrict: 'E',
			templateUrl: "templates/site-footer.html"
		};
	});
	
  app.config(function($routeProvider) {
		
		$routeProvider.when('/', {
			templateUrl: "templates/blog.html"
		}).when('/about', {
			templateUrl: "templates/about.html"
		}).when('/login', {
			templateUrl: "templates/login.html"
		}).when('/logout', {
			templateUrl: "templates/login.html"
		}).when('/signup', {
			templateUrl: "templates/signup.html"
		}).when('/forget',{
			templateUrl: "templates/forget.html"
		}).when('/home', {
			templateUrl: "templates/blog.html"
		}).when('/not-supportedfb', {
			templateUrl: "templates/not-supportedfb.html"
		}).when('/contact-us', {
			templateUrl: "templates/contact-us.html"
		}).when('/not-supported', {
			templateUrl: "templates/not-supportedtwt.html"
		}).when('/auth-failed', {
			templateUrl: "templates/auth-failed.html"
		}).when('/new-post',{
			templateUrl: "templates/new-post.html"
		}).otherwise({
			redirectTo: '/home'
		});;
	});
})();