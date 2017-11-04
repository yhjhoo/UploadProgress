package com;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Upload
 */

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiskFileItemFactory diFactory = new DiskFileItemFactory();
        FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getSession().getServletContext());
        diFactory.setFileCleaningTracker(fileCleaningTracker);
        FileItemFactory factory = diFactory;

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setProgressListener(new UploadListener(request));

        request.getAttribute("upload");
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        for (FileItem item : items) {
            File temFile = new File(Constant.absoluteFolderPath + item.getName());
            try {
                if (!temFile.exists()) {
                    log.info("Absolute path of upload file: " + temFile.getAbsolutePath());
                    item.write(temFile);
                } else {
                    log.info("File is exist!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
