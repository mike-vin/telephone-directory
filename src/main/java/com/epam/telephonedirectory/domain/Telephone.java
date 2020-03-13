package com.epam.telephonedirectory.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
