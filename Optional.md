# Optional recommendations

1. **Optionalis not cost-free, it is just another object that consumes memory and needs to be collected.**
2. The purpose of Java 8 Optional is clearly defined by Brian Goetz, Java’s language architect:
**Optional is intended to provide a limited mechanism for library method return types where there needed to be a clear way to represent “no result," and using null for such was overwhelmingly likely to cause errors.**

** **
1. Never assign null to an Optional variable

2. Ensure that an optional has a value before calling Optional.get() method

3. **orElse()** When no values is present, Set/Return an Already Constructed Default Object via the Optioanl.OrElse() method.
Using theOptional.orElse()method represents an elegant alternative to theisPresent()-get()pair for setting/returning a value. The important thing here is that the parameter oforElse()is evaluated even when having a non-emptyOptional. This means that it will be evaluated even if it will not be used, which is a performance penalty. In this context, useorElse()only when the parameter (the default object) is already constructed. In other situations, rely on item 4.

4. **orElseGet()** Using theOptional.orElseGet()method represents another elegant alternative to theisPresent()-get()pair for setting/returning a value. The important thing here is that the parameter oforElseGet()is a Java 8,Supplier. This means that theSuppliermethod passed as an argument is only executed when anOptionalvalue is not present. So, this is useful to avoid theorElse()performance penalty of creating objects and executing code that we don't need when anOptionalvalue is present.

5. When no value is present, Throw a java.util.NoSuchElementException via **orElseThrow()** since Java 10. Starting with Java 10, this can be done via theorElseThrow()method without arguments.

6. Consume an optional if it is present, If it is not present, then execute an Empty-based action. This is a Job for **Optional.ifPresentElse() Java 9**.
    ```
    Optional<String> status = ... ;
    status.ifPresentOrElse(
        System.out::println, 
        () -> System.out.println("Status not found")
    );
    ```
7. When the value is present, set/return that optional. When no value is present, set/return the other optional. **Optiona.or() Java 9**
    ```
     Optional<String> defaultStatus = Optional.of("PENDING");
     return status.or(() -> defaultStatus);
    ```
    
 8. Avoid chaining Optioanl's methods with the single purpose of getting a Value... Sometimes, we tend to "over use" things for instance:
    ```
    return Optional.ofNullable(status).orElse("PENDING");
    return status == null ? "PENDING" : status;
    ```
 9. Do not declare any field of type Optional.
 Do not use Optional in methods including setters or constryctors arguments.
 Remember that Optional was not intended to be used for fields and it doesn't implement Serializable. The Optional class is definitively not intended for use as a property of a Java Bean.
 
 10. Do not use Optional in Constructor Arguments
 Do not use Optional as field or in methods arguments.
 This is another usage against Optional intention. Optional wraps objects with another level of abstraction, which, in that case, simply adds extra boilerplate code.
 
 11. Do not use Optional in setters arguments
 
 12. Do not use Optional method arguments. Don't force call sites to create Optionals. UsingOptionalin methods arguments is another common mistake.This practice clutters the code and may cause dependence.
 
 13. Do not use Optional to return empty collections or arrays.
 
 14. Avoid using Optional in Collections. Usuallym there are better ways to represent things, This approach can be a design smell. It sounds like this: so, aMapreturnsnullif there is no mapping for a key or ifnullis mapped to the key, so I cannot distinguish it if the key is not present or is a missing value. I will wrap the values viaOptional.ofNullableand done! Well, what you will do if yourMapof OptionalGoodies will be populated withnullvalues, absentOptionalvalues, or evenOptionalobjects that contains something else, but not your Goodies? Haven't you just nested the initial problem into one more layer? How about the performance penalty? **Optionalis not cost-free, it is just another object that consumes memory and needs to be collected.**
