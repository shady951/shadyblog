package com.shadyblog.pojo;

import java.util.Date;

public class Iprecord {

	private Integer id;
	private String ip;
	private String address;
	private Integer indexnum;
	private Integer contentnum;
	private Date firstTime;
	private Date lastTime;

	public Iprecord(Integer id, String ip, String address, Integer indexnum, Integer contentnum,Date firstTime, Date lastTime) {
		this.id = id;
		this.ip = ip;
		this.address = address;
		this.indexnum = indexnum;
		this.contentnum = contentnum;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
	}

	public Iprecord() {
		
	}
	
	public Iprecord(Integer id, Date lastTime) {
		this.id = id;
		this.lastTime = lastTime;
	}
	
	public Iprecord(String ip, String address, Date firstTime, Date lastTime) {
		this.ip = ip;
		this.address = address;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIndexnum() {
		return indexnum;
	}

	public void setIndexnum(Integer indexnum) {
		this.indexnum = indexnum;
	}

	public Integer getContentnum() {
		return contentnum;
	}

	public void setContentnum(Integer contentnum) {
		this.contentnum = contentnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
