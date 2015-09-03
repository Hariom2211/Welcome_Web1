package com.pw.bean;

public class Photo {
	private int photoId;
	private byte[] image;

	public Photo(int photoId, byte[] image) {
		this.photoId = photoId;
		this.image = image;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
