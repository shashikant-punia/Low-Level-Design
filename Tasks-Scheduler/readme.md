# Design a multi-threaded task scheduler with n threads which has the capability to schedule recurring tasks or one-time tasks.

# Use Cases:

1. Job scheduled at a particular time
2. A recurring job with a particular interval of recurrence.
3. The user should be able to configure the number of worker threads
4. When there are an insufficient number of idle threads to perform the execution of all the tasks, we are going to execute the tasks with the latest execution time.


Source: https://akhiilgupta.medium.com/design-a-multi-threaded-task-scheduler-lld-multi-threaded-construct-eb090c5a8727