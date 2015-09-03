package com.pw.service.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.pw.bean.Album;
import com.pw.bean.Photo;
import com.pw.service.AlbumService;
import com.pw.service.DatabaseAlbumService;

public class BufferedAlbumServiceProxy implements AlbumService {
	private AlbumService albumService;
	private Queue<Photo> photosQueue;
	private final int BUFFER_SIZE = 3;
	private int last_filled_index;

	public BufferedAlbumServiceProxy() {
		albumService = new DatabaseAlbumService();
		photosQueue = new ArrayBlockingQueue<Photo>(BUFFER_SIZE);
		last_filled_index = 0;
	}

	@Override
	public List<Album> getAlbums() throws Exception {
		return albumService.getAlbums();
	}

	@Override
	public int getNextPhoto(int albumId, int photoId) throws Exception {
		return albumService.getNextPhoto(albumId, photoId);
	}

	@Override
	public int getPrevPhoto(int albumId, int photoId) throws Exception {
		return albumService.getPrevPhoto(albumId, photoId);
	}

	@Override
	public byte[] getPhoto(int albumId, int photoId) throws Exception {
		boolean found = false;
		byte[] img = null;
		Photo photo = null;

		Iterator<Photo> it = photosQueue.iterator();
		while(it.hasNext()) {
			photo = it.next();
			if(photo.getPhotoId() == photoId) {
				img = photo.getImage();
				found = true;
				break;
			}
		}
		if(found == false) {
			img = albumService.getPhoto(albumId, photoId);
			if(photosQueue.size() == BUFFER_SIZE) {
				photosQueue.remove();
			}
			photosQueue.add(new Photo(photoId, img));
		}
		
		return img;
	}

}
