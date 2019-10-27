$(function(){

	// 出勤ボタン
	const attendanceB = document.querySelector("#attendance");
	// 退勤ボタン
	const leaveB = document.querySelector("#leave");

	// 出勤ボタンのクリックイベントにメソッドを紐づけ。
	attendanceB.addEventListener("click", function(){
		postAttendance(this);
	});
	// 退勤ボタンのクリックイベントにメソッドを紐づけ。
	leaveB.addEventListener("click", function(){
		postAttendance(this);
	});

	// ポスト用メソッド
	function postAttendance(buttonInfo) {
	    const token = $("meta[name='_csrf']").attr("content");
	    const header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	      xhr.setRequestHeader(header, token);
	    });
		$.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url: "/info/attendance",
		      data:buttonInfo.id
		}).done(function(data,textStatus,jqXHR) {
		  	if(data["registResult"]) buttonInfo.disabled = true;
		  	console.log(buttonInfo.value);
		  	if(buttonInfo.value == '出勤') leaveB.disabled = false;
		}).fail(function(jqXHR, textStatus, errorThrown){
			console.log("失敗");
		}).always(function(test){
			console.log("オールウェイズ" + test);
		});
	}



});


