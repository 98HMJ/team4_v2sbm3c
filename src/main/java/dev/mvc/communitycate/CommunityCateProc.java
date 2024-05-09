package dev.mvc.commnuitycate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.communitycate.CommunityCateProc")
public class CommunityCateProc implements CommunityCateProcInter{
    @Autowired
    private CommunityCateDAO communityCateDAO;

    @Override
    public int update(CommunityCateVO commnuityCateVO) {
        return communityCateDAO.update(commnuityCateVO);
    }

    @Override
    public int delete(int commnuitycateno) {
        return communityCateDAO.delete(commnuitycateno);
    }

    @Override
    public ArrayList<CommunityCateVO> list() {
        return communityCateDAO.list();
    }
    
}
