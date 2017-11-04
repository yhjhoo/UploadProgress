package com;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class UploadStatus
 */
@WebServlet("/uploadStatus")
public class UploadStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            response.reset();
            JSONObject obj = new JSONObject();
            obj.put("status", session.getAttribute("status"));
            obj.put("read", session.getAttribute("read"));
            obj.put("total", session.getAttribute("total"));

            response.getWriter().print(obj);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
