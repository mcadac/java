# Concurrency 

## Concepts 

1. **Resource utilization:** Programs sometimes have to wait for external operations such as input or output, and while waiting can do no useful work.
2. **Fairness:** Multiple users and programs may have equal claims on the machine's resources. It is prefereable to let them share the computer via finer-grained
time slicing than to let one program run 
3. **Convenience:** It is often easier or more desirable to write several programs that each perform a single task and have them coordinate with each other
as necessary than to write a single program that performs all the tasks.
