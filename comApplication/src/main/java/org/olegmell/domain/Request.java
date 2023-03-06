package org.olegmell.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_iq")
    private User username;
    private String filename;

    public String getUsername(){
        return username != null ? username.getUsername() : "<пусто>";
    }
    public Request() {
    }

    public Request(String text, String tag, User user) {
        this.username = user;
        this.text = text;
        this.tag = tag;
    }

}
