function initilizeDashboard() {
	$('#mcuDashboardPanel').hide();
	$('#showHm').click(function() {
		hideAllPanels();
		$('#mcuDashboardPanel').show();
	});
}
function loadAllMasterUnitIntoDashboard() {
	$.getJSON('rest/mcu/getAllMCU', function(data) {
		index = 1;
		addRecord = "";
		$.each(data, function(key, val) {
			addRecord += '<div id="'+val.masterUnitName+'" name="'+val.masterUnitName+'" class="col-md-2 mcurecord mcurecordRedColor">'
					+ val.masterUnitName + '<br>' + val.masterUnitMacID
					+ '<br>' + val.masterUnitSoftwareVersion + '<br>'
					+ val.masterUnitIPaddr + '</div>';
			index = index + 1;

		});
		appendEndDiv(addRecord);
		initilizeWebSocket();
	});
}
function appendEndDiv(addRecord) {
	$('#mcuDB').html('<div class="row">' + addRecord + "</div>");
}
var wsocket;
var isConnected;
var timerFn;
var browserClient="HAServerAPP";

function initilizeWebSocket() {
	isConnected = false;
	url = "ws://localhost:8080/plugin-cloud-server/has";
//	alert(url);
	webSocket = new WebSocket(url);
	webSocket.onopen = function(message){ wsOpen(message);};
	webSocket.onmessage = function(message){ wsGetMessage(message);};
	webSocket.onclose = function(message){ wsClose(message);};
	webSocket.onerror = function(message){ wsError(message);};
}
function wsOpen(message){
	isConnected = true;
}
function wsSendRegMessage(){
	//Message Type: Register, Status: send to Server;; "Command": Send from Server
	var sendTo = "{'messageFrom':'"+browserClient+"', 'messageTo':'HAServer', 'messageType':'REG', 'masterUnitLicense':'ADMIN', 'message':'ADMIN'}";
	webSocket.send(sendTo);
	updateStatus();
	$('#liveUpdate').html('Disable Live');
}
function updateStatus() {
	var sendTo = "{'messageFrom':'"+browserClient+"', 'messageTo':'HAServer', 'messageType':'MUU', 'masterUnitLicense':'ADMIN', 'message':'ADMIN'}";
	webSocket.send(sendTo);
}
function wsCloseConnection(){
	webSocket.close();
}
function wsGetMessage(message){
	var response = JSON.parse(message.data);
	console.log(response);
	if(response.messageType === 'ACTMCU') {
		$('#'+response.message).removeClass('mcurecordRedColor').addClass('mcurecordGreenColor');
	}
	else if(response.messageType === 'DISMCU') {
		$('#'+response.message).removeClass('mcurecordGreenColor').addClass('mcurecordRedColor');
	}
	else if(response.messageType === 'MUU') {
		var msg = new String(response.message);
		var activeMCU = msg.split(',');
		for(i = 1; i < activeMCU.length; i ++) {
			alert(activeMCU[i-1]);
			$('#'+activeMCU[i-1]).removeClass('mcurecordRedColor').addClass('mcurecordGreenColor');
		}
	}
	else {
		console.log('No Mapping: '+response.message);
	}
}
function wsClose(message){
	console.log("Disconnect");
	isConnected = false;
	timerFn = setInterval(reconect, 10000);
}
function reconect() {
	console.log('Trying to Connect...');
	initilizeWebSocket();
	if(isConnected == true) clearInterval(timerFn);
}

function wserror(message){
	console.log("Error");
	isConnected = false;
	timerFn = setInterval(reconect, 3000);

	
}