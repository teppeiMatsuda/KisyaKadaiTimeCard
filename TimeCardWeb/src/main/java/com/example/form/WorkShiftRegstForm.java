package com.example.form;

import java.util.List;

import com.example.model.TWorkShift;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WorkShiftRegstForm {
	private List<TWorkShift> workShiftList;

}
