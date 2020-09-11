package com.project.helpzoo.mypage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.helpzoo.member.model.vo.Member;
import com.project.helpzoo.mypage.model.service.MyPageService;

@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("/mypage/*")
public class MyPageController {
	
	@Autowired
	private MyPageService mypageService;
	
	// 마이 페이지 메인(회원 정보 수정)으로 이동하는 Controller
	@RequestMapping("updateMember")
	public String myPage() {
		return "mypage/updateMember";
	}
	
	// 비밀 번호 변경으로 이동하는 Controller
	@RequestMapping("changePwd")
	public String changePwd() {
		return "mypage/changePwd";
	}
	
	// 회원 탈퇴 페이지로 이동하는 Controller
	@RequestMapping("secession")
	public String secession() {
		return "mypage/secession";
	}
	// 회원 탈퇴 완료 페이지로 이동
	@RequestMapping("secessionComplete")
	public String secessionSuccess() {
		return "mypage/secessionSuccess";
	}
	
	// 회원 정보 수정
	@RequestMapping("updateAction")
	public String updateAction(Member upMember, Model model, RedirectAttributes rdAttr, HttpServletRequest request) {
		
		// session scope에 있는 로그인 회원 정보를 얻어와 id, name, grade를 추출해서 upMember에 세팅한다.
		// model은 object 타입이므로 형변환이 필요하다.
		Member loginMember = (Member)model.getAttribute("loginMember");
		upMember.setMemberNo(loginMember.getMemberNo());
		upMember.setMemberEnrollDate(loginMember.getMemberEnrollDate());
		upMember.setMemberId(loginMember.getMemberId());
		upMember.setMemberName(loginMember.getMemberName());
		upMember.setMemberGrade(loginMember.getMemberGrade());
		
		int result = mypageService.updateMember(upMember);
		
		String status = null;
		String msg = null;
		String text = null;
		
		if(result >0) {
			status = "success";
			msg = "회원 정보 수정 성공";
			text = "회원 정보가 수정되었습니다.";
			
			// model에 추가된 요소 중 session에 등록된 loginMember의 값을 upMember로 바꾼다.
			model.addAttribute("loginMember", upMember);
		}else {
			status = "error";
			msg = "회원 정보 수정 실패";
			text = "회원 정보 수정에 실패했습니다.";
		}
		rdAttr.addFlashAttribute("status", status);
		rdAttr.addFlashAttribute("msg", msg);
		rdAttr.addFlashAttribute("text", text);
		
		// return "redirect:mypage"
		return "redirect:" + request.getHeader("referer");
 	}
	
	// 회원 탈퇴
	@RequestMapping("deleteMember")
	public String deleteMember(String memberPwd, Model model, RedirectAttributes rdAttr,
			SessionStatus SessionStatus) {
		
		Member loginMember = (Member)model.getAttribute("loginMember");
		loginMember.setMemberPwd(memberPwd);
		
		int result = mypageService.deleteMember(loginMember);
		
		String status = null;
		String msg = null;
		String text = null;
		String path = null;
		
		if(result >0) {
			status = "success";
			msg = "회원 탈퇴 성공";
			SessionStatus.setComplete();
			path = "redirect:secessionComplete";
		}else {
			status = "error";
			msg = "회원 탈퇴 실패";
			text = "올바른 비밀번호가 입력되었는지 확인해주세요.";
			path = "redirect:secession";

		}
		rdAttr.addFlashAttribute("status", status);
		rdAttr.addFlashAttribute("msg", msg);
		rdAttr.addFlashAttribute("text", text);
		
		return path;
	}
	
}