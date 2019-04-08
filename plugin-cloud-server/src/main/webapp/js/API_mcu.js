/**
 * http://usejsdoc.org/
 */
function addMUPanelSubmit() {
	//alert('Add Master Unit Clicked!!!');
	$.getJSON("http://localhost:8080/plugin-cloud-server/rest/user/getUserByName/"+$('#mcuUserName').val(), function(data) {
		$('#mcuUID').val(data.userID);
		raiseAddMCURequest(data.userID);
	});
}
function addMUPanelCancel() {
	alert('Add Master Unit Canceled!!!');
	resetMCUForm();
}
function resetMCUForm() {
	$('#masterUnitName').val('');
	$('#masterUnitMacID').val('');
	$('#masterUnitSoftwareVer').val('');
	$('#masterUnitLicense').val('');
	$('#mcuUserID').val('');
	$('#mcuUID').val('');
}

function raiseAddMCURequest(usrID) {
	masterUnitName = $('#masterUnitName').val();
	masterUnitMacID = $('#masterUnitMacID').val();
	masterUnitSoftwareVersion = $('#masterUnitSoftwareVer').val();
	masterUnitLicense = $('#masterUnitLicense').val();
	masterUnitStatus = 'n';
	userID = usrID;
	reqToAdd = {
		"masterUnitName" : masterUnitName,
		"masterUnitMacID" : masterUnitMacID,
		"masterUnitSoftwareVersion" : masterUnitSoftwareVersion,
		"masterUnitLicense" : masterUnitLicense,
		"masterUnitStatus" : masterUnitStatus,
		"userID" : userID
	};
	$.ajax({
		url : 'rest/mcu/addMCU',
		type : 'post',
		data : JSON.stringify(reqToAdd),
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.statusCode == 200) {
				alert('Master Unit Added successfully..');
			} else {
				alert('Master Unit addition Unsuccessfully');
			}
			resetMCUForm();
		}
	});
}
function populateUserLicense(userID) {
	$.getJSON("http://localhost:8080/plugin-cloud-server/rest/user/getUserByName/"+userID, function(data) {
			$('#masterUnitLicense').val(data.userLicense);
		}
	);
}
