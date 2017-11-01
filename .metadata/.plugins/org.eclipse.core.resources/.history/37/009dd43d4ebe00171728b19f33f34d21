<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/libs/bootstarp/3.2.0/bootstrap.min.js"></script>


<script>
	function validateform() {

		var name = document.myform.fullName.value;
		if (name == null || name == "") {
			alert("Name can't be blank");
			return false;
		}
		/*  var email=document.myform.email.regEx;
		 var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		 if(regEx!=email){
		 alert("Please enter valid email");
		 return false;
		
		 }  */
		var password = document.myform.password.value;
		var confirmPw = document.myform.confirmPw.value;
		if (password.length < 4) {
			alert("Password must be at least 5 characters long.");
			return false;

		}
		if (password == confirmPw) {
			return true;
		} else {
			alert("password not matched")
			return false;
		}
		var phoneNo = document.myform.phoneNo.value;
		/* if(isNaN(phoneNo)){
			alert("Invalid phone number can't be blank");
			return false;
		} */
		if (phoneNo.length() != 8) {
			alert("phone number must have 8 digits");
			return false;
		}

		return true;
	}
</script>
<style type="text/css">
body {
	padding-top: 2px;
	background-color: aqua;
	font-size: 23px;
}

p.dotted {
	border-style: dotted;
}

test {
	margin: 0 auto;
}

#pid {
	float: none;
	margin: 0 auto;
	background-color: white;
}
</style>
<title>Registration Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5" id="pid">
				<br>
				<form action="Registration" method="post" id="fileForm"
					onsubmit="return validateform()" name="myform">
					<h3 class="text-center">Create an Account</h3>

					<div class="form-group">

						<label for="FullName">Full Name</label> <input type="text"
							id="pid" class="form-control" placeholder="Enter Name"
							name="fullName">
					</div>

					<div class="form-group">
						<label for="email">Email</label> <input class="form-control"
							type="text" id="email" placeholder="Enter Email" name="email">
					</div>


					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" placeholder="Enter Password" id="pass1"
							name="password">
					</div>
					<label for="password"> Confirm Password </label>
					<div class="form-group">
						<input type="password" class="form-control"
							placeholder="Retype Password" id="pass2" name="confirmPw">
					</div>
					<div class="form-group">
						<label for="email">Phone No.</label> <input class="form-control"
							type="text" id="email" placeholder="(xxx)-xxx-xxxx"
							name="phoneNo"> <br>
						<div class="form-group">
							<p class="dotted">
								By Clicking on the create an account button bellow, you
								certified that you have read and agree to our <a
									href="terms of use">terms of use</a> and <a href="privacy">privacy</a>
							</p>
						</div>
					</div>

					<div class="form-group">
						<div class="btn-group btn-group-justified">
							<div class="btn-group">
								<button type="submit" class="btn btn-success">Create an
									account</button>
							</div>
						</div>
					</div>
					<p>
						create an account?<a href="Login.jsp">Sign in.</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>