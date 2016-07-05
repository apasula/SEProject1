package com.couse.schedule.service;

import java.util.HashMap;

public class DepartmentMap {

	public static HashMap<String, String> map = new HashMap<>();
	
	static{
		
		map.put("ENG MGMT", "MSE.ENGMGT");
		map.put("Mech Engr", "MSE.ME");
		map.put("Electrical Engineering", "MSE.EE");
		map.put("Computer Engineering", "MSE.CE");
		map.put("Software Engineering", "MSCS.SFTW.ENG");

	}
}
