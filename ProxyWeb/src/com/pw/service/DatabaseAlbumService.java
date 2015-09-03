package com.pw.service;

import java.util.List;

import com.pw.bean.Album;
import com.pw.dao.AlbumDao;
import com.pw.util.ObjectFactory;

public class DatabaseAlbumService implements AlbumService {

	@Override
	public List<Album> getAlbums() throws Exception {
		AlbumDao albumDao = null;
		List<Album> albums = null;

		albumDao = (AlbumDao) ObjectFactory.getObject("AlbumDao");
		albums = albumDao.getAlbums();

		return albums;
	}

	@Override
	public int getNextPhoto(int albumId, int cPhotoId) throws Exception {
		int nPhotoId = 0;
		AlbumDao dao = null;

		dao = (AlbumDao) ObjectFactory.getObject("AlbumDao");
		nPhotoId = dao.getNextPhoto(albumId, cPhotoId);
		if (nPhotoId == 0) {
			nPhotoId = cPhotoId;
		}
		return nPhotoId;
	}

	@Override
	public int getPrevPhoto(int albumId, int cPhotoId) throws Exception {
		int pPhotoId = 0;
		AlbumDao dao = null;

		dao = (AlbumDao) ObjectFactory.getObject("AlbumDao");
		pPhotoId = dao.getPrevPhoto(albumId, cPhotoId);
		if (pPhotoId == 0) {
			pPhotoId = cPhotoId;
		}
		return pPhotoId;
	}

	@Override
	public byte[] getPhoto(int albumId, int photoId) throws Exception {
		System.out.println("getting photo from db...");
		return ((AlbumDao) ObjectFactory.getObject("AlbumDao")).getPhoto(
				albumId, photoId);
	}

}
