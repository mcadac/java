# Concurrency 

## Concepts 

1. **Resource utilization:** Programs sometimes have to wait for external operations such as input or output, and while waiting can do no useful work.
2. **Fairness:** Multiple users and programs may have equal claims on the machine's resources. It is prefereable to let them share the computer via finer-grained
time slicing than to let one program run 
3. **Convenience:** It is often easier or more desirable to write several programs that each perform a single task and have them coordinate with each other
as necessary than to write a single program that performs all the tasks.

## Important sentences

* Finding the right balance of sequentiality and asynchrony is often a characteristic of efficient people abd the same is true of programs.

## Threads

* **Threads** allow multiple streams of program control flow to coexist within a process. They share process-wide resources such memory and file handles, but each thread has its own program counter, stack, and local variables.

* Since threads share the memory address space of their owning process, all threads within a process have access to the same variables and allocate objects from the same heap, which allows finer-grained data sharing than inter-process mechanisms. But without explicit synch to coordinate access to share data, a thread may modify variables that another thread is in the middle of using, with unpredictable results.

* Threads can reduce development and maintenance costs and improve the performance of complex applications.
* It are useful in server applications for improving resource utilization and throughpu.
* In a multithreaded program, another thread can still run while the first thread is waiting for the I/O to complete, allowing the application to still make progress during the bloking I/O.

* Java Class libraried acquired a set of packages (java.nio) for nonblocking I/O.

### Risk of threads

#### Safety hazards
