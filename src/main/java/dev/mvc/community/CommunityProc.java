package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.community.CommunityProc")
public class CommunityProc implements CommunityProcInter {
    @Autowired
    private CommunityDAOInter communityDAO;

    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;

    /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
    public static int PAGE_PER_BLOCK = 10;

    public CommunityProc() {
        // System.out.println("-> CommunityProc created.");
    }

    @Override
    public int create(CommunityVO communityVO) {
        return this.communityDAO.create(communityVO);
    }

    @Override
    public CommunityVO read(int communityno) {
        return this.communityDAO.read(communityno);
    }

    @Override
    public ArrayList<CommunityVO> list() {
        return this.communityDAO.list();
    }

    @Override
    public int update(CommunityVO communityVO) {
        return this.communityDAO.update(communityVO);
    }

    @Override
    public int delete(int communityno) {
        return this.communityDAO.delete(communityno);
    }

    @Override
    public ArrayList<CommunityVO> search(String word) {
        return this.communityDAO.search(word);
    }
    
}
