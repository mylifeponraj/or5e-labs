function addControllerPanelSubmit() {
	alert('Controller Save Clicked!!!');
	raiseAddSURequest();
}
function addControllerPanelCancel() {
	alert('Controller Canceled!!!');
	resetSlaveUnitForm();
}
function populateControllerType(fieldID) {
	$.getJSON("rest/su/getSlaveUnitTypes", function(data) {
		$('#mcuUID').val(data.userID);
		raiseAddSURequest();
	});
	$.getJSON('rest/su/getSlaveUnitTypes', function(data) {
		$.each(data, function(key, val) {
			$('#'+fieldID).append("<a class='dropdown-item' href='#'>"+val+"</a>");
		});
	});
}
function resetSlaveUnitForm() {
	$('#controllerName').val('');
	$('#controllerType').val('');
	$('#controllerSwitchCnt').val('');
	$('#controllerMasterUnit').val('');
	$('#controllerPort').val('');
}

function raiseAddSURequest() {
	slaveUnitName = $('#controllerName').val();
	slaveUnitType = $('#controllerType').val();
	slaveUnitPort = $('#controllerPort').val();
	slaveSwitchCnt = $('#controllerSwitchCnt').val();
	masterUnitName = $('#controllerMasterUnit').val();
	reqToAdd = {
		"slaveUnitName" : slaveUnitName,
		"slaveUnitType" : slaveUnitType,
		"slaveUnitPort" : slaveUnitPort,
		"slaveSwitchCnt" : slaveSwitchCnt,
		"masterUnitName" : masterUnitName,
	};
	$.ajax({
		url : 'rest/su/addSU',
		type : 'post',
		data : JSON.stringify(reqToAdd),
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.statusCode == 200) {
				alert('Slave Unit Added successfully');
			} else {
				alert('Slave Unit addition Unsuccessfully');
			}
			resetSlaveUnitForm();
		}
	});
}
