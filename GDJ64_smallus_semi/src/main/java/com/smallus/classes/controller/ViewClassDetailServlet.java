package com.smallus.classes.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smallus.classes.model.service.ClassService;
import com.smallus.classes.model.vo.ClassDetail;
import com.smallus.classes.model.vo.Classes;

/**
 * Servlet implementation class VewClassDetailServlet
 */
@WebServlet("/class/viewClassDetail.do")
public class ViewClassDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewClassDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String classId=request.getParameter("classId");
		String hostId=request.getParameter("hostId");
		System.out.println(classId);
		List<Classes> classList=new ClassService().selectClassesByHostId(classId);
		List<ClassDetail> classDetailList = new ClassService().selectClassDetailByClassId(hostId);
		
		
		if(classList!=null&&!classList.isEmpty()&&classDetailList!=null&&!classDetailList.isEmpty()) {
			request.setAttribute("classList", classList);
			request.setAttribute("classDetailList", classDetailList);
			request.getRequestDispatcher("/views/host/hostClassDetail.jsp").forward(request, response);
		}else {
			System.out.println("클래스 djqt음 ");
			request.setAttribute("msg", "조회할 클래스가 없습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
