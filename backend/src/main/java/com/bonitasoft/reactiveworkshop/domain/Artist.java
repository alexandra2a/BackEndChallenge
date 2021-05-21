package com.bonitasoft.reactiveworkshop.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;

//@Document
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artist {

    @Id
    private String id;
    private String name;
    private String genre;

    @Transient
    @ElementCollection
    @Builder.Default
    private Comment[] comments = null;
    
    
    public Comment[] getComments() {
        return comments;
    }
    
    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

}
