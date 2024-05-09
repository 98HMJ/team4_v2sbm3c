package dev.mvc.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter  {
    @Autowired
    private MemberDAOInter memberDAO;

    public MemberProc(){
        //System.out.println("-> MemberProc created.");
    }

    @Override
    public int create(MemberVO memberVO) {
        return this.memberDAO.create(memberVO);
    }

    @Override
    public int checkid(String id) {
        return this.memberDAO.checkid(id);
    }

    @Override
    public int login(MemberVO memberVO) {
        return this.memberDAO.login(memberVO);
    }

    @Override
    public ArrayList<MemberVO> list(MemberVO memberVO) {
        return this.memberDAO.list(memberVO);
    }

    @Override
    public MemberVO read(int memberno) {
        return this.memberDAO.read(memberno);
    }

    @Override
    public int update(MemberVO memberVO) {
        return this.memberDAO.update(memberVO);
    }

    @Override
    public int delete(int memberno) {
        return this.memberDAO.delete(memberno);
    }
    
}
