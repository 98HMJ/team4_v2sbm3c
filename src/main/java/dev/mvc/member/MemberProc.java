package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Security;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter  {
    @Autowired
    private MemberDAOInter memberDAO;

    @Autowired
    private Security security;

    public MemberProc(){
        //System.out.println("-> MemberProc created.");
    }

    @Override
    public int create(MemberVO memberVO) {
        memberVO.setPassword(security.aesEncode(memberVO.getPassword()));
        return this.memberDAO.create(memberVO);
    }

    @Override
    public int checkid(String id) {
        return this.memberDAO.checkid(id);
    }

    @Override
    public int login(HashMap<String, Object> map) {
        map.put("password", security.aesEncode((String)map.get("password")));
        return this.memberDAO.login(map);
    }

    @Override
    public ArrayList<MemberVO> list() {
        return this.memberDAO.list();
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
    public MemberVO findpassword(HashMap<String,Object> map){
        return this.memberDAO.findpassword(map);
    }

    @Override
    public int changepassword(HashMap<String,Object> map){
        String key = security.aesEncode((String) map.get("password"));
        map.put("password", key);
        return this.memberDAO.changepassword(map);
    }
}
