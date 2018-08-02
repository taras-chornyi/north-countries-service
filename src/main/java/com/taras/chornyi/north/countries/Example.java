package com.taras.chornyi.north.countries;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
    String startsWith1(Integer d) {
        return "ssss";
    }


}

class Person {
    String firtsName;
    String lastNAme;

    public Person() {
    }

    public Person(String firtsName, String lastNAme) {
        this.firtsName = firtsName;
        this.lastNAme = lastNAme;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

public class Example {

    void method1() {
        Converter<String, Integer> converter1 = Integer::valueOf;
        Integer converted1 = converter1.convert("10");

        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted2 = converter.convert("xaxa");

        PersonFactory<Person> personFactory = Person::new;
        Person person1 = personFactory.create("John", "Smith");
    }
}
