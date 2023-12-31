package com.smallus.main.service;

import static com.smallus.common.JDBCTemplate.close;
import static com.smallus.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.smallus.classes.model.vo.ClassIndex;
import com.smallus.classes.model.vo.Classes;
import com.smallus.main.dao.MainDao;
import com.smallus.main.model.vo.Wish;

public class MainService {
	private MainDao dao = new MainDao();

	public List<Classes> searchCategories(String search, int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<Classes> list = dao.searchCategories(conn, search, cPage, numPerpage);
		close(conn);
		return list;
	}

	public int searchCategoriesCount(String search) {
		Connection conn = getConnection();
		int totalData = dao.searchCategoriesCount(conn, search);
		close(conn);
		return totalData;
	}

	public List<ClassIndex> NewClassList() {
		Connection conn = getConnection();
		List<ClassIndex> list = dao.NewClassList(conn);
		close(conn);
		return list;
	}

	public List<ClassIndex> wishClassList() {
		Connection conn = getConnection();
		List<ClassIndex> list = dao.wishClassList(conn);
		close(conn);
		return list;
	}

	public List<ClassIndex> selectNewClassByCategory(String categoryId) {
		Connection conn = getConnection();
		List<ClassIndex> list = dao.selectNewClassByCategory(conn, categoryId);
		close(conn);
		return list;
	}

	public List<ClassIndex> selectBestClassByCategory(String categoryTitle) {
		Connection conn = getConnection();
		List<ClassIndex> list = dao.selectBestClassByCategory(conn, categoryTitle);
		close(conn);
		return list;
	}

	public List<Wish> wishMember(String memberId) {
		Connection conn = getConnection();
		List<Wish> list = dao.wishMember(conn, memberId);
		close(conn);
		return list;
	}
	
	public int allClassByCategoryCount(String categoryId) {
		Connection conn = getConnection();
		int totalData = dao.allClassByCategoryCount(conn, categoryId);
		close(conn);
		return totalData;
	}
	
	
	public List<ClassIndex> selectAllclassByCategory(String categoryId, int cPage, int numPerpage) {
		Connection conn = getConnection();
		List<ClassIndex> list = dao.selectAllclassByCategory(conn, categoryId,cPage, numPerpage);
		close(conn);
		return list;
	}
	
}
