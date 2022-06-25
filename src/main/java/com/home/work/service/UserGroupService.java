package com.home.work.service;

import com.home.work.domain.Member;
import com.home.work.domain.MemberSetDto;
import com.home.work.domain.UserGroup;

import java.util.List;

public interface UserGroupService {
    UserGroup getUserGroupList(MemberSetDto memberSetDto);
    List<Member> getMemberDetail(String memberId);
}
