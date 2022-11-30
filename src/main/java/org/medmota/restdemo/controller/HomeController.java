package org.medmota.restdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Value(value = "${spring.profile.message}")
	private String msg;

	@GetMapping("/")
	public String getMsg() {
		return msg;
	}

}
