package com.esmc.mobileMoneyCredit.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author Amemorte
 * @since 11/08/2022
 */


@Entity
@Data@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String fileType;



    public Attachment(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;

    }

    public Attachment(String fileName){
        this.fileName = fileName;
    }


}
