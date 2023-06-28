package com.smallus.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smallus.admin.service.AdminService;
import com.smallus.classes.model.vo.Classes;
import com.smallus.main.service.MainService;

/**
 * Servlet implementation class MainSearchServlet
 */
@WebServlet("/main/mainSearch.do")
public class MainSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("q");
		List<Classes> listCheck=new MainService().searchCategoriesCheck(search);
		if(listCheck!=null) {
		
		int cPage,numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		
		int totalData=new MainService().searchCategoriesCount(search);
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
		List<Classes> list=new MainService().searchCategories(search,cPage,numPerpage);
		request.setAttribute("list",list);
		request.setAttribute("listCount",totalData);
		
		}else {			
			int cPage,numPerpage;
			try {
				cPage=Integer.parseInt(request.getParameter("cPage"));
			}catch(NumberFormatException e) {
				cPage=1;
			}
			try {
				numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
			}catch(NumberFormatException e) {
				numPerpage=10;
			}
			
			int totalData=new MainService().searchAddresCount(search);
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
			List<Classes> list2=new MainService().searchAddres(search,cPage,numPerpage);
			request.setAttribute("list",list2);
			request.setAttribute("listCount",totalData);
			
			
		}
		request.setAttribute("search", search);
		request.getRequestDispatcher("/views/main/searchCategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}