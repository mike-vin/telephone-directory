package com.epam.telephonedirectory.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_phone",
            joinColumns = {@JoinColumn(name = "user_id_fk", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "telephone_id_fk", referencedColumnName = "id")})
    private Set<Telephone> phoneNumbers;
}
