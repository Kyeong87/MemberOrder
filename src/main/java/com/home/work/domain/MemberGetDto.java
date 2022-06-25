package com.home.work.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MemberGetDto {
    @ApiModelProperty(notes = "아이디")
    private String id;

    @ApiModelProperty(notes = "이름")
    private String memberName;

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

    @ApiModelProperty(notes = "주문번호")
    private String orderNumber;

    @ApiModelProperty(notes = "제품명")
    private String orderName;

    @ApiModelProperty(notes = "주문날짜")
    private LocalDateTime orderRegisterDate;
}
