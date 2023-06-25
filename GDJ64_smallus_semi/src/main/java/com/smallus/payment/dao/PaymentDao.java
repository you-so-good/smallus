package com.smallus.payment.dao;

import static com.smallus.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.smallus.classes.model.vo.ClassDetail;
import com.smallus.classes.model.vo.Classes;
import com.smallus.member.model.vo.Member;
import com.smallus.payment.model.vo.Payment;
import com.smallus.payment.model.vo.PaymentCalc;
import com.smallus.payment.model.vo.PaymentCompleted;

public class PaymentDao {
	
	private Properties sql= new Properties();
	
	{
		String path=PaymentDao.class.getResource("/sql/payment.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
    //SELECT P.PAYMENT_ID, C.CLASS_TITLE, CD.BOOKING_TIME_START, CD.BOOKING_TIME_END, P.CLASS_PERSONNEL, P.PAYMENT_DATE, P.MEMBER_ID, 
	//CL.CALC_PASS_DATE FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) LEFT JOIN PAYMENT P USING(CLASS_DETAIL_ID) 
	//LEFT JOIN CALC CL USING(HOST_ID)WHERE HOST_ID=? AND P.PAYMENT_ID IS NOT NULL
	public List<Payment> selectPaymentByhostId(Connection conn, String hostId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Payment> list=new ArrayList<Payment>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectPaymentByhostId"));
			pstmt.setString(1, hostId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Payment p=new Payment();
				p.setPaymentId(rs.getString("PAYMENT_ID"));
				p.setClassTitle(rs.getString("CLASS_TITLE"));
				p.setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
				p.setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
				p.setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				p.setMemberId(rs.getString("MEMBER_ID"));
				list.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Member> searchByMemberPayment(Connection conn, String memberId,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList<Member>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchByMemberPayment"));
			//SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT PAYMENT_STATUS,PAYMENT_DATE,CLASS_TITLE,CLASS_THUMBNAIL,P.CLASS_PERSONNEL,BOOKING_TIME_START,BOOKING_TIME_END FROM PAYMENT P JOIN CLASS_DETAIL USING(CLASS_DETAIL_ID) JOIN CLASS USING(CLASS_ID) WHERE MEMBER_ID=? ORDER BY PAYMENT_DATE DESC)B) WHERE RNUM BETWEEN ? AND ?
			pstmt.setString(1, memberId);
			pstmt.setInt(2,(cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			Classes c=new Classes();
			Payment p=new Payment();
			ClassDetail d=new ClassDetail();
			p.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
			p.setPaymentDate(rs.getDate("PAYMENT_DATE"));
			c.setClassTitle(rs.getString("CLASS_TITLE"));
			c.setClassThumbnail(rs.getString("CLASS_THUMBNAIL"));
			p.setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
			d.setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
			d.setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
			p.setPaymentId(rs.getString("PAYMENT_ID"));
			Member m = new Member();
			m.setClasses(c);
			m.setPayment(p);
			m.setClassDetail(d);
			list.add(m);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Member> MemberCompletedpayment(Connection conn, String memberId,int cPage,int numPerpage,String completed){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList<Member>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberCompletedpayment"));
			//SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT PAYMENT_STATUS,PAYMENT_DATE,CLASS_TITLE,CLASS_THUMBNAIL,P.CLASS_PERSONNEL,BOOKING_TIME_START,BOOKING_TIME_END FROM PAYMENT P JOIN CLASS_DETAIL USING(CLASS_DETAIL_ID) JOIN CLASS USING(CLASS_ID) WHERE MEMBER_ID=? AND PAYMENT_STATUS=? ORDER BY PAYMENT_DATE DESC)B) WHERE RNUM BETWEEN ? AND ?
			pstmt.setString(1, memberId);
			pstmt.setString(2,completed);
			pstmt.setInt(3,(cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			Classes c=new Classes();
			Payment p=new Payment();
			ClassDetail d=new ClassDetail();
			p.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
			p.setPaymentDate(rs.getDate("PAYMENT_DATE"));
			c.setClassTitle(rs.getString("CLASS_TITLE"));
			c.setClassThumbnail(rs.getString("CLASS_THUMBNAIL"));
			p.setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
			d.setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
			d.setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
			p.setPaymentId(rs.getString("PAYMENT_ID"));
			Member m = new Member();
			m.setClasses(c);
			m.setPayment(p);
			m.setClassDetail(d);
			list.add(m);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public List<Member> Membercancellationpayment(Connection conn, String memberId,int cPage,int numPerpage,String completed){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList<Member>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("membercancellationpayment"));
			//SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT PAYMENT_STATUS,PAYMENT_DATE,CLASS_TITLE,CLASS_THUMBNAIL,P.CLASS_PERSONNEL,BOOKING_TIME_START,BOOKING_TIME_END FROM PAYMENT P JOIN CLASS_DETAIL USING(CLASS_DETAIL_ID) JOIN CLASS USING(CLASS_ID) WHERE MEMBER_ID=? AND PAYMENT_STATUS=? ORDER BY PAYMENT_DATE DESC)B) WHERE RNUM BETWEEN ? AND ?
			pstmt.setString(1, memberId);
			pstmt.setString(2,completed);
			pstmt.setInt(3,(cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			Classes c=new Classes();
			Payment p=new Payment();
			ClassDetail d=new ClassDetail();
			p.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
			p.setPaymentDate(rs.getDate("PAYMENT_DATE"));
			c.setClassTitle(rs.getString("CLASS_TITLE"));
			c.setClassThumbnail(rs.getString("CLASS_THUMBNAIL"));
			p.setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
			d.setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
			d.setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
			p.setPaymentId(rs.getString("PAYMENT_ID"));
			Member m = new Member();
			m.setClasses(c);
			m.setPayment(p);
			m.setClassDetail(d);
			list.add(m);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	
	public int paymentCount(Connection conn ,String memberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("paymentCount"));
			//SELECT COUNT(MEMBER_ID) FROM PAYMENT WHERE MEMBER_ID=?
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int paymentdetailCount(Connection conn ,String memberId,String completed) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("paymentdetailCount"));
			//SELECT COUNT(MEMBER_ID) FROM PAYMENT WHERE MEMBER_ID=? AND PAYMENT_STATUS=?
			pstmt.setString(1, memberId);
			pstmt.setString(2, completed);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public List<PaymentCompleted>  paymentCompleted(Connection conn, String paymentId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<PaymentCompleted>  list= new ArrayList<PaymentCompleted>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("paymentCompleted"));
			pstmt.setString(1, paymentId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
////SELECT PAYMENT_STATUS,PAYMENT_DATE,CLASS_TITLE,CLASS_THUMBNAIL,P.CLASS_PERSONNEL,BOOKING_TIME_START,BOOKING_TIME_END ,HOST_NAME, CLASS_PRICE, PAYMENT_NAME, TOTAL_PRICE,COUPON_PRICE FROM PAYMENT P LEFT JOIN CLASS_DETAIL USING(CLASS_DETAIL_ID) LEFT JOIN CLASS USING(CLASS_ID) LEFT JOIN HOST USING(HOST_ID) LEFT JOIN PAYMENT_TYPE USING(PAYMENT_TYPE) LEFT JOIN COUPON_TYPE USING(COUPON_ID) WHERE PAYMENT_ID=?
				PaymentCompleted pc=new PaymentCompleted();
				pc.getPayment().setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				pc.getPayment().setPaymentDate(rs.getDate("PAYMENT_DATE"));
				pc.getClasses().setClassTitle(rs.getString("CLASS_TITLE"));
				pc.getClasses().setClassThumbnail(rs.getString("CLASS_THUMBNAIL"));
				pc.getPayment().setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				pc.getClassDetail().setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
				pc.getClassDetail().setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
				pc.getPaymentType().setPaymentName(rs.getString("PAYMENT_NAME"));
				pc.getClasses().setClassPrice(rs.getInt("CLASS_PRICE"));
				pc.getHost().setHostName(rs.getString("HOST_NAME"));
				pc.getCoupon().setCouponPrice(rs.getInt("COUPON_PRICE"));
				pc.getPayment().setTotalPrice(rs.getInt("TOTAL_PRICE"));
				list.add(pc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//selectAllpaymentByhostId=SELECT P.PAYMENT_ID, C.CLASS_TITLE, CD.BOOKING_TIME_START, CD.BOOKING_TIME_END, P.CLASS_PERSONNEL, P.PAYMENT_DATE, P.MEMBER_ID, CL.CALC_PASS_DATE FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) LEFT JOIN PAYMENT P USING(CLASS_DETAIL_ID) LEFT JOIN CALC CL USING(HOST_ID)WHERE HOST_ID=? AND P.PAYMENT_ID IS NOT NULL AND PAYMENT_DATE BETWEEN TO_DATE(?) and TO_DATE(?)
	
	public int selectRsvCount(Connection conn,String hostId) {
		int count=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectRsvCount"));
			//SELECT COUNT(*) FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) 
			//LEFT JOIN PAYMENT P USING(CLASS_DETAIL_ID) WHERE HOST_ID=? AND P.PAYMENT_ID IS NOT NULL
			pstmt.setString(1, hostId);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	// SELECT P.PAYMENT_ID, C.CLASS_TITLE, CD.BOOKING_TIME_START,
	// CD.BOOKING_TIME_END, P.CLASS_PERSONNEL, P.PAYMENT_DATE, P.MEMBER_ID, P.PAYMENT_STATUS 
	// FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) LEFT
	// JOIN PAYMENT P USING(CLASS_DETAIL_ID)
	// LEFT JOIN CALC CL USING(HOST_ID)WHERE HOST_ID=? AND P.PAYMENT_ID IS NOT NULL
	public List<PaymentCalc> selectAllpaymentByhostId(Connection conn, String hostId,int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PaymentCalc> list = new ArrayList<PaymentCalc>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllpaymentByhostId"));
			pstmt.setString(1, hostId);
			pstmt.setInt(2,(cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentCalc p = new PaymentCalc();
				p.getPayment().setPaymentId(rs.getString("PAYMENT_ID"));
				p.getClasses().setClassTitle(rs.getString("CLASS_TITLE"));
				p.getClassDetail().setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
				p.getClassDetail().setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
				p.getPayment().setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				p.getPayment().setPaymentDate(rs.getDate("PAYMENT_DATE"));
				p.getPayment().setMemberId(rs.getString("MEMBER_ID"));
				p.getPayment().setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// SELECT P.PAYMENT_ID, C.CLASS_TITLE, CD.BOOKING_TIME_START,
	// CD.BOOKING_TIME_END, P.CLASS_PERSONNEL, P.PAYMENT_DATE, P.MEMBER_ID, P.PAYMENT_STATUS 
	// FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) LEFT
	// JOIN PAYMENT P USING(CLASS_DETAIL_ID)
	// LEFT JOIN CALC CL USING(HOST_ID)WHERE HOST_ID=? AND P.PAYMENT_ID=? 
	
	public List<PaymentCalc> sortingPaymentByStatus(Connection conn, String hostId,String passStatus,int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PaymentCalc> list = new ArrayList<PaymentCalc>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("sortingPaymentByStatus"));
			pstmt.setString(1, hostId);
			pstmt.setString(2, passStatus);
			pstmt.setInt(3,(cPage-1)*numPerpage+1);
			pstmt.setInt(4, cPage*numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentCalc p = new PaymentCalc();
				p.getPayment().setPaymentId(rs.getString("PAYMENT_ID"));
				p.getClasses().setClassTitle(rs.getString("CLASS_TITLE"));
				p.getClassDetail().setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
				p.getClassDetail().setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
				p.getPayment().setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				p.getPayment().setPaymentDate(rs.getDate("PAYMENT_DATE"));
				p.getPayment().setMemberId(rs.getString("MEMBER_ID"));
				p.getPayment().setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
//	selectNewPaymentByhostId=SELECT * FROM (SELECT ROWNUM AS RNUM, B.* 	FROM 
//	(SELECT P.PAYMENT_ID, C.CLASS_TITLE, CD.BOOKING_TIME_START, CD.BOOKING_TIME_END, P.CLASS_PERSONNEL, P.PAYMENT_DATE, P.MEMBER_ID, CL.CALC_PASS_DATE 
//	FROM CLASS_DETAIL CD LEFT JOIN CLASS C USING(CLASS_ID) 
//	LEFT JOIN PAYMENT P USING(CLASS_DETAIL_ID) 
//	LEFT JOIN CALC CL USING(HOST_ID) WHERE HOST_ID=? AND P.PAYMENT_ID IS NOT NULL)B) 
//	WHERE RNUM BETWEEN 1 AND 5 ORDER BY PAYMENT_DATE DESC
	public List<PaymentCalc> selectNewPaymentByhostId(Connection conn, String hostId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PaymentCalc> list = new ArrayList<PaymentCalc>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNewPaymentByhostId"));
			pstmt.setString(1, hostId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentCalc p = new PaymentCalc();
				p.getPayment().setPaymentId(rs.getString("PAYMENT_ID"));
				p.getClasses().setClassTitle(rs.getString("CLASS_TITLE"));
				p.getClassDetail().setBookingTimeStart(rs.getDate("BOOKING_TIME_START"));
				p.getClassDetail().setBookingTimeEnd(rs.getDate("BOOKING_TIME_END"));
				p.getPayment().setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				p.getPayment().setPaymentDate(rs.getDate("PAYMENT_DATE"));
				p.getPayment().setMemberId(rs.getString("MEMBER_ID"));
				p.getPayment().setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
}
