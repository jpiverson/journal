package com.vanke.journal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vanke.journal.enums.Type;
import com.vanke.journal.enums.Tech;

@Entity
@Table(name = "journal")
public class Journal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;// 主键标识

	@Column(name = "title", nullable = false, length = 20)
	private String title;// 模块名称

	@Column(name = "content", nullable = false)
	private String content;// 描述

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 10)
	private Type type;

	@Column(name = "project", length = 50)
	private String project;

	@Enumerated(EnumType.STRING)
	@Column(name = "tech", length = 50)
	private Tech tech;

	@Column(name = "remarks", length = 50)
	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", nullable = false)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", nullable = false)
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Tech getTech() {
		return tech;
	}

	public void setTech(Tech tech) {
		this.tech = tech;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
