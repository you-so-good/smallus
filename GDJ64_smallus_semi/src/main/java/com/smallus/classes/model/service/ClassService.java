package com.smallus.classes.model.service;

import static com.smallus.common.JDBCTemplate.close;
import static com.smallus.common.JDBCTemplate.commit;
import static com.smallus.common.JDBCTemplate.getConnection;
import static com.smallus.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.smallus.classes.model.dao.ClassesDao;
import com.smallus.classes.model.vo.ClassDetail;
import com.smallus.classes.model.vo.ClassIndex;
import com.smallus.classes.model.vo.Classes;

public class ClassService {
	
	private ClassesDao dao = new ClassesDao();
	
	public List<ClassDetail> selectClassDetailByClassId(String classId){
		Connection conn= getConnection();
		List<ClassDetail> list=dao.selectClassDetailByClassId(conn,classId);
		close(conn);
		return list;
	}
	
	public List<Classes> selectAllClassesByHostId(String hostId, int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Classes> list =dao.selectAllClassesByHostId(conn,hostId,cPage,numPerpage);
		close(conn);
		if(list.size()==1) {
			System.out.println("service-list");			
		}
		return list;
	}
	
	public List<Classes> selectClassListByPassStatus(String hostId, String passStatus,int cPage, int numPerpage){
		Connection conn= getConnection();
		List<Classes> list=dao.selectClassListByPassStatus(conn, hostId, passStatus,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public List<Classes> selectClassesByHostId(String hostId){
		Connection conn= getConnection();
		List<Classes> list=dao.selectClassesByHostId(conn,hostId);
		close(conn);
		return list;
	}
	
	public int selectClassCount(String hostId) {
		Connection conn=getConnection();
		int result=dao.selectClassCount(conn, hostId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int selectClassCountByStatus(String hostId, String passStatus) {
		Connection conn=getConnection();
		int result=dao.selectClassCountByStatus(conn, hostId, passStatus);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Classes> selectClassByCalendar(String hostId){
		Connection conn= getConnection();
		List<Classes> list=dao.selectClassesByHostId(conn,hostId);
		close(conn);
		return list;
	}
	
	public Classes selectClassByClassId(String hostId, String classId) {
		Connection conn= getConnection();
		Classes list=dao.selectClassByClassId(conn, hostId, classId);
		close(conn);
		return list;
	}
	
	
	public String selectClassIdByclassTitle(String classTitle) {
		Connection conn= getConnection();
		String result=dao.selectClassIdByclassTitle(conn,  classTitle);
		close(conn);
		return result;
	}
	
	public int updateRemainPersonnel(int updateRemainPersonnel, String classDetailId) {
		Connection conn=getConnection();
		int result=dao.updateRemainPersonnel(conn, updateRemainPersonnel,classDetailId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<ClassIndex> newClassList(){
		Connection conn= getConnection();
		List<ClassIndex> list=dao.newClassList(conn);
		close(conn);
		return list;
	}
	
	public int updatedClassStatusByClassId(String classId) {
		Connection conn=getConnection();
		int result=dao.updatedClassStatusByClassId(conn, classId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteClassDetailBydetaiLid(String classDetailId) {
		Connection conn=getConnection();
		int result=dao.deleteClassDetailBydetaiLid(conn, classDetailId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int InsertClassDetailByHostPage(List<ClassDetail> list ) {
		Connection conn=getConnection();
		int result=dao.InsertClassDetailByHostPage(conn, list);
		if(result>0) {
			commit(conn);
			System.out.println("insert o");
		}else {
			rollback(conn);
			System.out.println("insert x");
		}
		close(conn);
		return result;
		
	}
	
}	
