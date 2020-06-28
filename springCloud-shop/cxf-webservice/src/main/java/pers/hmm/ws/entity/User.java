package pers.hmm.ws.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: hmm
 * @Created: 2019/10/15
 * @Description:
 * @Modified by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String userName;
    private String passWord;
    private String address;
    private Integer age;
}
