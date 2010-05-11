$(function() {
	$('input[id*=btnBlock]').click(function() {
		$('#blockUsrDiv').dialog( {
			modal : true,
			resizable : false,
			title : "Bloquear usuario",
			close : function(event, ui) {
				closeDialog();
			}
		});
		$('[name=idPregunta]').attr('value', $(this).attr('rel'));
	});
});

$(function() {
	$('input[id*=btnClose]').click(function() {
		closeDialog();
	});
});

function closeDialog() {
	document.blockForm.reset();
	$('#blockUsrDiv').dialog("destroy");
}

function bloquearUsuario() {
	$('#blockUsrDiv').dialog("destroy");
	deleteQuestionDiv('div' + $('[name=idPregunta]').attr('value'));
}

function deleteQuestionDiv(id) {
	$("#" + id).remove();
}