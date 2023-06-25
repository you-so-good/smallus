package com.smallus.admin.service;

import static com.smallus.common.JDBCTemplate.close;
import static com.smallus.common.JDBCTemplate.commit;
import static com.smallus.common.JDBCTemplate.getConnection;
import static com.smallus.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.smallus.admin.dao.AdminDao;
import com.smallus.classes.model.vo.Classes;
import com.smallus.host.model.vo.Host;
import com.smallus.member.model.vo.Member;
import com.smallus.notice.model.vo.Notice;
import com.smallus.notice.model.vo.NoticeImage;
public class AdminService {
	
		AdminDao dao=new AdminDao();
	
	public int selectMemberCount() {
		Connection conn=getConnection();
		int totalData=dao.selectMemberCount(conn);
		close(conn);
		return totalData;
	}
	public int selectMemberSortCount(String memberSt) {
		Connection conn=getConnection();
		int totalData=dao.selectMemberSortCount(conn,memberSt);
		close(conn);
		return totalData;
	}
	public List<Member>checkMemberAll(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> list=dao.checkMemberAll(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	public List<Member>checkMemberSort(String memberSt,int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> list=dao.checkMemberSort(conn,memberSt,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int deleteByMember(String memberId) {
		Connection conn=getConnection();
		int result=dao.deleteByMember(conn,memberId);
		close(conn);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	public int deleteByHost(String hostId) {
		Connection conn=getConnection();
		int result=dao.deleteByHost(conn,hostId);
		close(conn);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int selectHostCount() {
		Connection conn=getConnection();
		int totalData=dao.selectHostCount(conn);
		close(conn);
		return totalData;
	}
	public int selectHostSortCount(String hostSt) {
		Connection conn=getConnection();
		int totalData=dao.selectHostSortCount(conn,hostSt);
		close(conn);
		return totalData;
	}
	public List<Host>checkHostAll(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Host> list=dao.checkHostAll(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	public List<Host>checkHostSort(String hostSt,int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Host> list=dao.checkHostSort(conn,hostSt,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int selectNoticeCount() {
		Connection conn=getConnection();
		int totalData=dao.selectNoticeCount(conn);
		close(conn);
		return totalData;
	}
	
	public List<Notice> checkNoticeAll(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Notice> list=dao.checkNoticeAll(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public List<NoticeImage> checkNoticeImageAll(){
		Connection conn=getConnection();
		List<NoticeImage> list2=dao.checkNoticeImageAll(conn);
		close(conn);
		return list2;
	}
	
	public int selectConfirmClassesCount() {
		Connection conn=getConnection();
		int totalData=dao.selectConfirmClassesCount(conn);
		close(conn);
		return totalData;
	}

	public List<Classes> checkConfirmClasses(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Classes> list=dao.checkConfirmClasses(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int classReject(String classPassId,String classId) {
		Connection conn=getConnection();
		int result=dao.classReject(conn,classPassId,classId);
		close(conn);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	public int classConfirm(String classId) {
		Connection conn=getConnection();
		int result=dao.classConfirm(conn,classId);
		close(conn);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
}
