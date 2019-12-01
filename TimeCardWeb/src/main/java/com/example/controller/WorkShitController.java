package com.example.controller;

import static com.example.common.PathConst.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.SessionForm;
import com.example.form.WorkShiftForm;
import com.example.service.workshift.WorkShiftCalendar;
import com.example.service.workshift.WorkShiftService;

@Controller
@RequestMapping(WORK_SHIFT_PATH)
public class WorkShitController {

	public static final String HTML_PATH = WORK_SHIFT_PATH;

	@Autowired
	WorkShiftService workShiftService;

	@Autowired
	SessionForm session;

	@ModelAttribute
	WorkShiftForm setUpForm() {
		return new WorkShiftForm();
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public WorkShiftCalendar workShiftList(@RequestBody WorkShiftForm form) {
//		if(userId == null) {
//			userId = session.getUserId();
//		}
		LocalDate now = LocalDate.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/MM/dd");
//		List<TWorkShift> workShiftList = new ArrayList<TWorkShift>();
//		workShiftList = workShiftService.listCurrentMonthShiftByUserId(form.getUserId(), now.format(df));
		WorkShiftCalendar shiftCalendar = workShiftService.createCalendar(form.getUserId(), now.format(df));
		return shiftCalendar;
	}

}
