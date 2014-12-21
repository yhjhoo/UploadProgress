package com;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ListFileServlet
 */
@WebServlet("/listFileServlet")
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(this.getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File dir = new File(Constant.absoluteFolderPath);
		File[] listFiles = dir.listFiles();
		
//		FileUtils fu = new FileUtils();
//		Collection<File> listFiles = fu.listFiles(dir, new String[]{}, false);
		
		JSONArray jsonArray = new JSONArray();
		for(File file : listFiles){
			log.info("fileName:" + file.getName());
			
			JSONObject obj = new JSONObject();
			obj.put("name", file.getName() );
			obj.put("path", Constant.relativeFolderPath + file.getName());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			obj.put("uploadedOn",  sf.format(new Date(file.lastModified())) );
			obj.put("fileSize",  _fileSize(file.length()) );
			
			String downloadLink = "<a href='" + Constant.relativeFolderPath + file.getName() + "'>Download</a>";
			obj.put("download", downloadLink);
			
			jsonArray.add(obj);
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonArray);
	}
	
	public String _fileSize(long fileLength){
		double bytes = fileLength;
		DecimalFormat df = new DecimalFormat("0.0");
		
		double kilobytes = 1 * 1024;
		double megabytes = kilobytes * 1024;
		double gigabytes = megabytes * 1024;
		double terabytes = gigabytes * 1024;
		double petabytes = terabytes * 1024;
		double exabytes = petabytes * 1024;
		double zettabytes = exabytes * 1024;
		double yottabytes = zettabytes * 1024;
		
		if(bytes/terabytes > 1){
			return df.format(bytes/terabytes) + "TB";
		}
		
		if(bytes/gigabytes > 1){
			return df.format(bytes/gigabytes) + "GB";
		}
		
		if(bytes/megabytes > 1){
			return df.format(bytes/megabytes) + "MB";
		}
		
		if(bytes/kilobytes > 1){
			return df.format(bytes/kilobytes) + "KB";
		}
		
		return bytes + "B";
		
		
	}

}
