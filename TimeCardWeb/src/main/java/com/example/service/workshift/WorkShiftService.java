package com.example.service.workshift;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.TUserDetail;
import com.example.model.TWorkShift;
import com.example.model.TWorkShiftExample;
import com.example.repository.TWorkShiftMapper;
import com.example.service.UserDetailService;

@Transactional
@Service
public class WorkShiftService {

	@Autowired
	TWorkShiftMapper tWorkShiftMapper;
	@Autowired
	UserDetailService userDetailService;

	@Transactional(readOnly = true)
	public List<TWorkShift> listCurrentMonthShiftByUserId(int userId, String month) {
		List<TWorkShift> shiftList = new ArrayList<>();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate date = LocalDate.parse(month, df);
		LocalDateTime startOfDay = date.withDayOfMonth(1).atStartOfDay();
		LocalDateTime nextMonth = startOfDay.plusMonths(1);
		TWorkShiftExample example = new TWorkShiftExample();
		example.createCriteria()
			.andUserIdEqualTo(userId)
			.andWorkDateGreaterThanOrEqualTo(this.convertSqlDate(startOfDay))
			.andWorkDateLessThan(this.convertSqlDate(nextMonth));
		shiftList = tWorkShiftMapper.selectByExample(example);
		return shiftList;
	}

	@Transactional
	public int InsertOrUpdateByList(List<TWorkShift> workShiftList) {
		int count = 0;
		for(TWorkShift record: workShiftList) {
			tWorkShiftMapper.insertOrUpdate(record);
			count++;
		}
		return count;
	}

	public WorkShiftCalendar createCalendar(int userId, String month) {
		WorkShiftCalendar workShiftCalendar = new WorkShiftCalendar();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate date = LocalDate.parse(month, df);
		LocalDate firstOfMonth = date.withDayOfMonth(1);
		LocalDate lastOfMonth = date.plusMonths(1L).withDayOfMonth(1).minusDays(1L);
		List<TWorkShift> shiftList = listCurrentMonthShiftByUserId(userId, month);
		final Map<Integer, TWorkShift> shiftMap = new HashMap<>();
		shiftList.stream().forEach(shift -> shiftMap.put(shift.getWorkDate().getDate(), shift));
		TUserDetail detail = userDetailService.findByUserId(userId);
		String startOfWork = detail.getStartOfWorkTime();
		String endOfWork = detail.getEndOfWorkTime();
		List<EventValue> eventList = new LinkedList<>();
		for(int i = firstOfMonth.getDayOfMonth(); i <= lastOfMonth.getDayOfMonth(); i++) {
			if(i == 1) {
				for(int j = 1; j < firstOfMonth.getDayOfWeek().getValue(); j++) {
					eventList.add(new EventValue());
				}
			}
			if(shiftMap.get(i) == null) {
				if(eventList.size() % 7 < 5) {
					eventList.add(new EventValue(i, startOfWork, endOfWork, "", "", false));
				} else {
					eventList.add(new EventValue(i, null, null, "", "", false));
				}
			}else {
				eventList.add(new EventValue(i, shiftMap.get(i).getStartOfWorkTime(), shiftMap.get(i).getEndOfWorkTime(), "", "", false));
			}

			if(i == lastOfMonth.getDayOfMonth()) {
				for(int k = 7; k > lastOfMonth.getDayOfWeek().getValue(); k--) {
					eventList.add(new EventValue());
				}
			}

		}
		workShiftCalendar.setEventList(eventList);
		return workShiftCalendar;
	}


	private Date convertSqlDate(LocalDateTime localDateTime) {
		return  Date.valueOf(localDateTime.toLocalDate());
	}
}
