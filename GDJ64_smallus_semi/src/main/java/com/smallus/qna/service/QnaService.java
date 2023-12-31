package com.smallus.qna.service;

import java.sql.Connection;
import java.util.List;

import com.smallus.qna.model.dao.QnaDao;
import com.smallus.qna.model.vo.Qna;

import static com.smallus.common.JDBCTemplate.*;

public class QnaService{
	
	private QnaDao dao = new QnaDao();
	
	public List<Qna> detailQnaList(){
		Connection conn = getConnection();
		List<Qna> list = dao.detailQnaList(conn);
		close(conn);
		return list;
	}
	
}
