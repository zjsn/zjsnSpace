package com.zjsn.rolesystem.model.user;

public class Person {
    private final String name;
    private final int age;
    private final String schoolName;
    private final String hobby;

    private Person (String name, int age, String schoolName, String hobby) {
        this.age= age;
        this.hobby = hobby;
        this.schoolName = schoolName;
        this.name = name;
    }

    public void sayHello () {
        System.out.println("i am happy");
    }

    public String toString() {
        return "{" +this.age +"," + this.hobby + "," + this.schoolName + "," + this.name +"}";
    }

    public static class Builder {
        private String name;
        private int age;
        private String schoolName;
        private String hobby;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public Builder setSchool(String school) {
            this.schoolName = school;
            return this;
        }
        public Builder setHobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public Person build() {
            return new Person(name, age, schoolName, hobby);
        }
    }

    public static void main(String[] args) {
        Person build = new Builder("111", 2).setHobby("kkkk").setSchool("xxxxschool").build();
        build.sayHello();
        System.out.println(build);
    }
}
