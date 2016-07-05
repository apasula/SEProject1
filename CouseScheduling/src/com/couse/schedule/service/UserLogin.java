package com.couse.schedule.service;

public class UserLogin {

	public boolean adminLogin(String username ,String password){
		
		if(username.equals(Constants.ADMIN_LOGIN) && password.equals(Constants.ADMIN_PASSWORD)){
			return true;
		}
		return false;
	}
	
    public boolean directorLogin(String username ,String password){
		
		if(username.equals(Constants.DIRECTOR_LOGIN) && password.equals(Constants.DIRECTOR_PASSWORD)){
			return true;
		}
		return false;
	}
}
