package dev.mvc.community;

import java.util.ArrayList;

public interface CommunityDAOInter {

    /**
     * 글 등록
     * @param communityVO
     * @return int
     */
    public int create(CommunityVO communityVO);

    /**
     * 글 조회
     * @param communityno
     * @return CommunityVO
     */
    public CommunityVO read(int communityno);

    /**
     * 글 작성 목록
     * @return ArrayList<CommunityVO>
     */
    public ArrayList<CommunityVO> list();

    /**
     * 글 수정
     * @param communityVO
     * @return int
     */
    public int update(CommunityVO communityVO);

    /**
     * 글 삭제
     * @param communityno
     * @return int 
     */
    public int delete(int communityno);

    /**
     * 글 검색
     * @param word
     * @return ArrayList<CommunityVO>
     */
    public ArrayList<CommunityVO> search(String word);
    
}
    
