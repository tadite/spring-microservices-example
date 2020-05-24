###tasks
- records crud api
- records stats api
- habits crud api for habits
- api gateway
- simple frontend


###decomposition
services:

1\. tracker - tracking time record for habits, giving group by
statistics:

- by day for all habits
- by day for habits list

2\. habits - catalog for all habits, crud

- pick colors for habits
- categories
- description, motivation, goals, time(start, deadline)

3\. diary/blog - notes on habits, feelings, 
information for learning habits

4\. storage - files/video/images storage

5\. users - users crud, bans,

6\. history - history of events, listening for events from other services 
through message broker, save them, give through api

7\. monitoring - technical, distributed tracing(sleuth), prometheus

8\. frontend

9\. zuul - api gateway
