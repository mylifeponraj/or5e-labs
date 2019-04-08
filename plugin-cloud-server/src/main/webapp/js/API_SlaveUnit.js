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
function loadSwichDefaultName(switchID) {
	$('#'+switchID+'-id').val($('#'+switchID+' option:selected').text());
}
function resetSlaveUnitForm() {
	$('#controllerName').val('');
	$('#controllerDisplayName').val('');
	$('#controllerType').val('');
	$('#controllerSwitchCnt').val('');
	$('#controllerSensorCnt').val('');
	$('#controllerMasterUnit').val('');
	$('#controllerPort').val('');
}
function addAllSwitchAndSensor() {
	alert('Adding Sensors...');
	slaveSwitchCnt = $('#controllerSwitchCnt').val();
	var sensorDetails = []
	for(index = 1 ; index <= slaveSwitchCnt ; index ++) {
		sensorName = $('#sw0'+index+'-id').val();
		sensorDevice = $('#sw0'+index).val();
		record = {'sensorName': sensorName, 'sensorDevice': sensorDevice, 'sensorSwitchNo': index};
		sensorDetails.push(record);
	}
	$.ajax({
		url : 'rest/sensor/addSensorUnits/'+$('#controllerName').val(),
		type : 'post',
		data : JSON.stringify(sensorDetails),
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.statusCode == 200) {
				alert('Sensor Added successfully');
			} else {
				alert('SensorAdditionFailed addition Unsuccessfully');
			}
		}
	});

	
}

function raiseAddSURequest() {
	slaveUnitName = $('#controllerName').val();
	slaveUnitType = $('#controllerType').val();
	slaveUnitPort = $('#controllerPort').val();
	slaveSwitchCnt = $('#controllerSwitchCnt').val();
	slaveSensorCnt = $('#controllerSensorCnt').val();
	masterUnitName = $('#controllerMasterUnit').val();
	slaveUnitDisplayName = $('#controllerDisplayName').val();
	reqToAdd = {
		"slaveUnitName" : slaveUnitName,
		"slaveUnitType" : slaveUnitType,
		"slaveUnitPort" : slaveUnitPort,
		"slaveSwitchCnt" : slaveSwitchCnt,
		"masterUnitName" : masterUnitName,
		"slaveUnitDisplayName": slaveUnitDisplayName
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
				addAllSwitchAndSensor();
			} else {
				alert('Slave Unit addition Unsuccessfully');
			}
			resetSlaveUnitForm();
		}
	});
}
function writeSlaveSwitchField(switchTitle, switchID) {
	document.write("<div class='col-sm-3 text-left' style='border: 1px solid orange'>"+switchTitle+"<br/><input type='text' id='"+switchID+"-id' name='"+switchID+"-id' value=''/><br/><select id='"+switchID+"'name='"+switchID+"' onchange=\"javascript:loadSwichDefaultName('"+switchID+"');\"></select><br/></div>");
}
function loadSwitchConfiguration() {
	$.getJSON('rest/device/getSwitchingDevices', function(data) {
		$('#sw01').append("<option value='100'>No Switch</option>");
		$('#sw02').append("<option value='100'>No Switch</option>");
		$('#sw03').append("<option value='100'>No Switch</option>");
		$('#sw04').append("<option value='100'>No Switch</option>");
		$('#sw05').append("<option value='100'>No Switch</option>");
		$('#sw06').append("<option value='100'>No Switch</option>");
		$('#sw07').append("<option value='100'>No Switch</option>");
		$('#sw08').append("<option value='100'>No Switch</option>");
		$.each(data, function(index, obj) {
			$('#sw01').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw02').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw03').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw04').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw05').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw06').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw07').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sw08').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
		});
	});
}
function loadSensorConfiguration() {
	$.getJSON('rest/device/getSensorDevices', function(data) {
		$('#sen01').append("<option value='100'>No Sensor</option>");
		$('#sen02').append("<option value='100'>No Sensor</option>");
		$('#sen03').append("<option value='100'>No Sensor</option>");
		$('#sen04').append("<option value='100'>No Sensor</option>");
		$.each(data, function(index, obj) {
			$('#sen01').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sen02').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sen03').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
			$('#sen04').append("<option value='"+obj.deviceID+"'>"+obj.deviceName+"</option>");
		});
	});
}
