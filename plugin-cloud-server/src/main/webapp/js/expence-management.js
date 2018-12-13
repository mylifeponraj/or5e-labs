function loadAddExpenceModel() {
	document
			.write("<div class='modal fade' id='addExpModal' data-keyboard='false' data-backdrop='static'><div class='modal-dialog modal-dialog-centered'> <div class='modal-content'> <div class='modal-header'> <h4 class='modal-title'>Add Expence</h4> <button type='button' class='close' data-dismiss='modal'>&times;</button> </div> <div class='modal-body'> <div class='form-group'> <label for='expType'>Expence Type :</label> <select class='form-control' id='expType' name='expType'></select> </div> <div class='input-group mb-3'> <div class='input-group-prepend'> <span class='input-group-text'>Expence Amount</span> </div> <input type='text' class='form-control' placeholder='Expence Amount' name='expAmt' id='expAmt' /> </div> </div> <div class='modal-footer'> <button type='button' class='btn btn-primary' id='addExpBtn' name='addExpBtn'>Add Expence</button> <button type='button' id='clearAddExpenceBtn' class='btn btn-secondary' data-dismiss='modal'>Close</button> </div> </div> </div> </div>")
}

function addFDDetails() {
	$('#addFDBtn').click(function() {
		alert("Doc you want to add the FD.");
		fdNum = $('#fdNum').val();
		fdName = $('#fdName').val();
		fdDepDate = $('#fdDepDate').val();
		fdDepAmt = $('#fdDepAmt').val();
		fdMatDate = $('#fdMatDate').val();
		fdMatAmt = $('#fdMatAmt').val();
		reqToAdd = {
			"fdNumber" : fdNum,
			"fdName" : fdName,
			"fdDepDate" : fdDepDate,
			"fdDepAmt" : fdDepAmt,
			"fdMatDate" : fdMatDate,
			"fdMatAmt" : fdMatAmt,
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
			$('#expType').append(
					'<option value="' + obj.expenceID + '">' + obj.expenceName
							+ '</opton>');
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