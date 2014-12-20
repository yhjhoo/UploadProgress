package com;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class Upload
 */

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(this.getClass());
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create a factory for disk-based file items
		DiskFileItemFactory diFactory = new DiskFileItemFactory();
//		diFactory.setRepository(new File(""));
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getSession().getServletContext());;
		diFactory.setFileCleaningTracker(fileCleaningTracker);
		FileItemFactory factory = diFactory;

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setProgressListener(new UploadListener(request) );

		request.getAttribute("upload");
		// Parse the request
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
				String contentType = item.getContentType();
				boolean isInMemory = item.isInMemory();
				long sizeInBytes = item.getSize();
			} else {
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				String contentType = item.getContentType();
				boolean isInMemory = item.isInMemory();
				long sizeInBytes = item.getSize();
			}
			//File temFile = new File("/var/www/upload/" + item.getName() );
			
			
			File temFile = new File(Constant.absoluteFolderPath + item.getName() );
			try {
				if(!temFile.exists()){
					log.info("Absolute path of upload file: " + temFile.getAbsolutePath() );
					item.write(temFile);
				}else{
					log.info("File is exist!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

	}
}
