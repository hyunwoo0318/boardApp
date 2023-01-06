package Lim.boardApp.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private Date createdDate;
}
