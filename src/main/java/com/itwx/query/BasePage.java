package com.itwx.query;

import lombok.Data;

@Data
public class BasePage {

    private int pageIndex = 1;

    private int pageSize;
}
