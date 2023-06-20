package com.smallus.classes.model.service;

import static com.smallus.common.JDBCTemplate.close;
import static com.smallus.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.smallus.classes.model.dao.ClassesDao;
import com.smallus.classes.model.vo.ClassDetail;
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
		close(conn);
		return result;
	}
	
	public int selectClassCountByStatus(String hostId, String passStatus) {
		Connection conn=getConnection();
		int result=dao.selectClassCountByStatus(conn, hostId, passStatus);
		close(conn);
		return result;
	}
	
}	