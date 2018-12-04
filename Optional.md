# Optional recommendations

1. Never assign null to an Optional variable

2. Ensure that an optional has a value before calling Optional.get() method

3. **orElse()** When no values is present, Set/Return an Already Constructed Default Object via the Optioanl.OrElse() method.
Using theOptional.orElse()method represents an elegant alternative to theisPresent()-get()pair for setting/returning a value. The important thing here is that the parameter oforElse()is evaluated even when having a non-emptyOptional. This means that it will be evaluated even if it will not be used, which is a performance penalty. In this context, useorElse()only when the parameter (the default object) is already constructed. In other situations, rely on item 4.

4. **orElseGet()** Using theOptional.orElseGet()method represents another elegant alternative to theisPresent()-get()pair for setting/returning a value. The important thing here is that the parameter oforElseGet()is a Java 8,Supplier. This means that theSuppliermethod passed as an argument is only executed when anOptionalvalue is not present. So, this is useful to avoid theorElse()performance penalty of creating objects and executing code that we don't need when anOptionalvalue is present.

5. When no value is present, Throw a java.util.NoSuchElementException via **orElseThrow()** since Java 10. Starting with Java 10, this can be done via theorElseThrow()method without arguments
