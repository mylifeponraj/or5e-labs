function formatNumber(num) {
	var n1, n2;
	num = num + '' || '';
	// works for integer and floating as well
	n1 = num.split('.');
	n2 = n1[1] || null;
	n1 = n1[0].replace(/(\d)(?=(\d\d)+\d$)/g, "$1,");
	num = n2 ? n1 + '.' + n2 : n1;
	return num;
}
function loadAddExpenceModel() {
	document
			.write("<div class='modal fade' id='addExpModal' data-keyboard='false' data-backdrop='static'><div class='modal-dialog modal-dialog-centered'> <div class='modal-content'> <div class='modal-header'> <h4 class='modal-title'>Add Expence</h4> <button type='button' class='close' data-dismiss='modal'>&times;</button> </div> <div class='modal-body'> <div class='form-group'> <label for='expType'>Expence Type :</label> <select class='form-control' id='expType' name='expType'></select> </div> <div class='input-group mb-3'> <div class='input-group-prepend'> <span class='input-group-text'>Expence Amount</span> </div> <input type='text' class='form-control' placeholder='Expence Amount' name='expAmt' id='expAmt' /> </div> </div> <div class='modal-footer'> <button type='button' class='btn btn-primary' id='addExpBtn' name='addExpBtn'>Add Expence</button> <button type='button' id='clearAddExpenceBtn' class='btn btn-secondary' data-dismiss='modal'>Close</button> </div> </div> </div> </div>")
}
function loadFDModel() {
	document
			.write("<div class='modal fade' id='addFDModal' data-keyboard='false' data-backdrop='static'> <div class='modal-dialog'> <div class='modal-content'> <div class='modal-header'> <h4 class='modal-title'>Add Fixed Deposit</h4> <button type='button' class='close' data-dismiss='modal'>&times;</button> </div> <div class='modal-body'> <div class='input-group mb-3'> <div class='input-group-prepend'> <span class='input-group-text'>FD number</span> </div> <input type='text' id='fdNum' name='fdNum' class='form-control' placeholder='FD number'> </div> <div class='input-group mb-3'> <div class='input-group-prepend'> <span class='input-group-text'>FD Name</span> </div> <input type='text' id='fdName' name='fdName' class='form-control' placeholder='FD Name'> </div> <div class='input-group mb-3'> <div class='input-group-prepend'> <span class='input-group-text'>FD Date</span> </div> <input id='fdDepDate' name='fdDepDate' class='form-control' width='276' /> </div> <div class='input-group mb-3'> <div class='input-group-prepend''> <span class='input-group-text'>FD Amount</span> </div> <input type='text' id='fdDepAmt' name='fdDepAmt' class='form-control' placeholder='FD Amount'> </div> <div class='input-group mb-3'> <div class='input-group-prepend''> <span class='input-group-text'>Rate of Interest</span> </div> <input type='text' id='rateOfInterest' name='rateOfInterest' class='form-control' placeholder='Rate of Interest'> </div> <div class='input-group mb-3'> <div class='input-group-prepend''> <span class='input-group-text'>FD Maturity Date</span> </div> <input id='fdMatDate' name='fdMatDate' class='form-control' width='276' /> </div> <div class='input-group mb-3'> <div class='input-group-prepend''> <span class='input-group-text'>FD Maturity Amount</span> </div> <input type='text' id='fdMatAmt' name='fdMatAmt' class='form-control' placeholder='FD Maturity Amount'> </div> </div> <div class='modal-footer'> <button type='button' class='btn btn-primary' id='addFDBtn' name='addFDBtn'>Add Deposit</button> <button type='button' id='clearAddFDBtn' class='btn btn-secondary' data-dismiss='modal'>Close</button> </div> </div> </div> </div>");
}
function loadFinanceDashboard() {
	userid = $('#userID').val();
	$.getJSON('rest/dashboard/getExpenceDashboard/' + userid, function(
			dashboard) {
		$('#totalFDFrm').html(dashboard.totalFDCount);
		$('#totalFDDeposit').html(formatNumber(dashboard.totalFDDeposit));
		$('#totalFDMaturity').html(formatNumber(dashboard.totalFDMaturity));
		$('#totalExpenceAmt').html(dashboard.totalExpence);
		$.each(dashboard.fdDetails, function(index, detail) {
			$('#FDTable tbody').append(
					"<tr><td>" + detail.fdNumber + "</td><td>"
							+ detail.fdDepositDate + "</td><td>"
							+ formatNumber(detail.fdAmount) + "</td><td>"
							+ detail.fdMaturityDate + "</td><td>"
							+ formatNumber(detail.fdMaturityAmount) + "</td></tr>");
		});
	});
}
function addFDDetails() {
	$('#addFDBtn').click(function() {
		alert("Doc you want to add the FD.");
		fdNum = $('#fdNum').val();
		fdName = $('#fdName').val();
		fdDepDate = $('#fdDepDate').val();
		fdDepAmt = $('#fdDepAmt').val();
		fdMatDate = $('#fdMatDate').val();
		rateOfInterest = $('#rateOfInterest').val();
		fdMatAmt = $('#fdMatAmt').val();
		reqToAdd = {
			"fdNumber" : fdNum,
			"fdName" : fdName,
			"fdDepDate" : fdDepDate,
			"fdDepAmt" : fdDepAmt,
			"fdMatDate" : fdMatDate,
			"fdMatAmt" : fdMatAmt,
			"rateOfInterest" : rateOfInterest,
			"userID" : $('#userID').val()
		};
		$.ajax({
			url : 'rest/expences/addFD',
			type : 'post',
			data : JSON.stringify(reqToAdd),
			contentType : 'application/json',
			dataType : "json",
			success : function(data) {
				if (data.successMessage == 1) {
					alert('Fixed deposit Added successfully');
				} else {
					alert('Fixed deposit addition Unsuccessfully');
				}
				resetAddFDModelForm();
			}
		});
	});
	$('#clearAddFDBtn').click(function() {
		resetAddFDModelForm();
	});
}
function resetAddFDModelForm() {
	$('#fdNum').val('');
	$('#fdName').val('');
	$('#fdDepDate').val('');
	$('#fdDepAmt').val('');
	$('#fdMatDate').val('');
	$('#fdMatAmt').val('');
}
function addExpenceType() {
	$.getJSON('rest/expences/getAllExpenceType', function(data) {
		$.each(data, function(index, obj) {
			$('#expType').append('<option value="' + obj.expenceID + '">' + obj.expenceName + '</opton>');
		});
	});
	$('#addExpBtn').click(function() {
		expType = $('#expType').val();
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
				resetAddExpenceModelForm();
			}
		});
	});
	$('#clearAddExpenceBtn').click(function() {
		resetAddExpenceModelForm();
	});
}
function resetAddExpenceModelForm() {
	$('#expType').val(100);
	$('#expAmt').val('');
}