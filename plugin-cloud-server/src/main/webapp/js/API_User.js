/**
 * License
 * 
 * @returns
 */
function addLicPanelSubmit() {
	$.getJSON('rest/user/createLicense/' + $('#userLicenseName').val(),
			function(data) {
				$('#userLicense').val(data.userLicense);
			});
}
function addLicPanelCancel() {
	$('#licenseUserEmail').val('');
	$('#userLicense').val('');
	$('#userLicenseName').val('');
}
function loadState(stateSelect) {
	$('#'+stateSelect).empty();
	$('#'+stateSelect).append("<option value='NA'>Select a State</option>");

	$('#'+stateSelect).append("<option value='TN'>Tamil Nadu</option>");
	$('#'+stateSelect).append("<option value='KA'>Karnataka</option>");
	$('#'+stateSelect).change(function() {
		loadCity($(this).val());
	});
}
function loadCity(state) {
	$('#userCity').empty();
	$('#userCity').append("<option value='NA'>Select a City</option>");
	if(state == 'TN') {
		var tnCitySupported = ['Chennai', 'Madurai', 'Thoothukudi', 'Sivakasi'];
		for (var index = 0; index < tnCitySupported.length; index++) {
			$('#userCity').append("<option value='"+tnCitySupported[index]+"'>"+tnCitySupported[index]+"</option>");
		}
	}
	else if(state == 'KA') {
		var tnCitySupported = ['Bengaluru', 'Mandya', 'Chikmangalur'];
		for (var index = 0; index < tnCitySupported.length; index++) {
			$('#userCity').append("<option value='"+tnCitySupported[index]+"'>"+tnCitySupported[index]+"</option>");
		}
	}
}


function populateUserEmail(userID, emailField) {
	$.getJSON("http://localhost:8080/plugin-cloud-server/rest/user/getUser/"+userID, function(data) {
			$('#'+emailField).val(data[0].userEmail);
			$('#userLicense').val(data[0].userLicense);
			console.log(emailField+": "+ data[0].userEmail);
			console.log($('#'+emailField));
		}
	);
}