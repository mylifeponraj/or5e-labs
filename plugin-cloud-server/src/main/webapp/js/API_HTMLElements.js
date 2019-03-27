//	<br/>
//	<div class='row'>
//		<div class='col-sm-2 text-right'>DB URL: </div>
//		<div class='col-sm-8'><input type='text' value='data'/></div>
//	</div>

function writeTextField(fieldID, fieldName, fieldValue, isDisable) {
	if (isDisable == undefined || isDisable == false) {
		document
				.write("<br/><div class='row'><div class='col-sm-2 text-right'>"
						+ fieldName
						+ "&nbsp;</div><div class='col-sm-8'><input id="
						+ fieldID
						+ " type='text' value='"
						+ fieldValue.trim()
						+ "'/></div></div>");
	} else {
		document
				.write("<br/><div class='row'><div class='col-sm-2 text-right'>"
						+ fieldName
						+ "&nbsp;</div><div class='col-sm-8'><input id="
						+ fieldID
						+ " type='text' value='"
						+ fieldValue.trim()
						+ "' disabled/></div></div>");
	}
}

// <br/><div class='row'><div class='col-sm-2 text-right'>Expence Type: </div>
// <div class='col-sm-8'>
// <select id="sel1" name="sellist1">
// <option>Select Expence Type</option>
// </select>
// </div></div>
function writeSelectField(filedID, fieldName, fieldDefaultOption, loadValueFn) {
	document.write("<br/><div class='row'><div class='col-sm-2 text-right'>"
			+ fieldName + "&nbsp;</div><div class='col-sm-8'><select id='"
			+ filedID + "' name='" + filedID + "'><option value="
			+ fieldDefaultOption + ">" + fieldDefaultOption
			+ "</option></select></div></div>");
	if (loadValueFn != null) {
		loadValueFn(filedID);
	}
}

// <br/><div class="row"><div class="col-sm-12">
// <button id='savePanelBtn' onclick='javascript:func();' type="button"
// class="btn btn-success">Save</button>&nbsp;&nbsp;
// <button type="button" id='cancelPanelBtn' onclick='javascript:func();'
// class="btn btn-danger">Cancel</button>
// </div></div>
function writePanelSubmit(saveBtnID, cancelBtnID, saveFn, cancelFn) {
	document
			.write("<br/><div class='row'><div class='col-sm-12'><button id='"
					+ saveBtnID
					+ "' onclick='javascript:"
					+ saveFn
					+ ";' type='button' class='btn btn-success'>Save</button>&nbsp;&nbsp;<button type='button' id='"
					+ cancelBtnID + "' onclick='javascript:" + cancelFn
					+ ";' class='btn btn-danger'>Cancel</button></div></div>");
}
function writePanelSubmitAsSaveMessage(saveBtnID, cancelBtnID, saveFn,
		cancelFn, saveBtnMessage) {
	document.write("<br/><div class='row'><div class='col-sm-12'><button id='"
			+ saveBtnID + "' onclick='javascript:" + saveFn
			+ ";' type='button' class='btn btn-success'>" + saveBtnMessage
			+ "</button>&nbsp;&nbsp;<button type='button' id='" + cancelBtnID
			+ "' onclick='javascript:" + cancelFn
			+ ";' class='btn btn-danger'>Cancel</button></div></div>");
}

/**
 * <div class="dropdown">
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    Dropdown button
  </button>
  <div class="dropdown-menu">
    <a class="dropdown-item" href="#">Link 1</a>
    <a class="dropdown-item" href="#">Link 2</a>
    <a class="dropdown-item" href="#">Link 3</a>
  </div>
</div>
*/
function writeBlankDropDown(fieldID, fieldName) {
	document.write("<br/><div class='row'><div class='col-sm-2 text-right'>"
			+ fieldName + "&nbsp;</div><div class='col-sm-8'><div class='dropdown'><button id='"
			+fieldID+"' type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>"
			+fieldName+"</button><div class='dropdown-menu'></div></div></div></div>");
}