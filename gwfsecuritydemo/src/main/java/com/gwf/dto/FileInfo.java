package com.gwf.dto;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/10/9.
 */
@Data
public class FileInfo {

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;
}
