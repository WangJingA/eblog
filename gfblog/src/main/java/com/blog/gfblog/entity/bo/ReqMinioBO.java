package com.blog.gfblog.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class ReqMinioBO {

    private String name;

    private List<String> list;
}
