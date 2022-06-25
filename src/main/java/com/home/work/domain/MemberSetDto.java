package com.home.work.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberSetDto {
    @ApiModelProperty(notes = "아이디")
    private String id;

    @ApiModelProperty(notes = "비밀번호")
    private String password;

    @ApiModelProperty(notes = "이름")
    private String name;

    @ApiModelProperty(notes = "별명")
    private String nickName;

    @ApiModelProperty(notes = "휴대폰번호")
    private String phone;

    @ApiModelProperty(notes = "이메일")
    private String email;

    @ApiModelProperty(notes = "성별")
    private MemberGender gender; // 남, 여

    @ApiModelProperty(notes = "가입날짜")
    private LocalDateTime registerDate;

    @ApiModelProperty(notes = "요청 페이지 번호")
    private Integer nowPage;

    @ApiModelProperty(notes = "한 페이지당 게시글 수")
    private Integer pageSize;

    @ApiModelProperty(notes = "시작페이지")
    private Integer startPage;
}