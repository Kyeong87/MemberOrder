package com.home.work.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id")
    private Integer id;

    @ApiModelProperty(notes = "주문번호")
    private String orderNumber;

    @ApiModelProperty(notes = "제품명")
    private String name;

    @ApiModelProperty(notes = "회원아이디")
    private String memberId;

    @ApiModelProperty(notes = "주문날짜")
    private LocalDateTime registerDate;
}