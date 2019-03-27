<%
	if (session.getAttribute("userDisplayName") == null) {
		response.sendRedirect("logout.do");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAMITN Home</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/API_Panel.js"></script>
<script src="js/API_Expence.js"></script>
<script src="js/API_Settings.js"></script>
<script src="js/API_User.js"></script>
<script src="js/API_HomeAutomation.js"></script>
<script src="js/API_mcu.js"></script>
<script src="js/API_Dashboard.js"></script>
<script src="js/API_HTMLElements.js"></script>
<script src="js/API_SlaveUnit.js"></script>
<script src="js/bootstrap3-typeahead.min.js"></script> 

<script type="text/javascript">
	$(document).ready(function() {
		$('#notificationPane').hide();
		initilizeHomeAutomation();
		initilizeExpences();
		initilizeSettings();
		initilizeDashboard();
	});
	function writeSideBar() {
		document.write("<div class='sidebar'><i class='fas'><br/><br/></i><i class='fas fa-home' id='showHm'></i><i class='fas fa-microchip' id='showAdm'></i><i class='fas fa-video' id='showFFMPEG'></i><i class='fab fa-bitcoin' id='showFin'></i><i class='fas fa-cog' id='showSet'></i></div>");
	}
	function writeNotificationPanel() {
		document.write("<div id='notificationPane' class='text-center' style='position: fixed; top: 0px; left: 0px; width:100%; height: 25px; z-index: 1; background-color: orange; color: black; font-weight: bold'></div>");
	}

</script>
</head>
<body>
	<script type="text/javascript">writeSideBar();writeNotificationPanel();</script>
	
	<div class="container-fluid content1">
		<div class='row'>
			<div class='col-sm-4' style="color: white; font-size: 20px;">IAMI<font style='color: orange'><b>TN</b></font> Admin Console</div>
			<div class='col-sm-8 text-right' style="color: white; font-size: 20px;">Hello : <font color='orange'><%=(String) session.getAttribute("userDisplayName")%></font> | <a href="logout.do">Logout</a></div>
		</div>
		<div id='mcuDashboardPanel'>
			<br/><h2>Master Controller Dashboard</h2> | <a href='javascript: loadAllMasterUnitIntoDashboard();'>Load MCU</a> | <a id='liveUpdate' href='javascript: wsSendRegMessage()'>Enable Live</a><br />
			<div id='mcuDB'>

			</div>
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
				writeTextField('mcuUserID', 'Sold To:', ' ');
				$('#mcuUserID').typeahead({
				    source:  function (query, process) {
						return $.get("http://localhost:8080/plugin-cloud-server/rest/user/getUserByName/"+query, 
		        			{}, 
		        			function (data) {
			            		return process(data);
			        		}
		        		);
				    },
				    afterSelect: function (selectedItem) {
				    	console.log('Selected : '+selectedItem);
				    	$('#mcuUserName').val(selectedItem);
				    	alert($('#mcuUserName').val());
				    }
				});
				writePanelSubmit('addMUSaveBtn', 'addMUCancelBtn', 'addMUPanelSubmit()', 'addMUPanelCancel()');
			</script>
			<input type="hidden" id=mcuUID' name='mcuUID' value=''/>
			<input type="hidden" id='mcuUserName' name='mcuUserName' value=''/>
			</div>


			<div id='addLicenseFrm'>
			<script type="text/javascript">
				writeTextField('userLicenseName', 'Sold To:', ' ');
				$('#userLicenseName').typeahead({
				    source:  function (query, process) {
						return $.get("http://localhost:8080/plugin-cloud-server/rest/user/getUserByName/"+query, 
		        			{}, 
		        			function (data) {
			            		return process(data);
			        		}
		        		);
				    },
				    afterSelect: function (selectedItem) {
				    	console.log('Selected : '+selectedItem);
				    	populateUserEmail(selectedItem, 'licenseUserEmail');
				    }
				});
				writeTextField('licenseUserEmail', 'Customer Email:', ' ', true);
				writeTextField('userLicense', 'Master Unit License(AUTO GENERATED):', ' ', true);
				writePanelSubmitAsSaveMessage('genLicenseSaveBtn', 'agenLicenseCancelBtn', 'addLicPanelSubmit()', 'addLicPanelCancel()', 'Generate License');
			</script>
			</div>

			<div id='addControllerFrm'>
			<script type="text/javascript">
				writeTextField('controllerName', 'Controller Name:', ' ');
				writeTextField('controllerType', 'Controller Type:', ' ');
				$('#controllerType').typeahead({
				    source:  function (query, process) {
						return $.get("rest/su/getSlaveUnitTypes", 
		        			{}, 
		        			function (data) {
			            		return process(data);
			        		}
		        		);
				    },
				    afterSelect: function (selectedItem) {
				    	console.log('Selected : '+selectedItem);
				    	$('#controllerType').val(selectedItem);
				    }
				});
				writeTextField('controllerPort', 'Controller Port:', ' ');
				writeTextField('controllerSwitchCnt', 'Controller Switching:', ' ');
				writeTextField('controllerMasterUnit', 'Master Unit ID:', ' ');
				$('#controllerMasterUnit').typeahead({
				    source:  function (query, process) {
						return $.get("rest/mcu/getMCU/"+query, 
		        			{}, 
		        			function (data) {
			            		return process(data);
			        		}
		        		);
				    },
				    afterSelect: function (selectedItem) {
				    	console.log('Selected : '+selectedItem);
				    	$('#controllerMasterUnit').val(selectedItem);
				    }
				});
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