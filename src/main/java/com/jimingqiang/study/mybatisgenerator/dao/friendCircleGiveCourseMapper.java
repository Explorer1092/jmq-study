package com.jimingqiang.study.mybatisgenerator.dao;

import com.jimingqiang.study.mybatisgenerator.entity.friendCircleGiveCourse;

public interface friendCircleGiveCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(friendCircleGiveCourse record);

    int insertSelective(friendCircleGiveCourse record);

    friendCircleGiveCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(friendCircleGiveCourse record);

    int updateByPrimaryKey(friendCircleGiveCourse record);
}