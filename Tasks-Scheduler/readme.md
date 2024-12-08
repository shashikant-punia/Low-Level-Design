## Problem Statement: Design a multi-threaded task scheduler with n threads which has the capability to schedule recurring tasks or one-time tasks.

# Use Cases:

Job scheduled at a particular time
A recurring job with a particular interval of recurrence.
The user should be able to configure the number of worker threads
When there are an insufficient number of idle threads to perform the execution of all the tasks, we are going to execute the tasks with the latest execution time.