function writeAddUserModel() {
	document.write("<div class='modal fade' id='addUserModal' data-keyboard='false' data-backdrop='static'> <div class='modal-dialog'> <div class='modal-content'> <div class='modal-header'> <h4 class='modal-title'>Add User Details</h4> <button type='button' class='close' data-dismiss='modal'>&times;</button> </div> <div class='modal-body'> <div class='form-row'> <div class='form-group col-md-6'> <label for='userName'>UserName</label> <input type='text' class='form-control' id='userName' name='userName'> </div> <div class='form-group col-md-6'> <label for='inputEmail4'>Email</label> <input type='email' class='form-control' id='userEmail' name='userEmail'> </div> </div> <div class='form-group'> <label for='displayName'>Display Name</label> <input type='text' class='form-control' name='displayName' id='displayName' /> </div>  <div class='form-group'> <label for='userType'>User Type&nbsp;&nbsp;&nbsp;</label> <div class='form-check form-check-inline'> <input class='form-check-input' type='radio' name='userType' id='userType' value='A'> <label class='form-check-label' for='userType'>Admin</label> </div> <div class='form-check form-check-inline'> <input class='form-check-input' type='radio' name='userType' id='userType' value='U' checked='checked'> <label class='form-check-label' for='userType'>Regular</label> </div> </div> <div class='form-group'> <label for='address1'>Address</label> <textarea class='form-control' id='address1' name='address1' rows='3'></textarea> </div> <div class='form-group'> <label for='address2'>Landmark</label> <input type='text' class='form-control' id='address2' name='address2'/> </div> <div class='form-row'> <div class='form-group col-md-6'> <label for='city'>City</label> <input type='text' class='form-control' id='city' name='city'> </div> <div class='form-group col-md-4'> <label for='state'>State</label> <select id='state' class='form-control'> <option value='TN' selected>Tamilnadu</option> <option value='KA'>Karnatakka</option> </select> </div> <div class='form-group col-md-2'> <label for='pincode'>Zip</label> <input type='text' class='form-control' name='pincode' id='pincode'> </div> </div> </div>  <div class='modal-footer'> <button type='button' class='btn btn-primary' id='addUserBtn' name='addUserBtn'>Add User</button> <button type='button' id='clearAddFDBtn' class='btn btn-secondary' data-dismiss='modal'>Close</button> </div> </div> </div> </div>");
}
function addUserAction() {
	$('#addUserBtn').click(function() {
		alert("Doc you want to add the User.");
		userName = $('#userName').val();
		userEmail = $('#userEmail').val();
		displayName = $('#displayName').val();
		userType = $('input[name=userType]:checked').val();
		address1 = $('#address1').val();
		address2 = $('#address2').val();
		city = $('#city').val();
		state = $('#state').val();
		pincode = $('#pincode').val();
		reqToAdd = {
			"userName" : userName,
			"userEmail" : userEmail,
			"displayName" : displayName,
			"userType" : userType,
			"address1" : address1,
			"address2" : address2,
			"city" : city,
			"state": state,
			"pincode": pincode,
			"userID" : $('#userID').val()
		};
		$.ajax({
			url : 'rest/user/registerUser',
			type : 'post',
			data : JSON.stringify(reqToAdd),
			contentType : 'application/json',
			dataType : "json",
			success : function(data) {
				if (data.statusCode == 200) {
					alert('User Added successfully');
				} else {
					alert('User addition Unsuccessfully');
				}
				resetAddFDModelForm();
			}
		});

	});
}