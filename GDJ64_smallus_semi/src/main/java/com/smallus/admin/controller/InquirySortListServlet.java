package com.smallus.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smallus.Inquiry.model.vo.Inquiry;
import com.smallus.admin.service.AdminService;
import com.smallus.member.model.vo.Member;

/**
 * Servlet implementation class InquirySortListServlet
 */
@WebServlet("/admin/InquirySortListServlet.do")
public class InquirySortListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquirySortListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardCheck=request.getParameter("boardCheck");
		int cPage,numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=5;
		}
		int totalData=new AdminService().selectInquirySortCount(boardCheck);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?numPerpage="+numPerpage+"&cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()+"?numPerpage="+numPerpage+"&cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?numPerpage="+numPerpage+"&cPage="+pageNo+"'>[다음]</a>";
		}
		List<Inquiry> list=new AdminService().checkInquirySort(boardCheck,cPage,numPerpage);
//		list.forEach(e->System.out.println(e)); //list불러온값 확인
		if(list!=null&&!list.isEmpty()) {
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("InquiryList", list);
		request.getRequestDispatcher("/views/admin/adminInquiryList.jsp").forward(request, response);
		}else {
		request.getRequestDispatcher("/views/admin/adminInquiryList.jsp").forward(request, response);
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
