package com.mt.springbootmongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users_seq")
public class UserSequences implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private int seq;

    public UserSequences(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}
