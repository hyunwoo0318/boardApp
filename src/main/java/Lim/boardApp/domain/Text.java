package Lim.boardApp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Text {
    @Id
    @GeneratedValue
    Long tid;

    @Lob
    String content;

    String title;

    @Column(name = "CREATED_DATE")
    Date createdDate;

    //getter & setter
    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
