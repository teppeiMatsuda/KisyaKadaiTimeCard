package com.example.service.workshift;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkShiftCalendar {

	private List<EventValue> eventList;
}
