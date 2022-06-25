package com.home.work.service.impl;

import com.home.work.domain.*;
import com.home.work.mapper.UserGroupMapper;
import com.home.work.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public List<Member> getMemberDetail(String memberId) {
        List<Member> result = userGroupMapper.getMemberDetail(memberId);
        return result;
    }

    @Override
    public UserGroup getUserGroupList(MemberSetDto memberSetDto) {

        if(memberSetDto.getNowPage() != null) {
            Integer page = (memberSetDto.getNowPage() - 1 ) * memberSetDto.getPageSize();
            memberSetDto.setStartPage(page);
        }

        List<MemberGetDto> userList = userGroupMapper.getUserGroupList(memberSetDto);

        //total rows
        Integer totalCount = userGroupMapper.getUserTotalCount();
        System.out.println(memberSetDto.getName()+":"+memberSetDto.getEmail()+":"+memberSetDto.getNowPage());
        System.out.println(userList);

        UserGroup userGroup = new UserGroup();
        userGroup.setUserList(userList);
        userGroup.setPagination(new PagingDto(memberSetDto.getPageSize(), memberSetDto.getNowPage(), totalCount));
        return userGroup;
    }
}

