package com.home.work.mapper;

import com.home.work.domain.Member;
import com.home.work.domain.MemberGetDto;
import com.home.work.domain.MemberSetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper {
    List<MemberGetDto> getUserGroupList(MemberSetDto member);
    List<Member> getMemberDetail(String memberId);
    Integer getUserTotalCount();
}
