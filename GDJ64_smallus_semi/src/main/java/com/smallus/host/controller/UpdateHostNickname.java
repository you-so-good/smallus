package com.smallus.host.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smallus.host.model.vo.Host;
import com.smallus.host.service.HostService;

/**
 * Servlet implementation class UpdateHostNickname
 */
@WebServlet(name = "/host/updateNickname.do", urlPatterns = { "/host/updateNickname.do" })
public class UpdateHostNickname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHostNickname() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		Host host=(Host)session.getAttribute("loginHost");
		String hostId=(host.getHostId());
		
		String nickname=request.getParameter("nickname");
		System.out.println(nickname);
		int result=new HostService().updateHostNickname(nickname,hostId);
		if(result>0) {
			System.out.println("nick change Ok");
		}
		System.out.println("nickname "+result);
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
