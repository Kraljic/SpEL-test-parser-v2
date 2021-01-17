# SpEL text parser

**SpEL text parse** is based on **Spring Expression Language** (SpEL)<br/>
It is used to create texts from templates.<br/>

Text example:
```
Hello ${name}, how are you?
```

Class example for text above:
```java
class Person { 
    String name;
    Person(String name) { ... }
    String getName() { ... }
}
```

Usage:
```java
String text = "Hello ${name}, how are you?"
Person context = new Person("Mark");

SpelParser spelParser = createSpelParser();
String out = spelParser.parse(text, context);
```
`out` wil contain: *`'Hello Mark, how are you?'`*

## Context switching
`%%context::field%%` syntax allows switching context. It is possible to switch context to inner field or array.<br/>
***Note**: if field is **array** it will iterate the array for every element and form a string from every element.

Text example:
 ```
${name} has ${dogs.size()} dogs.
They are: 
%%context::dogs%%  - ${name}
%%context_end::dogs%%
```
***Note**: It captures everything between `%%context%%` tags including new line.

Class structure:
```java
class Person {
    String name;
    Dog[] dogs; // Can be also any other type of Iterable
    
    // constuctor and accessors methods ommited..
}
class Dog {
    String name;

    // constuctor and accessors methods ommited..
}
```

Usage:
```java
String text = "...";

Dog timmy = new Dog("Timmy");
Dog max = new Dog("Max");

Person person = new Person();
person.setName("Mark");
peronn.setDogs(new Dog[] {timmy, max});

SpelParser spelParser = createSpelParser();
String out = spelParser.parse(text, person);
```
`out` wil contain: 
```
Mark has 2 dogs.
  - Timmy
  - Max
```

## TODO

- [ ] Add iterator index to array context
- [ ] Add support for custom method definition inside SpEL
- [ ] Write JavaDoc
