package com.pw.bean;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
	private int albumId;
	private String albumName;
	private Date createdDt;

	public Album(int albumId, String albumName, Date createdDt) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.createdDt = createdDt;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

}
