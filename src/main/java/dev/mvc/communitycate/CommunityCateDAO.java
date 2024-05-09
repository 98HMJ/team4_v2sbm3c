package dev.mvc.communitycate;

import java.util.ArrayList;

public interface CommunityCateDAO {
    /**
     * 커뮤니티 카데고리 수정
     * @param commnuityCateVO
     * @return int
     */
    public int update(CommunityCateVO commnuityCateVO);

    /**
     * 커뮤니티 카테고리 삭제
     * @param commnuitycateno
     * @return int
     */
    public int delete(int commnuitycateno);

    /**
     * 커뮤니티 카테고리 목록
     * @return ArrayList<CommnuityCateVO>
     */
    public ArrayList<CommunityCateVO> list();
} 