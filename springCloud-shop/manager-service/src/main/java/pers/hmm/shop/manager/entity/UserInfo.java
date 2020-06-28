package pers.hmm.shop.manager.entity;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class UserInfo {
    private Long userId;
    private String username;
    private String password;
    private List<Role> roles;
}
