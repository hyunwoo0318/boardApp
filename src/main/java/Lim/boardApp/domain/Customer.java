package Lim.boardApp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;

/* customer 테이블
    cid varchar pk,
    loginId varchar unique
    password varchar,
    cname varchar,
    age int
* */
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String cname;

    private Integer age;

    public Customer(){}
    @Builder
    public Customer(String loginId, String password, String cname, Integer age) {
        this.loginId = loginId;
        this.password = password;
        this.cname = cname;
        this.age = age;
    }
}
