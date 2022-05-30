package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceDemo {

	public boolean ispalindrome(int x) {
		int result = 0;
		int before = x;
		while (x > 0) {
			result = result * 10 + x % 10;
			x /= 10;
		}
		return result == before;
	}
}
