package com.lazarev.hibernate.relations.onetoone;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import static jakarta.persistence.GenerationType.*;
@Entity
@Table(name = "passports")
public class Passport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer number;
    private Integer series;

    @OneToOne(mappedBy = "passport",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @LazyToOne(value = LazyToOneOption.PROXY)
    private Person person;

    public Passport(){}

    public Passport(Integer id, Integer number, Integer series) {
        this.id = id;
        this.number = number;
        this.series = series;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        person.setPassport(this);
        this.person = person;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number=" + number +
                ", series=" + series +
                '}';
    }
}
