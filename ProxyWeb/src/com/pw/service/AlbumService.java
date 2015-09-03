package com.pw.service;

import java.util.List;

import com.pw.bean.Album;

public interface AlbumService {
	List<Album> getAlbums() throws Exception;

	int getNextPhoto(int albumId, int photoId) throws Exception;

	int getPrevPhoto(int albumId, int photoId) throws Exception;

	byte[] getPhoto(int albumId, int photoId) throws Exception;
}
