package com;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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
			obj.put("path", Constant.relativeFolderPath + file.getName());
			
			jsonArray.add(obj);
		}
		response.getWriter().print(jsonArray);
	}

}
