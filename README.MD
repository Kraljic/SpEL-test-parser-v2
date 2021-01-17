# SpEL text parser

**SpEL text parse** is based on **Spring Expression Language** (SpEL)<br/>
It is used to create texts from templates.<br/>

Text example:
```
Hello ${name}, how are you?
```

Class example for text above:
```
class Person { 
    String name;
    Person(String name) { ... }
    String getName() { ... }
}
```

Usage:
```
String text = "Hello ${name}, how are you?"
Person context = new Person("Mark");

SpelParser spelParser = createSpelParser();
String out = spelParser.parse(text, context);
// out -> 'Hello Mark, how are you?'
```