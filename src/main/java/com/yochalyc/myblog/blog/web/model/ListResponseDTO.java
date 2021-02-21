package com.yochalyc.myblog.blog.web.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
abstract class ListResponseDTO<T> {

    protected Integer page;

    protected Integer pageSize;

    protected Integer count;

    protected List<T> list;

}
