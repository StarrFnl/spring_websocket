package com.shinhan.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.OwnerVO;

@Repository
public class OwnerDAO {
	@Autowired
	SqlSession sqlSession;
	String namespace = "net.firstzone.test.";
	
	public OwnerVO selectOwnerById(int owner_id) {
		OwnerVO owner = sqlSession.selectOne(namespace + "selectOwnerById", owner_id);
		return owner;
	}
	
	public int updateOwnerPathById(int owner_id, String owner_path) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner_id", owner_id);
		map.put("owner_path", owner_path);
		int result = sqlSession.update(namespace+"updateOwnerPathById", map);
		return result;
	}
}
