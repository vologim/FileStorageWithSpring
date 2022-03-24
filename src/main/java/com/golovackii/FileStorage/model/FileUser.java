
package com.golovackii.FileStorage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "file")
@NoArgsConstructor
@Getter
@Setter
public class FileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "file_location")
    private String fileLocation;   

    public FileUser(String fileName, String fileLocation) {
        this.fileName = fileName;
        this.fileLocation = fileLocation;
    }

}
