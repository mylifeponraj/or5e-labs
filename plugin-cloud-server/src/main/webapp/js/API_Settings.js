/**
 * http://usejsdoc.org/
 */
function initilizeSettings() {
	$('#settingsPanel').hide();
	hideAllSettingsPane();
	$('#showSet').click(function() {
		hideAllSettingsPane();
		hideAllPanels();
		$('#settingsPanel').show();
	});
	$('#setGen').click(function() {
		hideAllSettingsPane();
		$('#setGen').addClass('active');
		$('#settingsGEN').slideDown();
	});
	$('#setDB').click(function() {
		hideAllSettingsPane();
		$('#setDB').addClass('active');
		$('#settingsDB').slideDown();
	});
	$('#setIOT').click(function() {
		hideAllSettingsPane();
		$('#setIOT').addClass('active');
		$('#settingsIOT').slideDown();
	});

}
function hideAllSettingsPane() {
	$('#settingsDB').hide();
	$('#settingsGEN').hide();
	$('#settingsIOT').hide();
	
	$('#setDB').removeClass('active');
	$('#setGen').removeClass('active');
	$('#setIOT').removeClass('active');
}

function dbPanelSubmit() {
	alert('DB Save Clicked!!!');
}
function dbPanelCancel() {
	alert('DB Cancel Clicked!!!');
}
function genPanelSubmit() {
	alert('GEN Save Clicked!!!');
}
function genPanelCancel() {
	alert('GEN Cancel Clicked!!!');
}
function iotPanelSubmit() {
	alert('IOT Save Clicked!!!');
}
function iotPanelCancel() {
	alert('IOT Cancel Clicked!!!');
}

