package com.vanke.journal.web.pojo;

public class EventOuput {

	private int id;
	private String title;
	private String start;
	private String url;
	private String color;

	public EventOuput() {
		super();
	}

	public EventOuput(int id, String title, String start, String url, String color) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.url = url;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
