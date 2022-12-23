package com.lazarev.jpa.relations.onetoone;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuppressWarnings("all")
@Entity
@Table(name = "people")
public class Person {
    @Id
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    public Person(){}

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
