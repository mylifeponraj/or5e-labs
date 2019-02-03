
<%
	if (session.getAttribute("userDisplayName") == null) {
		response.sendRedirect("login.do");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAMITN Home</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style type="text/css">
body {
	background-color: #222;
	color: #aaa;
}

input[type="text"] {
  border: 0;
  outline: 0;
  width: 80%;
  display: block;
  background: transparent;
  color: #aaa;
  border-bottom: 2px solid blue;
}
input[type="text"]:focus {
  border-color: green
}

select {
  border: 0;
  outline: 0;
  width: 80%;
  display: block;
  background: transparent;
  color: #aaa;
  border-bottom: 2px solid blue;
}
select:focus {
  border-color: green
}

/* The side navigation menu */
.sidebar {
	margin: 0;
	padding-left: 15px; width : 70px;
	background-color: #000;
	color: #aaa;
	position: fixed;
	font-size: 40px; height : 100%;
	overflow: auto;
	height: 100%;
	width: 70px;
}

/* Sidebar links */
.sidebar a {
	display: block;
	color: #ddd;
	padding: 16px;
	text-decoration: none;
}

/* Active/current link */
.sidebar a.active {
	background-color: #4CAF50;
	color: white;
}

/* Links on mouse-over */
.sidebar a:hover:not (.active ){
	background-color: #555;
	color: white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div.content1 {
	margin-left: 100px;
	width: 90%;
	/*	padding: 1px 16px;*/
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
	.sidebar {
		width: 100%;
		height: auto;
		position: relative;
	}
	.sidebar a {
		float: left;
	}
	div.content1 {
		margin-left: 0;
	}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
	.sidebar a {
		text-align: center;
		float: none;
	}
}
</style>

<script type="text/javascript">
	
	$(document).ready(function() {
		$('#settingsPanel').hide();
		$('#financePanel').hide();
		$('#hmAutoPanel').hide();
		$('#notificationPane').hide();

		$('#showAdm').click(function() {
			hideAllHomeAutomation();
			$('#hmAutoPanel').show();
			$('#settingsPanel').hide();
			$('#financePanel').hide();
		});
		$('#showSet').click(function() {
			hideAllSettingsPane();
			$('#settingsPanel').show();
			$('#financePanel').hide();
			$('#hmAutoPanel').hide();
		});
		$('#showFin').click(function() {
			hideAllExpencePane();
			$('#financePanel').show();
			$('#settingsPanel').hide();
			$('#hmAutoPanel').hide();
		});
		$('#showHm').click(function() {
			$('#notificationPane').html('Showing Home...');
			$('#notificationPane').slideDown(500, function() {
				setTimeout(function(){ 
					$('#notificationPane').slideUp(500);
				}, 
				2000);
			});
		});

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
		
		
		hideAllExpencePane();
		$('#fnExp').click(function() {
			hideAllExpencePane();
			$('#fnExp').addClass('active');
			$('#financeExpence').slideDown();
			
			$.getJSON('rest/expences/getAllExpenceType', function(data) {
				$('#expenceType').empty();
				$('#expenceType').append('<option value="NA">Select Expence Type</opton>')
				$.each(data, function(index, obj) {
					$('#expenceType').append('<option value="' + obj.expenceID + '">' + obj.expenceName + '</opton>');
				});
			});
		});
		$('#fnFD').click(function() {
			hideAllExpencePane();
			$('#fnFD').addClass('active');
			$('#financeFD').slideDown();
		});
		$('#fnHome').click(function() {
			hideAllExpencePane();
			$('#fnHome').addClass('active');
			$('#financeHome').slideDown();
		});

		hideAllSettingsPane();
		$('#setIOT').click(function() {
			hideAllSettingsPane();
			$('#setIOT').addClass('active');
			$('#settingsIOT').slideDown();
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
	});
	function hideAllSettingsPane() {
		$('#settingsDB').hide();
		$('#settingsGEN').hide();
		$('#settingsIOT').hide();
		
		$('#setDB').removeClass('active');
		$('#setGen').removeClass('active');
		$('#setIOT').removeClass('active');
	}
	function hideAllExpencePane() {
		$('#financeHome').hide();
		$('#financeExpence').hide();
		$('#financeFD').hide();
		
		
		$('#fnHome').removeClass('active');
		$('#fnFD').removeClass('active');
		$('#fnExp').removeClass('active');
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
	
//	<br/>
//	<div class='row'>
//		<div class='col-sm-2 text-right'>DB URL: </div>
//		<div class='col-sm-8'><input type='text' value='data'/></div>
//	</div>
	
	function writeTextField(fieldID, fieldName, fieldValue, isDisable) {
		if(isDisable == undefined || isDisable == false){
			document.write("<br/><div class='row'><div class='col-sm-2 text-right'>"+fieldName+"&nbsp;</div><div class='col-sm-8'><input id="+fieldID+" type='text' value='"+fieldValue.trim()+"'/></div></div>");
		}
		else {
			document.write("<br/><div class='row'><div class='col-sm-2 text-right'>"+fieldName+"&nbsp;</div><div class='col-sm-8'><input id="+fieldID+" type='text' value='"+fieldValue.trim()+"' disabled/></div></div>");
		}
	}

//	<br/><div class='row'><div class='col-sm-2 text-right'>Expence Type: </div>
//	<div class='col-sm-8'>
//	<select id="sel1" name="sellist1">
//        <option>Select Expence Type</option>
// 	</select>
//</div></div>
	function writeSelectField(filedID, fieldName, fieldDefaultOption, loadValueFn) {
		document.write("<br/><div class='row'><div class='col-sm-2 text-right'>"+fieldName+"&nbsp;</div><div class='col-sm-8'><select id='"+filedID+"' name='"+filedID+"'><option value="+fieldDefaultOption+">"+fieldDefaultOption+"</option></select></div></div>");
		if(loadValueFn != null) {
			loadValueFn(filedID);
		}
	}


//	<br/><div class="row"><div class="col-sm-12">
//		<button id='savePanelBtn' onclick='javascript:func();' type="button" class="btn btn-success">Save</button>&nbsp;&nbsp;
//		<button type="button" id='cancelPanelBtn' onclick='javascript:func();' class="btn btn-danger">Cancel</button>
//	</div></div>
	function writePanelSubmit(saveBtnID, cancelBtnID, saveFn, cancelFn) {
		document.write("<br/><div class='row'><div class='col-sm-12'><button id='"+saveBtnID+"' onclick='javascript:"+saveFn+";' type='button' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button type='button' id='"+cancelBtnID+"' onclick='javascript:"+cancelFn+";' class='btn btn-danger'>Cancel</button></div></div>");
	}
	function writePanelSubmitAsSaveMessage(saveBtnID, cancelBtnID, saveFn, cancelFn, saveBtnMessage) {
		document.write("<br/><div class='row'><div class='col-sm-12'><button id='"+saveBtnID+"' onclick='javascript:"+saveFn+";' type='button' class='btn btn-success'>"+saveBtnMessage+"</button>&nbsp;&nbsp;<button type='button' id='"+cancelBtnID+"' onclick='javascript:"+cancelFn+";' class='btn btn-danger'>Cancel</button></div></div>");
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
	function writeNotificationPanel() {
		document.write("<div id='notificationPane' class='text-center' style='position: fixed; top: 0px; left: 0px; width:100%; height: 25px; z-index: 1; background-color: orange; color: black; font-weight: bold'></div>");
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
	function addExpPanelSubmit() {
		expType = $('#expenceType').val();
		expAmt = $('#expAmt').val();
		reqToAdd = {
			"expenceType" : expType,
			"expenceAmount" : expAmt,
			"userID" : $('#userID').val()
		};
		$.ajax({
			url : 'rest/expences/addExpence',
			type : 'post',
			data : JSON.stringify(reqToAdd),
			contentType : 'application/json',
			dataType : "json",
			success : function(data) {
				if (data.successMessage == 1) {
					alert('Expence Added successfully');
				} else {
					alert('Expence addition Unsuccessfully');
				}
				resetAllExpenceModelForm()
			}
		});
	}
	function addExpPanelCancel() {
		resetAllExpenceModelForm();
	}
	function resetAllExpenceModelForm() {
		$('#expenceType').val('NA');
		$('#expAmt').val('');
	}
	function addFDPanelSubmit() {
		alert('Add FD Clicked!!!');
	}
	function addFDPanelCancel() {
		alert('Add FD Canceled!!!');
	}
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
	function addLicPanelSubmit() {
		alert('Generate License Clicked!!!');
	}
	function addLicPanelCancel() {
		alert('Generate License Canceled!!!');
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

</script>
</head>
<body>
	<!-- The sidebar -->
	<div class="sidebar">
		<i class="fas fa-user"></i>
		<i class="fas fa-home" id='showHm'></i> 
		<i class="fas fa-microchip" id='showAdm'></i> 
		<i class="fas fa-video" id='showFFMPEG'></i>
		<i class="fab fa-bitcoin" id='showFin'></i> 
		<i class="fas fa-cog" id='showSet'></i>
	</div>
	
	<script type="text/javascript">writeNotificationPanel();</script>
	
	<div class="container-fluid content1">
		<div class='row'>
			<div class='col-sm-4' style="color: white; font-size: 20px;">IAMI<font style='color: orange'><b>TN</b></font> Admin Console</div>
			<div class='col-sm-8 text-right' style="color: white; font-size: 20px;"><b>Welcome:</b> Ponraj Suthanthiran Mani</div>
		</div>
		<div id="hmAutoPanel">
			<br/><h2>Home Automation</h2><br />
			<ul class="nav nav-tabs">
				<li class="nav-item"><a id='haUser' class="nav-link" href="#">Add User</a></li>
				<li class="nav-item"><a id='haLicense' class="nav-link" href="#">Create License</a></li>
				<li class="nav-item"><a id='haMaster' class="nav-link" href="#">Add Master Unit</a></li>
				<li class="nav-item"><a id='haController' class="nav-link" href="#">Add Controller Unit</a></li>
				<li class="nav-item"><a id='haSensor' class="nav-link" href="#">Add Sensor</a></li>
				<li class="nav-item"><a id='haMapping' class="nav-link" href="#">Map Resources</a></li>
			</ul>

			<div id='addUserFrm'>
			<script type="text/javascript">
				writeTextField('userName', 'Usernanme:', ' ');
				writeTextField('userDispalyName', 'User Display Name:', ' ');
				writeTextField('userEmail', 'User Email:', ' ');
				writeTextField('userPhone', 'User Phone/Mobile:', ' ');
				writeTextField('userAdd1', 'Address 1:', ' ');
				writeTextField('userAdd2', 'Addres 2:', ' ');
				writeTextField('userContry', 'Country:', 'India', true);
				writeSelectField('userState', 'State:', 'Select State', loadState);
				writeSelectField('userCity', 'City:', 'Select City', null);
				writeTextField('userPinCode', 'Pincode:', ' ');
				writePanelSubmit('addUserSaveBtn', 'addUserCancelBtn', 'addUserPanelSubmit()', 'addUserPanelCancel()');
			</script>
			</div>
			
			
			<div id='addMasterUnitFrm'>
			<script type="text/javascript">
				writeTextField('masterUnitName', 'Master Unit Name(*Unique):', ' ');
				writeTextField('masterUnitMacID', 'Master Unit MAC ID:', ' ');
				writeTextField('masterUnitSoftwareVer', 'Master Unit Software Version:', ' ');
				writeTextField('masterUnitLicense', 'Master Unit License:', ' ');
				writeTextField('User ID', 'Sold To:', ' ');
				writePanelSubmit('addMUSaveBtn', 'addMUCancelBtn', 'addMUPanelSubmit()', 'addMUPanelCancel()');
			</script>
			</div>


			<div id='addLicenseFrm'>
			<script type="text/javascript">
				writeTextField('User ID', 'Sold To:', ' ');
				writeTextField('userEmail', 'Customer Email:', ' ');
				writeTextField('userLicense', 'Master Unit License(AUTO GENERATED):', ' ', true);
				writePanelSubmitAsSaveMessage('genLicenseSaveBtn', 'agenLicenseCancelBtn', 'addLicPanelSubmit()', 'addLicPanelCancel()', 'Generate License');
			</script>
			</div>

			<div id='addControllerFrm'>
			<script type="text/javascript">
				writeTextField('controllerName', 'Controller Name:', ' ');
				writeTextField('controllerType', 'Controller Type:', ' ');
				writeTextField('controllerMacID', 'Controller MAC ID:', ' ');
				writeTextField('controllerMasterUnit', 'Master Unit ID:', ' ');
				writePanelSubmit('addControllerSaveBtn', 'addControllerCancelBtn', 'addControllerPanelSubmit()', 'addControllerPanelCancel()');
			</script>
			</div>
			<div id='addSensorFrm'>
			<script type="text/javascript">
				writeTextField('sensorName(*Unique)', 'Sensor Name:', ' ');
				writeTextField('sensorType', 'Sensor Type:', ' ');
				writeTextField('controllerID', 'Controller ID:', ' ');
				writePanelSubmit('addSensorSaveBtn', 'addSensorCancelBtn', 'addSensorPanelSubmit()', 'addSensorPanelCancel()');
			</script>
			</div>
			<div id='mappingFrm'>
			<div class='row'>Mapping is underway</div>
			</div>

		</div>
		<!-- This is the Setting Panel STARTS:001-->
		<div id="settingsPanel">
			<br/><h2>Settings</h2><br />

			<ul class="nav nav-tabs">
				<li class="nav-item"><a id='setGen' class="nav-link" href="#">General</a></li>
				<li class="nav-item"><a id='setDB' class="nav-link" href="#">Database</a></li>
				<li class="nav-item"><a id='setIOT' class="nav-link" href="#">IOT</a></li>
			</ul>
			<div id='settingsGEN'>
				<div class='row'>General Settings comes here.</div>
			</div>
			<div id='settingsDB'>
				<script type="text/javascript">
				writeTextField('dbURL', 'DB URL:', 'Data Of DB URL');
				writeTextField('dbUSR', 'DB User:', 'Data Of DB User');
				writeTextField('dbPWD', 'DB URL:', 'Data Of DB PWD');
				writeTextField('dbSCM', 'DB Schema:', 'Data Of DB Schema');
				writePanelSubmit('dbSettingsSaveBtn', 'dbCancelSaveBtn', 'dbPanelSubmit()', 'dbPanelCancel()');
				</script>
			</div>
			
			<div id='settingsIOT'>
				<script type="text/javascript">
				writeTextField('dbURL', 'DB URL:', 'Data Of DB URL');
				writeTextField('dbUSR', 'DB User:', 'Data Of DB User');
				writePanelSubmit('iotSettingsSaveBtn', 'iotCancelSaveBtn', 'iotPanelSubmit()', 'iotPanelCancel()');
				</script>
			</div>
		</div>
		<!-- ENDS: 001 -->



		<!-- This is the Finance Panel START:002-->
		<div id="financePanel">
			<br/><h2>Finance</h2><br />

			<ul class="nav nav-tabs">
				<li class="nav-item"><a id='fnHome' class="nav-link" href="#">Home</a></li>
				<li class="nav-item"><a id='fnExp' class="nav-link" href="#">Add Expences</a></li>
				<li class="nav-item"><a id='fnFD' class="nav-link" href="#">Add FD</a></li>
			</ul>
			<div id='financeHome'>
			<br/>
				<div class='row'>
				Dashboard Comes here...
				</div>
			</div>

			<div id='financeExpence'>
				<script type="text/javascript">
				writeSelectField('expenceType', 'Expence Type:', 'Select Expence Type', null);
				writeTextField('expAmt', 'Expense Amount:', '');
				writePanelSubmit('addExpSaveBtn', 'addExpCancelBtn', 'addExpPanelSubmit()', 'addExpPanelCancel()');
				</script>
			</div>

			<div id='financeFD'>
			<script type="text/javascript">
				writeTextField('fdAccoutNum', 'Fixed Deposit Account Number:', ' ');
				writeTextField('fdAccoutName', 'Fixed Deposit Account Name:', ' ');
				writeTextField('fdAccoutDate', 'Fixed Deposit Date:', ' ');
				writeTextField('fdDepositAmt', 'Fixed Deposit Amount:', ' ');
				writeTextField('fdMaturityDate', 'Deposit Maturity Date:', ' ');
				writeTextField('fdMaturityAmt', 'Deposit Maturity Amount:', ' ');
				writeTextField('fdMaturityInterest', 'Interest Rate:', ' ');
				writePanelSubmit('addFDSaveBtn', 'addFDCancelBtn', 'addFDPanelSubmit()', 'addFDPanelCancel()');
			</script>
			</div>

		</div>
		<!-- This is the Finance Panel ENDS:002-->

	<input type='hidden' name='userID' id='userID'
		value='<%=(Integer) session.getAttribute("userID")%>' />

	</div>
</body>
</html>