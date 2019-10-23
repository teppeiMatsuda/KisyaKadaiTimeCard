$(function(){


	const attendanceB = document.querySelector("#attendance");

	console.log(attendanceB);

	const leaveB = document.querySelector("#leave");

	console.log(leaveB);

	attendanceB.addEventListener("click", function(e){
		console.log(this + "this");
		console.log(e + "e")
		postAttendance(e);
	});

	function postAttendance(buttonInfo) {
		//buttonInfo.getId

	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	      xhr.setRequestHeader(header, token);
	    });

		$.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
/*//		      dataType : 'json',
*/		      url: "/info/attendance",
		      data: JSON.stringify(buttonInfo),
		}).done(function(data,textStatus,jqXHR) {
			  	console.log("完了" + data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			console.log("失敗");
		}).always(function(test){
			console.log("オールウェイズ" + test.toString());
		});
	}



});


