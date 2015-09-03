package com.pw.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pw.service.AlbumService;
import com.pw.util.ObjectFactory;

@WebServlet(name = "PhotoStreaming", urlPatterns = { "/photoStream" })
public class PhotoStreamingServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int albumId = 0;
		int photoId = 0;
		byte[] img = null;
		AlbumService albumService = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			albumId = Integer.parseInt(request.getParameter("albumId"));
			photoId = Integer.parseInt(request.getParameter("photoId"));
			albumService = (AlbumService) ObjectFactory
					.getObject("AlbumService");
			img = albumService.getPhoto(albumId, photoId);
			
			response.setContentLength(img.length);
			response.setContentType("img/jpeg");
			response.setHeader("content-disposition", "inline");
			
			byte[] buffer = new byte[1024];
			bis = new BufferedInputStream(new ByteArrayInputStream(img));
			bos = new BufferedOutputStream(response.getOutputStream());
			while(bis.read(buffer) != -1) {
				bos.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}finally {
			if(bis != null) {
				bis.close();
			}
			if(bos != null) {
				bos.close();
			}
		}
	}

}
