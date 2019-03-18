/**
 * http://usejsdoc.org/
 */
function hideAllExpencePane() {
	$('#financeHome').hide();
	$('#financeExpence').hide();
	$('#financeFD').hide();
	
	
	$('#fnHome').removeClass('active');
	$('#fnFD').removeClass('active');
	$('#fnExp').removeClass('active');
}

function initilizeExpences() {
	$('#financePanel').hide();
	$('#showFin').click(function() {
		hideAllExpencePane();
		hideAllPanels();
		$('#financePanel').show();
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

