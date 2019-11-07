package com.example.service.workshift;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventValue {

	private Integer eventDay;
	private String regularStrtOfTime;
	private String regularEndOfTime;
	private String startOfWorkTime;
	private String endOfWorkTime;
	private boolean holidayFlg;

}
