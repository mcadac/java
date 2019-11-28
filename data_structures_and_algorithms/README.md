# Data stuctures and algorithms

## BigO Notation:

        - We need to compare an algorithm against another one... Sometimes is difficult to know about performance because it also depends on the hardware where we are running the algorithm. The hadware can influence in the time of the algorithm.

        - Look at the number of steps. -> The time complexity.
        - Check the worst case.
        - How well the algorithm scale

        - Table complexity (It's sort the best to worst):
            1. O(1)             Constants
            2. O(log n)         Logarithmic
            3. O(n)             Linear
            4. O(n * log n)     n log-start n
            5. O(n^2)           Quadratic

        - The next graph explains how bigO notation works.
            
            1. X =  Number of items will be processed by the algorithm.
            2. Y =  Number the steps of the algorithm for processing the items.

            Link: https://en.wikipedia.org/wiki/Big_O_notation#/media/File:Comparison_computational_complexity.svg
            
        - A good idea to determine the algorithm complexity is checking
        the number of loops there are.

## Data stucture:

        - Organice and store data
        - e.g an array -> organice the data sequencially -> These are well for random access
        - A developer always decide which data structure is better taking into account the application needs.

1. Arrays:
            
            - Contiguous block in memory 
                -> The system has to reserved memory
                    -> For that reason the arrays have a static length
            
            - Every element occupies the same amount of space in memory
                -> When it store objects this uses the reference to the memory and the reference to the objects have the same size.
            
            - Easily to calculate the address in memory
                
                -> x + i *y
                    - x = Start address of the array
                    - i = position element in the array
                    - y = static size of every element in the array

                -> Example:
                    -> x = 12 and y = 4
                        a. array[0] = 12
                        b. array[1] = 12 + (1+4) = 16
                        c. array[2] = 12 + (2+4) = 20
                        d. array[3] = 12 + (3+4) = 24

            - The time to retrieve elements will be the same when we know the index or position of the element.

            - Operations:

                1. Retrieve with index                          O(1)
                2. Retrieve without index                       O(n)
                3. Add an element to a full array               O(n)
                4. Add an element to the end of an array        O(1)
                5. Insert or update an element at a specific    O(1)
                6. Delete an element                            O(1)
                7. Delete an element shifting elements          O(n)   
2. test

## Algorithms:

    - Steps to perfom a specific task
    -  A stable sort algorithm preserves the relative ordering of duplicate items; whereas, an unstable sort algorithm does not 
        
1. Bubble sort:
    - This validates every index in each iteration.
    - This is O(n^2) complexity.
    - This is considerate the worst sort algorithm because of its time complexity.
    
    




