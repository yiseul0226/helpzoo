package com.project.helpzoo.admin.model.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.helpzoo.model.vo.Donation2;

@Repository
public class ADonationDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Donation2> selectList() {
		return sqlSession.selectList("adminMapper.selectDonation",null);
	}

	public int deleteDonation(String donationNo) {
		return sqlSession.update("adminMapper.deleteDonation", donationNo);
	} 

}
