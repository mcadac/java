# Optional recommendations

1. Never assign null to an Optional variable
2. Ensure that an optional has a value before calling Optional.get() method
3. When no values is present, Set/Return an Already Constructed Default Object via the Optioanl.OrElse() method.
  Using theOptional.orElse()method represents an elegant alternative to theisPresent()-get()pair for setting/returning a value. The important thing here is that the parameter oforElse()is evaluated even when having a non-emptyOptional. This means that it will be evaluated even if it will not be used, which is a performance penalty. In this context, useorElse()only when the parameter (the default object) is already constructed. In other situations, rely on item 4.
