package Lim.boardApp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @Lob
    private String content;

    private String title;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
