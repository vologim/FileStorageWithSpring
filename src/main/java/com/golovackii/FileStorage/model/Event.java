
package com.golovackii.FileStorage.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "log")
    private String log;
    
    @Autowired
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
        
    @Autowired
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileUser fileUser;

    public Event(String log, User user, FileUser fileUser) {
        this.log = log;
        this.user = user;
        this.fileUser = fileUser;
    }

}
