package com.xhn.auth.model;

import com.xhn.auth.model.AuthAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthAccountDTO extends AuthAccount{


    private String password;





}
