package com.home.work.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserGroup {

    @ApiModelProperty(value = "검색 데이터 리스트")
    private List<MemberGetDto> userList;

    @ApiModelProperty(value = "리스트 페이징")
    private PagingDto Pagination;
}