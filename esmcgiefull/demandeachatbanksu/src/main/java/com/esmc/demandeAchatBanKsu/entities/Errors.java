package com.esmc.demandeAchatBanKsu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Errors {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String message;
    @Column(length = 100000)
    private  String throwable;
    private  HttpStatus httpStatus;
    private  ZonedDateTime zonedDateTime;

    public Errors(String message, String throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }
}
