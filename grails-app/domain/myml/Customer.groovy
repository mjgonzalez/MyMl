package myml

import java.util.Collection;

class Customer{
	String nickname
	
	static constraints = {
		nickname(maxSize:30)
	}
}