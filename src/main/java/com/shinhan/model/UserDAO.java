package com.shinhan.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.UserVO;



@Repository
public class UserDAO {
	@Autowired
	SqlSession sqlSession;
	String namespace = "net.firstzone.test.";
	
	public UserVO selectUserById(int user_id) {
		UserVO result = sqlSession.selectOne(namespace + "selectUserById", user_id);
		System.out.println(result);
		return result;
	}
}


