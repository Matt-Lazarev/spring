package com.lazarev.springcore.objects;

import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component("person")
public class Person {


    /* Field Injection */
//    @Autowired
//    private Cat cat;

    /* Method Injection */
//    private Cat cat;
//    @Autowired
//    public void setCat(Cat cat){
//        this.cat = cat;
//    }


    /* Constructor Injection */
//    private final Cat cat;
//    public Person(Cat cat){
//        this.cat = cat;
//    }

    /* @Qualifier */
//    private final Pet pet;
//    @Autowired
//    public Person(@Qualifier("cat") Pet pet){
//        this.pet = pet;
//    }

    private final Pet pet;
    public Person(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet(){
        return pet;
    }
}
