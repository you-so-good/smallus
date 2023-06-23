package com.smallus.host.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.smallus.host.model.vo.Calc;
import com.smallus.host.model.vo.Host;
import com.smallus.host.service.CalcService;
import com.smallus.payment.model.vo.PaymentCalc;
import com.smallus.payment.service.PaymentService;

/**
 * Servlet implementation class ViewHostCalcServlet
 */
@WebServlet("/host/sortingHostCalc.do")
public class SortingHostCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortingHostCalcServlet() {
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
		String hostId=host.getHostId();
		// 페이징 처리
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		} catch (NumberFormatException e) {
			numPerpage = 6;
		}

		String pageBar = "";
		int totalData = new CalcService().selectCalcCount(hostId);
		System.out.println(totalData+" "+hostId);
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
		System.out.println(hostId+" "+totalData);
		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar += "<span class='h-pageBar-txt'> 이전 </span>";
		} else {
			pageBar += "<a href='" + request.getRequestURI() + "?hostId=" + hostId + "&cPage=" + (pageNo - 1)
					+ "&numPerpage=" + numPerpage + "' class='h-pageBar-txt'> 이전 </a>";
		}
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<span class='h-pageBar-now'> " + pageNo + " </span>";
			} else {
				pageBar += "<a href='" + request.getRequestURI() + "?hostId=" + hostId + "&cPage=" + pageNo
						+ "&numPerpage=" + numPerpage + "'> " + pageNo + " </a>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<span class='h-pageBar-txt'> 다음 </span>";
		} else {
			pageBar += "<a href='" + request.getRequestURI() + "?hostId=" + hostId + "&cPage=" + pageNo + "&numPerpage="
					+ numPerpage + "' class='h-pageBar-txt'> 다음 </a>";
		}
		request.setAttribute("pageBar", pageBar);

		//		정산 대기','처리 중','정산 완료
		String calcStatus=request.getParameter("calcStatus");
		if(calcStatus.equals("Y")) calcStatus="정산완료";
		else if(calcStatus.equals("W")) calcStatus="정산대기";
		else if(calcStatus.equals("N"))calcStatus="정산거절";
		List<Calc> cSortList=new CalcService().sortingByCalcStatus(hostId, calcStatus, cPage, numPerpage);
		if(cSortList.isEmpty()||cSortList==null) {
			System.out.println("cSortList 없음없");
		}else {
			System.out.println("cSortList 있음있");
			request.setAttribute("cSortList",cSortList);				
		}
		request.getRequestDispatcher("/views/host/viewHostCalc.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
