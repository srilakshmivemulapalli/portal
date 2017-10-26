package com.nisum.portal.service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailAccount {
	
	
	@Value("${gmail.email}")
	private String adminemail;
	
	@Value("${gmail.password}")
	private String adminpassword;
	
	@Value("${gmail.subject}")
	private String subject;
	
	@Value("${gmail.question.subject}")
	private String questionSub;
	
	@Value("${gmail.answer.toQuestion}")
	private String subQuestToAnser;
  
	@Value("${gmail.training.request}")
	private String subtrainingreq;
	public String getSubQuestToAnser() {
		return subQuestToAnser;
	}

	public void setSubQuestToAnser(String subQuestToAnser) {
		this.subQuestToAnser = subQuestToAnser;
	}

	public String getAdminemail() {
		return adminemail;
	}

	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuestionSub() {
		return questionSub;
	}

	public void setQuestionSub(String questionSub) {
		this.questionSub = questionSub;
	}

	public String getSubtrainingreq() {
		return subtrainingreq;
	}

	public void setSubtrainingreq(String subtrainingreq) {
		this.subtrainingreq = subtrainingreq;
	}
}
