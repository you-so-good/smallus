package com.smallus.member.dao;

import static com.smallus.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.smallus.classes.model.vo.ClassDetail;
import com.smallus.classes.model.vo.Classes;
import com.smallus.common.JDBCTemplate;
import com.smallus.member.model.vo.Member;
import com.smallus.member.model.vo.Notifications;
import com.smallus.payment.model.vo.Payment;

public class MemberDao {
	private Properties sql = new Properties();// final로 선언하면 처리속도 빨라짐

	{
		String path = JDBCTemplate.class.getResource("/sql/member.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member memberLogin(Connection conn, String memberId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("memberLogin"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = getMember(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public int enrollMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("enrollMember"));
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setString(5, m.getMemberEmail());
			pstmt.setString(6, m.getMemberConsent());
			pstmt.setString(7, m.getMemberNickname());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member selectByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectByMemberId"));
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = getMember(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return m;
	}

	public Member selectBymemberNickname(Connection conn, String memberNickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBymemberNickname"));
			pstmt.setString(1, memberNickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = getMember(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return m;
	}

	public int updatePassword(Connection conn, String userId, String password) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updatePassword"));
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int KakaoenrollMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("KakaoenrollMember"));
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setString(5, m.getMemberEmail());
			pstmt.setString(6, m.getMemberNickname());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member kakaoLogin(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("kakaoLogin"));
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = getMember(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return m;

	}

	public Member selectBynickName(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBynickName"));
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = (getMember(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return m;
	}

	public int updateMember(Connection conn, Member m, String s) {
		PreparedStatement pstmt = null;

		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateMember"));

			pstmt.setString(1, m.getMemberNickname());
			pstmt.setString(2, m.getMemberImg());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setString(4, s);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteByMember(Connection conn, String memberId, String password) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteByMember"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Member> paymentDetails(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("paymentDetails"));
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Classes c = new Classes();
				Payment p = new Payment();
				ClassDetail d = new ClassDetail();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				p.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				p.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				c.setClassTitle(rs.getString("CLASS_TITLE"));
				c.setClassThumbnail(rs.getString("CLASS_THUMBNAIL"));
				p.setClassPersonnel(rs.getInt("CLASS_PERSONNEL"));
				d.setBookingTimeStart1(rs.getTimestamp("BOOKING_TIME_START").toLocalDateTime().format(formatter));
				d.setBookingTimeEnd1(rs.getTimestamp("BOOKING_TIME_END").toLocalDateTime().format(formatter));
				c.setClassId(rs.getString("CLASS_ID"));
				Member m = new Member();
				m.setClasses(c);
				m.setPayment(p);
				m.setClassDetail(d);
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<Classes> wishList(Connection conn, String memberId, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Classes> list = new ArrayList<Classes>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("wishList"));
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(3, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getClass(rs));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int wishRemove(Connection conn, String memberId, String title) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("wishRemove"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, title);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int wishAdd(Connection conn, String memberId, String title) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("wishAdd"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, title);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int wishListCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("wishListCount"));
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public List<Notifications> selectAllNotifications(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notifications> list = new ArrayList<Notifications>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllNotifications"));
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getNotifications(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int notificationsCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("notificationsCount"));

			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int notifications(Connection conn, String notId) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql.getProperty("notifications"));
			pstmt.setString(1, notId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int reviewCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("reviewCount"));
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public static Member getMember(ResultSet rs) throws SQLException {
		return Member.builder().memberId(rs.getString("MEMBER_ID")).memberPw(rs.getString("MEMBER_PW"))
				.memberName(rs.getString("MEMBER_NAME")).memberPhone(rs.getString("MEMBER_PHONE"))
				.memberEmail(rs.getString("MEMBER_EMAIL")).memberConsent(rs.getString("MEMBER_CONSENT"))
				.memberImg(rs.getString("MEMBER_IMG")).memberNickname(rs.getString("MEMBER_NICKNAME"))
				.memberSt(rs.getString("MEMBER_ST")).build();
	}

	public static Notifications getNotifications(ResultSet rs) throws SQLException {
		return Notifications.builder().notiflId(rs.getString("NOTIFL_ID")).hostId(rs.getString("HOST_ID"))
				.memberId(rs.getString("MEMBER_ID")).notiflMessage(rs.getString("NOTIFL_MESSAGE"))
				.createdAt(rs.getDate("CREATED_AT")).notiflType(rs.getString("NOTIFL_TYPE")).build();
	}

	public Classes getClass(ResultSet rs) throws SQLException {
		return Classes.builder().classId(rs.getString("CLASS_ID")).categoryTitle(rs.getString("CATEGORY_TITLE"))
				.classTitle(rs.getString("CLASS_TITLE")).classThumbnail(rs.getString("CLASS_THUMBNAIL"))
				.classAddress(rs.getString("ADDRESS")).build();

	}
}
