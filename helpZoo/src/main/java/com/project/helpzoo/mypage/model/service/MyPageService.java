package com.project.helpzoo.mypage.model.service;

import java.util.List;

import com.project.helpzoo.funding.model.vo.funding.FundingProject;
import com.project.helpzoo.member.model.vo.Member;
import com.project.helpzoo.model.vo.Donation;
import com.project.helpzoo.mypage.model.vo.mPageInfo;

public interface MyPageService {

	// public abstract (생략 가능)
	
	/** 회원 정보 수정 Service
	 * @param upMember
	 * @return result
	 */
	public abstract int updateMember(Member upMember);

	/** 회원 탈퇴 Service
	 * @param loginMember
	 * @return result
	 */
	public abstract int deleteMember(Member loginMember);

	/** 비밀번호 변경 Service
	 * @param loginMember
	 * @param newPwd1
	 * @return result
	 */
	public abstract int updatePwd(Member loginMember, String newPwd1);
	
	/** 페이징 처리를 위한 Service
	 * @param type
	 * @param cp
	 * @param loginMember
	 * @return
	 */
	public abstract mPageInfo pagination(int cp, Member loginMember);

	/** 내가 주최한 펀딩 게시글 리스트 출력 Service
	 * @param mInfo
	 * @param loginMember 
	 * @return fdListbyMe
	 */
	public abstract List<Member> selectList(mPageInfo mInfo, Member loginMember);
	
	/** 내가 주최한 펀딩 게시글 리스트 썸네일 출력 Service
	 * @param fdListbyMe
	 * @return thList
	 */
	public abstract List<Member> selectThumbnailList(List<Member> fdListbyMe);

	/** 내가 주최한 펀딩 게시글 리스트 출력 Service
	 * @param cp
	 * @param memberNo
	 * @return
	 */
	public abstract mPageInfo pagination(int cp, int memberNo);

	/** 내가 주최한 기부 게시글 리스트 출력 Service
	 * @param dInfo
	 * @param memberNo
	 * @return
	 */
	public abstract List<Donation> selectdoList(mPageInfo dInfo, int memberNo);

	/** 내가 주최한 기부 썸네일 리스트  출력 Service
	 * @param doListByme
	 * @return
	 */
	public abstract List<Donation> selectDoThumbnailList(List<Donation> doListByme);



}
