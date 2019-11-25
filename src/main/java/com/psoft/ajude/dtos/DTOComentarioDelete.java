package com.psoft.ajude.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DTOComenterioDelete")
public class DTOComentarioDelete {

    @ApiModelProperty(value = "Id do comentario deletado")
    private Integer id;

    public Integer getId() {
        return id;
    }
}
