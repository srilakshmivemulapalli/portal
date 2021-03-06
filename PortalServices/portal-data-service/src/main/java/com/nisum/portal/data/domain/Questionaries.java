package com.nisum.portal.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Questionaries")
public class Questionaries implements Serializable,Comparable<Questionaries>{
	public Questionaries() {
		super();
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "questionId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer questionId;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Categories categoryId;
	private String question;
	private String description;
	private Timestamp createdDate;
	private String emailId;
	
	@OneToMany(mappedBy = "questId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<QuestionReplies> questionReplies;
	
	@OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<QuestionariesComments>  questionariesComments;
	
	
	/**
	 * @return the questionariesComments
	 */
	public List<QuestionariesComments> getQuestionariesComments() {
		return questionariesComments;
	}
	/**
	 * @param questionariesComments the questionariesComments to set
	 */
	public void setQuestionariesComments(List<QuestionariesComments> questionariesComments) {
		this.questionariesComments = questionariesComments;
	}
	public Questionaries(Categories categoryId, String question, String description, Timestamp createdDate,
			String emailId) {
		super();
		this.categoryId = categoryId;
		this.question = question;
		this.description = description;
		this.createdDate = createdDate;
		this.emailId = emailId;
	}
	
	
	
	public Questionaries(Integer questionId, Categories categoryId, String question, String description,
			Timestamp createdDate,String emailId) {
		super();
		this.questionId = questionId;
		this.categoryId = categoryId;
		this.question = question;
		this.description = description;
		this.createdDate = createdDate;
		this.emailId = emailId;
	}
	/**
	 * @return the questionReplies
	 */
	public List<QuestionReplies> getQuestionReplies() {
		return questionReplies;
	}
	/**
	 * @param questionReplies the questionReplies to set
	 */
	public void setQuestionReplies(List<QuestionReplies> questionReplies) {
		this.questionReplies = questionReplies;
	}
	/**
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the categoryId
	 */
	public Categories getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((questionReplies == null) ? 0 : questionReplies.hashCode());
		result = prime * result + ((questionariesComments == null) ? 0 : questionariesComments.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionaries other = (Questionaries) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionReplies == null) {
			if (other.questionReplies != null)
				return false;
		} else if (!questionReplies.equals(other.questionReplies))
			return false;
		if (questionariesComments == null) {
			if (other.questionariesComments != null)
				return false;
		} else if (!questionariesComments.equals(other.questionariesComments))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Questionaries [questionId=" + questionId + ", categoryId=" + categoryId + ", question=" + question
				+ ", description=" + description + ", createdDate=" + createdDate + ", emailId=" + emailId
				+ ", questionReplies=" + questionReplies + ", questionariesComments=" + questionariesComments + "]";
	}
	@Override
	public int compareTo(Questionaries o) {
		return getCreatedDate().compareTo(o.getCreatedDate());
	}

	
}
