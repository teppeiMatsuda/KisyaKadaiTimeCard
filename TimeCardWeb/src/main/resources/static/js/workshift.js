const week = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"];

initWorkShift();

function initWorkShift() {
	let userId = 1; // document.getElementByName("param").value;
	let data = requestWorkShift(createRequest(userId));
}

function createRequest(userId) {
	return data = {
			"userId": userId
	};
}

function requestWorkShift(requestBody) {
	let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
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
		console.log(data);
		let eventList = data.eventList;
		let element = document.getElementById("workshift");
		for(i = 0; i < eventList.length / 7; i++){
			let tr = document.createElement("tr");
			tr.classList.add("week");
			for(j = i * 7; j < i * 7 + 7; j++) {
				let td = document.createElement("td");
				td.classList.add("day");
				event = eventList[j];
				if(event.eventDay != null){
					let day = document.createElement("div");
					let regularShift = document.createElement("div");
					let workStartTime = document.createElement("div");
					let workEndTime = document.createElement("div");
					day.innerHTML = event.eventDay;
					if(event.regularStrtOfTime != null){
						regularShift.innerHTML = event.regularStrtOfTime + "～" + event.regularEndOfTime;
						workStartTime.innerHTML = "begin:" + event.startOfWorkTime;
						workEndTime.innerHTML = "leave:" + event.endOfWorkTime;
					} else{
						regularShift.innerHTML = "holyday";
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

function createRegularShift() {

}