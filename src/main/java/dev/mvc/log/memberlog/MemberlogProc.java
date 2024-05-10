package dev.mvc.log.memberlog;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("dev.mvc.log.memberlog.MemberlogProc")
public class MemberlogProc implements MemberlogProcInter {
  @Autowired
  private MemberlogDAOInter memberlogDAO;
  
  public MemberlogProc(){
    // System.out.println("-> MemberlogProc created!");
  }

  @Override
  public int create(MemberlogVO memberlogVO) {
    int cnt = this.memberlogDAO.create(memberlogVO);
    return cnt;
  }

  @Override
  public ArrayList<MemberlogVO> list() {
    ArrayList<MemberlogVO> list = this.memberlogDAO.list();
    return list;
  }

  @Override
  public MemberlogVO read(int memberlogno) {
    MemberlogVO memberlogVO = this.memberlogDAO.read(memberlogno);
    return memberlogVO;
    
  }

  @Override
  public ArrayList<MemberlogVO> list_memberno(int memberno) {
    ArrayList<MemberlogVO> list_memberno = this.memberlogDAO.list_memberno(memberno);
    return list_memberno;
  }
}
