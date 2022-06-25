package com.home.work.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Paging")
public class PagingDto {

    public PagingDto() {}
    public PagingDto(Integer pageSize, Integer nowPage, Integer totalCnt) {
        this.pageSize = pageSize;
        this.nowPage = nowPage;
        this.totalCnt = totalCnt;
    }

    @ApiModelProperty(notes = "한 페이지당 게시글 수")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    @ApiModelProperty(notes = "요청 페이지 번호")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer nowPage;

    @ApiModelProperty(notes = "전체 게시글 수")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalCnt;
}