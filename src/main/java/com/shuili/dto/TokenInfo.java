package com.shuili.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenInfo {

    String userName;
    String token;

}
