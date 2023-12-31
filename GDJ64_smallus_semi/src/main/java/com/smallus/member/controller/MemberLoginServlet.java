package com.smallus.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smallus.main.model.vo.Wish;
import com.smallus.main.service.MainService;
import com.smallus.member.model.vo.Member;
import com.smallus.member.model.vo.Notifications;
import com.smallus.member.service.MemberService;
import com.smallus.payment.service.PaymentService;


/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberlogin.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.클라이언트가 보낸 데이터를 가져옴(userId,password)
		String memberId=request.getParameter("memberId");
		String password=request.getParameter("password");
		System.out.println(memberId+" : "+password);
		
		//아이디 저장 로직처리 (*로직처리 순서 페이지 전환하는 response가 응답하기전에 쿠기를 먼저 저장시켜줘야한다!)
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		//checkbox에 check가 되면 on
		//checkbox에 check가 안되면 null
		if(saveId!=null) {
			Cookie c=new Cookie("saveId",memberId);
			c.setMaxAge(60*60*24*7);
			c.setPath("/");
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}

		//2. DB접속해서 id와 password와 일치하는 회원이 있는지 확인 후 데이터 가져오기
		Member loginMember=new MemberService().memberLogin(memberId, password);
		System.out.println(loginMember);
		//loginMember가 null을 기준으로 로그인 처리여부를 결정할 수 있음.
		if(loginMember!=null) {
			//로그인 성공 -> 인증받음
			HttpSession session=request.getSession();
			session.setAttribute("loginMember",loginMember);
			response.sendRedirect(request.getContextPath());
			List<Notifications> list =new MemberService().selectAllNotifications(memberId);
			int notcount = new MemberService().notificationsCount(memberId);
			session.setAttribute("notcount1",notcount);
			session.setAttribute("Notlist1",list);
			List<Wish> wishMember= new MainService().wishMember(memberId);
			session.setAttribute("wishMember", wishMember);
			//수강일이 지나면 결제완료 -> 수강완료로 업데이트되는 구문 
			int updateReservatation = new PaymentService().updatePaymentStatus();
			
		
		}else {
			//로그인 실패 -> 인증못받음
			//실패 메세지 출력
			request.setAttribute("msg","아이디,패스워드가 일치하지 않습니다");
			request.setAttribute("loc","/views/member/memberLogin.jsp");
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
