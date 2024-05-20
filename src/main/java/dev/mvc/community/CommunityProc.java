package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.community.CommunityProc")
public class CommunityProc implements CommunityProcInter {
    @Autowired
    private CommunityDAOInter communityDAO;

    public CommunityProc() {
        System.out.println("-> CommunityProc created.");
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
