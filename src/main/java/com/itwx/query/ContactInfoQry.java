package com.itwx.query;

import lombok.Data;

@Data
public class ContactInfoQry extends BasePage{

    private String contactName;

    private String phone;

    private String email;
}
