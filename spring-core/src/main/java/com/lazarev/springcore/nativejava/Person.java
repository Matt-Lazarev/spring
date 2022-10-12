package com.lazarev.springcore.nativejava;
@SuppressWarnings("all")

public class Person {
    private final Pet pet;
    private final House house;

    public Person(Pet pet, House house) {
        this.pet = pet;
        this.house = house;
    }
}

@SuppressWarnings("all")


class House{
    private final Door door;
    private final Window window;
    private final Material material;

    House(Door door, Window window, Material material) {
        this.door = door;
        this.window = window;
        this.material = material;
    }
}

class Door {}
class Pet{}
class Window{}
class Material{}

class Main{
    public static void main(String[] args) {
        Person p = new Person(
                new Pet(),
                new House(new Door(), new Window(), new Material())
        );
    }
}
