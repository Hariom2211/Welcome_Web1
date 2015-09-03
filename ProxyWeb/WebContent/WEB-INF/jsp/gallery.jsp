<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Gallery</title>
		<script type="text/javascript">
			function doAction(action) {
				document.getElementById("action").value=action;
				document.forms['galleryForm'].submit();
			}			
		</script>
	</head>
	<body>
		<h1>Cloud Gallery</h1>
		<form name="galleryForm" action="${pageContext.request.contextPath}/gallery">
			<table>
				<tr>
					<td>
						Album :
					</td>
					<td>
						<select name="albums">
							<option value="0"></option>
							<c:forEach items="${albums}" var="album">
								<c:choose>
									<c:when test="${albumId == album.albumId}">
										<option value="${album.albumId}" selected="selected">${album.albumName}</option>
									</c:when>
									<c:otherwise>
										<option value="${album.albumId}">${album.albumName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td>&nbsp;</td>
					<td>
						<input type="button" value="go" onclick="doAction('album_selected')"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="&lt;" onclick="doAction('prev')"/>
					</td>
					<td colspan="2">
						<input type="button" value="&gt;" onclick="doAction('next')"/>
					</td>
				</tr>
				<c:if test="${cPhotoId > 0}">
					<tr>
						<td colspan="4">Preview</td>
					</tr>
					<tr>
						<td colspan="4">
							<img src="${pageContext.request.contextPath}/photoStream?albumId=${albumId}&photoId=${cPhotoId}" height="300px" width="450px"/>
						</td>
					</tr>
				</c:if>
			</table>
			<input type="hidden" name="action" id="action"/>	
			<input type="hidden" name="cPhotoId" value="${cPhotoId}"/>
		</form>	
	</body>
</html>



