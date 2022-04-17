## (a)

The ER diagram can be represented as follows

![ER](ER.png)

Design:

1. Each answer posted by one user
2. Each question posted by one user
3. User can only like each answer for one time
4. Each answer answers one question 
5. Each question belongs to one topic
6. Each topic can only have one parent topic



The relation schema of database showed as follows:

![image-20220417022254977](schema.png)

Constraints:

1. Each user, answer, and question, topic has their own id as the primary key.
2. Each answer must reference one user and one question.
3. Each question must reference one user and one topic.
4. Each topic reference one other topic as parent.
5. Each like must reference one user and one answer, the primary key is (uid, and_id).



## (b)

Environment:

```mysql
mysql> show variables like '%storage_engine%';
+---------------------------------+-----------+
| Variable_name                   | Value     |
+---------------------------------+-----------+
| default_storage_engine          | InnoDB    |
| default_tmp_storage_engine      | InnoDB    |
| disabled_storage_engines        |           |
| internal_tmp_mem_storage_engine | TempTable |
+---------------------------------+-----------+

mysql> select version();
+-----------+
| version() |
+-----------+
| 8.0.23    |
+-----------+
```


The SQL for create the database schema can be found at:

https://github.com/1092772959/CSGY_6083_Database/blob/main/Create.sql
