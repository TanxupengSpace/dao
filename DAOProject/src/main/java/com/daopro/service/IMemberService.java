package com.daopro.service;

import com.daopro.vo.Member;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMemberService {
    /**
     * 数据增加
     * @param vo
     * @return
     */
    boolean add(Member vo);

    /**
     * 数据修改
     * @param vo
     * @return
     */
    boolean edit(Member vo);

    /**
     * 根据ID删除数据
     * @param ids
     * @return
     */
    boolean removeById(Set<String>ids);

    /**
     * 根据id修改数据
     * @param id
     * @return
     */
    Member get(String id);

    /**
     * 查询全部数据
     * @return
     */
    List<Member> list();

    /**
     * 分页模糊查询
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     */
    Map<String, Object> split(int currentPage, int lineSize, String column, String keyWord);
}
