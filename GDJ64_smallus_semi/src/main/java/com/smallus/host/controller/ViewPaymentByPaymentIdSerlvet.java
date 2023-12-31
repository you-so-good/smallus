package com.smallus.host.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smallus.payment.model.vo.PaymentCompleted;
import com.smallus.payment.service.PaymentService;

/**
 * Servlet implementation class ViewPaymentByPaymentIdSerlvet
 */
@WebServlet("/host/viewPaymentDetail.do")
public class ViewPaymentByPaymentIdSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPaymentByPaymentIdSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String paymentId=request.getParameter("paymentId");
		System.out.println(paymentId);
		PaymentCompleted p =new PaymentService().selectPaymentByPaymentId(paymentId);
		if(p!=null) {
			request.setAttribute("payment", p);
			request.getRequestDispatcher("/views/host/hostPayment.jsp").forward(request, response);
			
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
