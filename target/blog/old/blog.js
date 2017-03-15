$(document)
		.ready(
				
				function() {
					var globaluser = null;
					
					$("#logoutlink").click(
							function() {
								alert("are you sure you want to logout");
								$('.logout').hide();
								globaluser = null;
								$('#SignInForm').show();
								$('#blogpostPage').hide();
							}
						);
					$('#SignUpForm').hide();
					$('#SignInForm').show();
					$('#blogpostPage').hide();					
					$('#createPost').hide();
					$('#logoutId').hide();
					
					
					$("#SignInlink").click(
							function() {
								$('#SignUpForm').hide();
								$('#SignInForm').show();
							}
						);
					$("#SignUplink").click(
							function() {
								$('#SignUpForm').show();
								$('#SignInForm').hide();
							}
						);
						
						$("#createPostLink").click(
							function() {
								$('#createPost').show();
							}
						);
						
					
					console.log("hide both");
					
					$('#create')
							.click(
									function() {
										console.log("1");
										document.getElementById("signUpError").innerHTML ="";
									
										var name = $("#name").val();
										var email = $("#email").val();
										var atpos = email.indexOf("@");
									    var dotpos = email.lastIndexOf(".");
										var pwd = $("#pwd")
										.val();
										
										  if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
										        //alert("Not a valid e-mail address");
										        document.getElementById("signUpError").innerHTML = "Enter valid e-mail address";
										        return false;
										    }
										  
										if(pwd.length <1){
											
											document.getElementById("signUpError").innerHTML ="Enter Password;"
											return false;
										}
										if(pwd != $("#cpwd").val()){
											alert("Password should be same");
											document.getElementById("signUpError").innerHTML ="Password should be same;"
											return false;
										}
										
									  
										var data = {
										
										email : email,
										password : pwd,
										name : name
												
										};
										console.log("before call");
										$
												.ajax({
													url : 'http://localhost:8080/blog/online/user/signup',
													
													type : 'post',
													contentType : 'application/json',
													success : function(response) {
														console.log(response);
														document.getElementById("signUpError").innerHTML ="";
														
														$('#SignUpForm').hide();
														$('#SignInForm').show();
														console.log(response.status);
													},
													error : function(response) {
														
													
														document.getElementById("signUpError").innerHTML ="Email already exists ";	
													},
													data : JSON.stringify(data)
												});
									});
					
					$('#login')
					.click(
							function() {
								console.log("login");
								document.getElementById("signInError").innerHTML = "";
								
								var email = $("#lemail").val();
								var pwd = $("#lpwd").val();
								var atpos = email.indexOf("@");
							    var dotpos = email.lastIndexOf(".");
							    var listdata = new Array("Apples","Bananas","Pineapples","Peaches","Grapes");
							    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
							    	document.getElementById("signInError").innerHTML = "Incorrect Email"
							        //alert("Not a valid e-mail address");
							       // return false;
							    }
							    if(pwd.length <1){
									
									document.getElementById("signInError").innerHTML ="Enter Password;"
									//return false;
								}
								var data = {
								
								email : email,
								password : pwd
								
										
								};
								console.log("before call");
								$
										.ajax({
											url : 'http://localhost:8080/blog/online/user/signin',
											
											type : 'post',
											contentType : 'application/json',
											success : function(response) {
												console.log(response);
												document.getElementById("signInError").innerHTML = "";
												
												if(response.password == pwd){
													//alert("well Come "+ response.name);
													$('.Options').hide();
													$('#SignUpForm').hide();
													$('#SignInForm').hide();
													$('#logoutId').show();
													$('#blogpostPage').show();
													//generateList(listdata);
													document.getElementById("welcome").innerHTML = "Welcome"+" " +response.name;
													globaluser = response;
												}else{
													//alert("Incorrect password");
													document.getElementById("signInError").innerHTML = "Incorrect Password"
												}
											},
											error : function(response) {
												console.log(response);
												$('.Options').hide();
													$('#SignUpForm').hide();
													$('#SignInForm').hide();
													$('#logoutId').show();
													$('#blogpostPage').show();
												document.getElementById("signInError").innerHTML = "User doesn't exists"
											},
											data : JSON.stringify(data)
										});
							});
							
							
							$('#addpost')
					.click(
							function() {
								console.log("addpost");								
								var title = $("#title").val();
								var body = $("#body").val();
								var topic = $("#topic").val();
								
								var data = {								
								title : title,
								body : body,
								user : {"email":"ate@ate.ate"}
								};
								console.log("before addpost");
								console.log(data);
								$
										.ajax({
											url : 'http://10.142.232.231:8080/blog/online/user/addPost',											
											type : 'post',
											contentType : 'application/json',
											success : function(response) {
												var a = JSON.parse(response);
												console.log(response);
												console.log(a);
											},
											error : function(response) {
												var a = JSON.parse(response);
												console.log(response);
												console.log(a);
											},
											data : JSON.stringify(data)
										});
							});
							
							
					
					//Generate list 
					function generateList(listdata){
						// selects the div with an id of placeholder
						 var div = document.getElementById("postList");

						 // say that fruits contains all your data
						 
						     ul = document.createElement('ul'); // create an arbitrary ul element

						 // loop through the fruits array
						 for(var i in listdata) {
						         // create an arbitrary li element
						 	var li = document.createElement('li'),
						 		 content = document.createTextNode(listdata[i]); // create a textnode to the document

						   li.appendChild(content); // append the created textnode above to the li element
						   ul.appendChild(li); // append the created li element above to the ul element
						 }

						return div.appendChild(ul); // finally the ul element to the div with an id of placeholder 
					 }
					
				});



					