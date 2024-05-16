package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

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
    public int login(HashMap<String, Object> map) {
        return this.memberDAO.login(map);
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
    public MemberVO readByid(String id) {
        return this.memberDAO.readByid(id);
    }

    @Override
    public int update(MemberVO memberVO) {
        return this.memberDAO.update(memberVO);
    }

    @Override
    public int delete(int memberno) {
        return this.memberDAO.delete(memberno);
    }
    
    @Override
    public MemberVO findid(HashMap<String,String> map){
        return this.memberDAO.findid(map);
    }

    @Override
    public MemberVO findpassword(HashMap<String,String> map){
        return this.memberDAO.findpassword(map);
    }

    @Override
    public int chagepassword(int memberno){
        return this.memberDAO.chagepassword(memberno);
    }
}
