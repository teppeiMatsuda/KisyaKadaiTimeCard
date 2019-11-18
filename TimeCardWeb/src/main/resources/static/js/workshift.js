const token = $("meta[name='_csrf']").attr("content");
const isAdmin = document.getElementsByName("roleId")[0].value == 1;
const userId = document.getElementsByName("userId")[0].value;
let currentMonth = getNowYM();

document.getElementById("editbtn").onclick = function() {
	let inputData = new Array();
	let tbl = document.getElementById("workshift");
	for(var i = 1; i < tbl.rows.length; i++) {
		for(var j = 0; j < tbl.rows[i].cells.length; j++) {
			let inputVal = arrayConvInput(tbl.rows[i].cells[j].children);
			if(inputVal.editcheck != null && inputVal.editcheck) {
				var inputWorkShift = {
					"userId": userId,
					"workDate": new Date(currentMonth + "-" + inputVal.day),
					"deleteFlag": "0",
					"startOfWorkTime": inputVal.strHour + ":" + inputVal.strMin,
					"endOfWorkTime": inputVal.endHour + ":" + inputVal.endMin
				}
				inputData.push(inputWorkShift);
			}
		}
	}
	let postData = {"workShiftList": inputData}
	let req = new Request("/workshift/rgst", {
		method: "post"
		, body: JSON.stringify(postData)
        , headers: {
            "Content-Type": "application/json; charset=utf-8"
            ,  "X-CSRF-TOKEN": token
        },
	});
	fetch(req).then(response =>{
		if(response.ok) {
			alert("更新が完了しました");
		}
	}).catch(error =>{alert("シフト情報の更新に失敗しました。");});
}

function initWorkShift() {
	requestWorkShift(createRequest(userId));
}

function createRequest(userId) {
	return data = {
			"userId": userId
	};
}

function requestWorkShift(requestBody) {
	let req = new Request("/workshift", {
		method: "post"
		, body: JSON.stringify(requestBody)
        , headers: {
            "Content-Type": "application/json; charset=utf-8"
            ,  "X-CSRF-TOKEN": token
        },
	});
	fetch(req).then(response =>{
		if(response.ok) {
			return response.json();
		}
	}).then(data =>{
		let eventList = data.eventList;
		let element = document.getElementById("workshift");
		for(var i = 0; i < eventList.length / 7; i++){
			let tr = document.createElement("tr");
			tr.classList.add("week");
			for(j = i * 7; j < i * 7 + 7; j++) {
				let td = document.createElement("td");
				td.classList.add("day");
				event = eventList[j];
				if(event.eventDay != null){
					let day = document.createElement("div");
					day.name = "day";
					let regularShift = createRegularShift(event);
					let workStartTime = document.createElement("div");
					let workEndTime = document.createElement("div");
					day.innerHTML = event.eventDay;
					if(isAdmin) {
						let check = document.createElement("input");
						check.setAttribute("type", "checkbox");
						check.name = "editcheck";
						td.appendChild(check);
					}
					if(event.regularStrtOfTime != null){
						workStartTime.innerHTML = "begin:" + event.startOfWorkTime;
						workEndTime.innerHTML = "leave:" + event.endOfWorkTime;
					} else{
						td.classList.add("holyday");
					}
					td.appendChild(day);
					td.appendChild(regularShift);
					td.appendChild(workStartTime);
					td.appendChild(workEndTime);
				} else{
					td.classList.add("blank");
				}
				tr.appendChild(td);
			}
			element.appendChild(tr);
		}
	}).catch(error =>{alert("シフト情報の取得に失敗しました。");});
}

function createRegularShift(event) {
	let regularShiftElement = document.createElement("div");
	regularShiftElement.name = "ragularshift";
	if(isAdmin) {
		let strt = ["00", "00"];
		let end = ["00", "00"];
		if(event.regularStrtOfTime != null) {
			strt = event.regularStrtOfTime.split(":");
			end = event.regularEndOfTime.split(":");
		}
		let strtHour = createInputElement(strt[0]);
		strtHour.name = "strHour";
		let strtMin = createInputElement(strt[1]);
		strtMin.name = "strMin";
		let endHour = createInputElement(end[0]);
		endHour.name = "endHour";
		let endMin = createInputElement(end[1]);
		endMin.name = "endMin";
		regularShiftElement.appendChild(strtHour);
		regularShiftElement.appendChild(document.createTextNode(":"));
		regularShiftElement.appendChild(strtMin);
		regularShiftElement.appendChild(document.createTextNode("～"));
		regularShiftElement.appendChild(endHour);
		regularShiftElement.appendChild(document.createTextNode(":"));
		regularShiftElement.appendChild(endMin);
	}else {
		if(event.regularStrtOfTime != null){
			regularShiftElement.innerHTML = event.regularStrtOfTime + "～" + event.regularEndOfTime;
		}else {
			regularShiftElement.innerHTML = "holyday";
		}
	}
	return regularShiftElement;
}

function createInputElement(value) {
	let inpueElm = document.createElement("input");
	inpueElm.setAttribute("type", "text");
	inpueElm.setAttribute("maxlength", "2");
	inpueElm.classList.add("shiftinput");
	inpueElm.value = value;
	return inpueElm;
}

function getNowYM(){
  let dt = new Date();
  let y = dt.getFullYear();
  let m = ("00" + (dt.getMonth()+1)).slice(-2);
  let result = y + "-" + m;
  return result;
}

function arrayConvInput(values) {
	let result = [];
	if(values != null && values.length != 0){
		for(var i =0; i < values.length; i++) {
			if(values[i].name == "day") {
				result[values[i].name] = values[i].innerHTML;
			} else if(values[i].name == "editcheck"){
				result[values[i].name] = values[i].checked;
			} else if(values[i].name == "ragularshift") {
				let regular = values[i].children;
				for(var j = 0; j < regular.length; j++) {
					result[regular[j].name] = regular[j].value;
				}
			}
		}
	}
	return result;
}