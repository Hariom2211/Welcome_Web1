package com.pw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pw.bean.Album;
import com.pw.util.ConnectionFactory;

public class AlbumDao {
	private final String SQL_GET_ALL_ALBUMS = "SELECT ALBUM_ID, ALBUM_NM, CREATED_DT FROM ALBUMS";
	private final String SQL_GET_NXT_PHOTO = "SELECT MIN(PHOTO_ID) AS PHOTO_ID FROM PHOTOS WHERE ALBUM_ID = ? AND PHOTO_ID > ?";
	private final String SQL_GET_PRV_PHOTO = "SELECT MAX(PHOTO_ID) AS PHOTO_ID FROM PHOTOS WHERE ALBUM_ID = ? AND PHOTO_ID < ?";
	private final String SQL_GET_PHOTO_FILE = "SELECT DATA_FILE FROM PHOTOS WHERE ALBUM_ID = ? AND PHOTO_ID = ?";

	public List<Album> getAlbums() throws Exception {
		List<Album> albums = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_GET_ALL_ALBUMS);
			albums = new ArrayList<Album>();
			while (rs.next()) {
				albums.add(new Album(rs.getInt("ALBUM_ID"), rs
						.getString("ALBUM_NM"), rs.getDate("CREATED_DT")));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return albums;
	}

	public int getNextPhoto(int albumId, int cPhotoId) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int photoId = 0;

		try {
			con = ConnectionFactory.getConnection();
			pstmt = con.prepareStatement(SQL_GET_NXT_PHOTO);
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, cPhotoId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				photoId = rs.getInt("PHOTO_ID");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return photoId;
	}

	public int getPrevPhoto(int albumId, int cPhotoId) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int photoId = 0;

		try {
			con = ConnectionFactory.getConnection();
			pstmt = con.prepareStatement(SQL_GET_PRV_PHOTO);
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, cPhotoId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				photoId = rs.getInt("PHOTO_ID");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return photoId;
	}

	public byte[] getPhoto(int albumId, int photoId) throws Exception {
		byte[] img = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionFactory.getConnection();
			pstmt = con.prepareStatement(SQL_GET_PHOTO_FILE);
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, photoId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				img = rs.getBytes("DATA_FILE");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return img;
	}
}
