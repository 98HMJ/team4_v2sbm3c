package dev.mvc.rereply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.rereply.RereplyProc")
public class RereplyProc implements RereplyProcInter {

    @Autowired
    private RereplyDAOInter rereplyDAO;

    @Override
    public int create(RereplyVO rereplyVO) {
        return rereplyDAO.create(rereplyVO);
    }

    @Override
    public ArrayList<RereplyVO> list_by_rereply(int replyno) {
        return rereplyDAO.list_by_rereply(replyno);
    }

    @Override
    public RereplyVO read(int rereplyno) {
        return rereplyDAO.read(rereplyno);
    }

    @Override
    public int update_contents(RereplyVO rereplyVO) {
        return rereplyDAO.update_contents(rereplyVO);
    }

    @Override
    public int update_file(RereplyVO rereplyVO) {
        return rereplyDAO.update_file(rereplyVO);
    }

    @Override
    public int delete_rereply(int rereplyno) {
        return rereplyDAO.delete_rereply(rereplyno);
    }

    @Override
    public int count_by_rereplyno(int replyno) {
        return rereplyDAO.count_by_rereplyno(replyno);
    }

    @Override
    public int update_increase_cnt_like(int rereplyno) {
       return rereplyDAO.update_increase_cnt_like(rereplyno);
    }
    
}
