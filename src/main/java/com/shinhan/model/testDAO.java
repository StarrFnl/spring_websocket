package com.shinhan.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.testVO;

@Repository
public class testDAO  {
	@Autowired
	SqlSession sqlSession;
	String namespace = "net.firstzone.test.";
	
	public List<testVO> selectAll() {
		List<testVO> result = sqlSession.selectList(namespace + "selectAll");
		System.out.println(result);
		return result;
	}
}
