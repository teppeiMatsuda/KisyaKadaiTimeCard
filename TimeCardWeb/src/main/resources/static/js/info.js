$(function(){

	// 出勤ボタン
	const attendanceB = document.querySelector("#attendance");
	// 退勤ボタン
	const leaveB = document.querySelector("#leave");
	// 出勤ボタン・退勤ボタンを含むNodeリスト
	const attButtonList = document.querySelectorAll(".btnList");

	// 出勤ボタンのクリックイベントにメソッドを紐づけ。
	attendanceB.addEventListener("click", function(){
		postAttendance(this);
	});
	// 退勤ボタンのクリックイベントにメソッドを紐づけ。
	leaveB.addEventListener("click", function(){
		postAttendance(this);
	});

	// このままではIEで取り扱えない(forEachが使えない)ため配列に変換する。
	const attButtonListArray = Array.prototype.slice.call(attButtonList,0); 

	// 初期表示時に要素分だけ実行する。
	buttonValueChange();
	// 出退勤ボタンの表示文字列を状態によって変更するメソッド。
	function buttonValueChange(){
		attButtonListArray.forEach(function(elem,index){
			changeButtonValueWhenDisabled(elem);
		});
	}

	// 出退勤ボタンがdisabledの際にボタンのvalueを変換するメソッド。
	function changeButtonValueWhenDisabled(buttonInfo){
		if(buttonInfo.id == 'attendance' && buttonInfo.disabled == true){
			buttonInfo.value = '✓出勤済';
			if(buttonInfo.classList.contains("btn-outline-primary")) {
				buttonInfo.classList.remove("btn-outline-primary");
				buttonInfo.classList.add("btn-outline-success");
			}
		}else if(buttonInfo.id == 'leave' && buttonInfo.disabled == true && attendanceB.disabled == false){
			buttonInfo.value = '未出勤';
		}else if(buttonInfo.id == 'leave' && buttonInfo.disabled == true && attendanceB.disabled == true){
			buttonInfo.value = '✓退勤済'
			if(buttonInfo.classList.contains("btn-outline-danger")) {
				buttonInfo.classList.remove("btn-outline-danger");
				buttonInfo.classList.add("btn-outline-success");
			}
		}
	}

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
		  	if(buttonInfo.value == '出勤') {
				  leaveB.value = '退勤';
				  leaveB.disabled = false;
			}
			// ボタンのvalue(表示文字)を"済"に変更する。
			buttonValueChange();
		}).fail(function(jqXHR, textStatus, errorThrown){
			alert("出退勤記録の登録に失敗しました、管理者に確認してください。");
		}).always(function(test){
		});
	}
});


