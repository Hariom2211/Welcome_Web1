package com.pw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pw.bean.Album;
import com.pw.service.AlbumService;
import com.pw.util.ObjectFactory;

@WebServlet(name = "Gallery", urlPatterns = { "/gallery" })
public class GalleryServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int photoId = 0;
		int albumId = 0;
		String page = null;
		String action = null;
		HttpSession session = null;
		AlbumService albumService = null;
		RequestDispatcher dispatcher = null;

		// create session
		session = request.getSession();
		try {
			albumService = (AlbumService) ObjectFactory
					.getObject("AlbumService");
			action = request.getParameter("action");

			// load page
			if (action == null || action.trim().length() == 0) {
				List<Album> albums = null;

				albums = albumService.getAlbums();
				session.setAttribute("albums", albums);
			} else if (action.equals("album_selected")) {
				albumId = Integer.parseInt(request.getParameter("albums"));
				session.setAttribute("albumId", albumId);
				photoId = albumService.getNextPhoto(albumId, 0);
				request.setAttribute("cPhotoId", photoId);
			} else if(action.equals("next")) {
				albumId = (Integer) session.getAttribute("albumId");
				int cPhotoId = Integer.parseInt(request.getParameter("cPhotoId"));
				photoId = albumService.getNextPhoto(albumId, cPhotoId);
				request.setAttribute("cPhotoId", photoId);
			}else if(action.equals("prev")) {
				albumId = (Integer) session.getAttribute("albumId");
				int cPhotoId = Integer.parseInt(request.getParameter("cPhotoId"));
				photoId = albumService.getPrevPhoto(albumId, cPhotoId);
				request.setAttribute("cPhotoId", photoId);
			}
			page = "/WEB-INF/jsp/gallery.jsp";
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
