package com.example.demo.JWT;

import org.springframework.stereotype.Repository;

@Repository
public class JwtRepository 
{
	public boolean findUser(MyUser myuser){
		System.out.println("inside findUser method");
		String uname=myuser.getUsername();
		String pwd=myuser.getPassword();
		return uname.equalsIgnoreCase("amarnath.2813@gmail.com") && pwd.equals("123456789");
		}
	
}
