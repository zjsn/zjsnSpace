package com.zjsn.domain.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("门票实体类")
public class Ticket {
    @ApiModelProperty("门票主键id")
    private Long id;
    @ApiModelProperty("销售人员的主键")
    private Long salesId;


    public Ticket(Long id, Long salesId) {
        this.id = id;
        this.salesId = salesId;
    }
}
