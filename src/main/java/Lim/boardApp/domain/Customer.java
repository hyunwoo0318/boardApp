package Lim.boardApp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Customer  extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    private Integer age;

    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Text> textList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public Customer(){}
    @Builder
    public Customer(String loginId, String password, String name, Integer age, String role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public void updateTextList(List<Text> textList){
        this.textList = textList;
    }


}
