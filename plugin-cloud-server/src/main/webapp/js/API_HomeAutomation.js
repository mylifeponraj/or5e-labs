/**
 * http://usejsdoc.org/
 */

function addUserPanelSubmit() {
	alert('Add User Clicked!!!');
}
function addUserPanelCancel() {
	alert('Add User Canceled!!!');
}
function addMUPanelSubmit() {
	alert('Add Master Unit Clicked!!!');
}
function addMUPanelCancel() {
	alert('Add Master Unit Canceled!!!');
}
function addControllerPanelSubmit() {
	alert('Controller Save Clicked!!!');
}
function addControllerPanelCancel() {
	alert('Controller Canceled!!!');
}
function addSensorPanelSubmit() {
	alert('Sensor Save Clicked!!!');
}
function addSensorPanelCancel() {
	alert('Sensor Canceled!!!');
}

function hideAllHomeAutomation() {
	$('#addUserFrm').hide();
	$('#addMasterUnitFrm').hide();
	$('#addLicenseFrm').hide();
	$('#addControllerFrm').hide();
	$('#addSensorFrm').hide();
	$('#mappingFrm').hide();

	$('#haUser').removeClass('active');
	$('#haMapping').removeClass('active');
	$('#haMaster').removeClass('active');
	$('#haSensor').removeClass('active');
	$('#haController').removeClass('active');
	$('#haLicense').removeClass('active');
}
function initilizeHomeAutomation() {
	$('#hmAutoPanel').hide();
	hideAllHomeAutomation();
	$('#hmAutoPanel').show();
	$('#haUser').click(function() {
		hideAllHomeAutomation();
		$('#haUser').addClass('active');
		$('#addUserFrm').slideDown();
	});
	$('#haLicense').click(function() {
		hideAllHomeAutomation();
		$('#haLicense').addClass('active');
		$('#addLicenseFrm').slideDown();
	});
	$('#haMaster').click(function() {
		hideAllHomeAutomation();
		$('#haMaster').addClass('active');
		$('#addMasterUnitFrm').slideDown();

	});
	$('#haController').click(function() {
		hideAllHomeAutomation();
		$('#haController').addClass('active');
		$('#addControllerFrm').slideDown();
	});
	$('#haSensor').click(function() {
		hideAllHomeAutomation();
		$('#haSensor').addClass('active');
		$('#addSensorFrm').slideDown();
	});
	$('#haMapping').click(function() {
		hideAllHomeAutomation();
		$('#haMapping').addClass('active');
		$('#mappingFrm').slideDown();
	});

	$('#showHm').click(function() {
		$('#notificationPane').html('Showing Home...');
		$('#notificationPane').slideDown(500, function() {
			setTimeout(function() {
				$('#notificationPane').slideUp(500);
			}, 2000);
		});
	});

	$('#showAdm').click(function() {
		hideAllHomeAutomation();
		$('#hmAutoPanel').show();
		$('#settingsPanel').hide();
		$('#financePanel').hide();
	});
}