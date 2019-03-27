/**
 * http://usejsdoc.org/
 */
function addMUPanelSubmit() {
	//alert('Add Master Unit Clicked!!!');
	$.getJSON("http://localhost:8080/plugin-cloud-server/rest/user/getUserByName/"+$('#mcuUserName').val(), function(data) {
		$('#mcuUID').val(data.userID);
		raiseAddMCURequest();
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

function raiseAddMCURequest() {
	masterUnitName = $('#masterUnitName').val();
	masterUnitMacID = $('#masterUnitMacID').val();
	masterUnitSoftwareVersion = $('#masterUnitSoftwareVer').val();
	masterUnitLicense = $('#masterUnitLicense').val();
	masterUnitStatus = 'n';
	reqToAdd = {
		"masterUnitName" : masterUnitName,
		"masterUnitMacID" : masterUnitMacID,
		"masterUnitSoftwareVersion" : masterUnitSoftwareVersion,
		"masterUnitLicense" : masterUnitLicense,
		"masterUnitStatus" : masterUnitStatus,
		"masterUnitId" : $('#mcuUID').val()
	};
	$.ajax({
		url : 'rest/mcu/addMCU',
		type : 'post',
		data : JSON.stringify(reqToAdd),
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.statusCode == 200) {
				alert('Master Unit Added successfully');
			} else {
				alert('Master Unit addition Unsuccessfully');
			}
			resetMCUForm();
		}
	});
}
