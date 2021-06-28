package com.accolite.course.models;

import org.springframework.stereotype.Component;

@Component
public class Mail
{
	private String to;
	private String sub;
	private String body;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Mail [to=" + to + ", sub=" + sub + ", body=" + body + "]";
	}
	
	
}