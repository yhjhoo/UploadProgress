var startTime;
$(document).ready(function() {
//	$("#uploadprogressbar").progressBar(12);
	$("#subButton").click(function() {
		var myDate = new Date();
		startTime = myDate.getTime();
		$(this).attr("disabled", true);
		window.setTimeout("getProgressBar()", 1000);
		$("#uploadForm").submit();
	});
	
	
	$("#fileTable").dataTable( {
		"ajax": {
			"url": "listFileServlet",
			"dataSrc": ""
		},
		"columns": [
			{ "data": "name" },
			{ "data": "uploadedOn" },
			{ "data": "fileSize" },
			{ "data": "download" }
		]
	});
});
function getProgressBar() {
	var interval = window.setTimeout("getProgressBar()", 500);
	$.ajax({
		url : "./uploadStatus",
		dataType : "json",
		cache : false,
		success : function(data) {
			if (data) {
				$("#status").html(data.status);
				$("#read").html(data.read);
				$("#total").html(data.total);

				var percentage = Math.floor(100 * parseInt(data.read)/ parseInt(data.total));
				$("#uploadprogressbar").progressBar(percentage);
				if(percentage == 100){
					reloadContentList();
					$("#subButton").attr("disabled", false); 
					clearTimeout(interval);
				}
			}
			// $("#status").removeClass("loading");
		},
		error : function() {
			$("#status").html("error happend!");
			$("#read").html("error happend!");
			$("#total").html("error happend!");
		}
	});
}

function reloadContentList(){
	$("#fileTable").dataTable()._fnAjaxUpdate();
}
