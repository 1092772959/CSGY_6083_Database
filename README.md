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



Assumptions:

1. There are multi-level(equal to or more than 2) hierachies of topics
2. The level of each user will not be updated immediately after he/she posts a new answer



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

#### 

## (c)

#### (1)

```mysql
-- q1
insert into User (username, email, password, city, state, country, profile)
values ("Jaccob James", "corinna8@euneeedn.com", '123456', 'New York City', "NY",
	"United States", "This person is lazy.");
```

#### (2)

```mysql
-- 2) insert the question
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 5, NOW(), 'Shift 2D Grid', 
'Given a 2D grid of size m x n and an integer k. You need to shift the grid k times. Return the 2D grid after applying shift operation k times.');
```

#### (3)

Justification: 

```mysql
update `User` u
left join (
select uid, count(a.ans_id) as answer_cnt
from `Answers` a 
group by uid
) tmp on tmp.uid = u.uid
set u.level = CASE
			WHEN answer_cnt is NULL OR answer_cnt < 3 THEN 'basic'
            WHEN answer_cnt >= 3 AND answer_cnt < 5 THEN 'advanced'
            WHEN answer_cnt >= 5 THEN 'expert'
            END;
```



#### (4)

```mysql
-- suppose we want to get all the answers of question 3
select q.ques_id, a.ans_id, a.ans_body, a.date, a.isBest
from Questions q
         join Answers a on q.ques_id = a.ques_id
where q.ques_id = 3
order by a.date;
```



#### (5)

Jusitication:  For both upper and lower level hierarchy topics, we calculate the number of questions and answers. For upper level one, we use recursive query to get all the lower level topics under it. The temprary relation `topic_group` records each topic and all the topics under it. Also, we have to add itself into this relation. Then use join and group by to calculate the number of questions under each topic.

```mysql
with recursive topic_group(tid, sub_tid) as (
	select parent_id, topic_id
    from Topic
    where parent_id is not NULL
    union all
    select tg.tid, t.topic_id
    from Topic t
    join topic_group tg on t.parent_id = tg.sub_tid
    where parent_id is not NULL
)
select total.tid, COUNT(DISTINCT q.ques_id) as QuestionNumber
, COUNT(a.ans_id) as AnswerNumber
from (select * from topic_group tg union select topic_id, topic_id from topic) total
left join Questions q on total.sub_tid = q.topic_id
left join Answers a on q.ques_id = a.ques_id
group by total.tid;
```



#### (6)

Jusitication: We utilize [fulltext search](https://dev.mysql.com/doc/refman/8.0/en/fulltext-boolean.html) for this question. Its relevance ranking is based on the BM25 and TF-IDF. Besides, we add different weights to title, question body and answers to better reflect the relevance. We believe the questions' scores are more important than those of answers. The coefficients are 5.0, 3.0 and 1.0 respectively.

```mysql
select q.ques_id, q.title, sum(score) as score_sum
from (
    select ques_id, 5.0 * MATCH(title)
                AGAINST ('indexes MySQL' IN BOOLEAN MODE) as score
    from Questions
    where topic_id = 4
 union all
    select ques_id, 3.0 * MATCH(ques_body)
                AGAINST ('indexes MySQL' IN BOOLEAN MODE) as score
    from Questions
    where topic_id = 4
 union all
    select a.ques_id, 1.0 * SUM(MATCH(ans_body)
                AGAINST ('indexes MySQL' IN BOOLEAN MODE)) as score
    from Answers a 
    join Questions Q on a.ques_id = Q.ques_id
    where q.topic_id = 4
    group by a.ques_id
) tmp
join Questions q on q.ques_id = tmp.ques_id
group by tmp.ques_id
order by score_sum desc;
```



## (d)

#### Test Results

##### (1)

![image-20220417191216811](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417191216811.png)

##### (2)

![image-20220417193629923](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417193629923.png)

##### (3)

![image-20220417192827261](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417192827261.png)

Clarification: As the result shows, Jaccob James is still basic user because he answered zero question, while Alice Walker and Wade Cole have been updated to adanced and expert respectively because they have answered 5 and 3 questions.

<img src="/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417193909275.png" alt="image-20220417193909275" style="zoom:67%;" />

<figure>
  <figcaption align="center"><b>User-Answers Relation</b></figcaption>
</figure>

##### (4)

![image-20220417194908033](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417194908033.png)

<figure>
  <figcaption align="center"><b>Result 4</b></figcaption>
</figure>

Clarification: Suppose given question id 3, we could get three answers. We can check the results by the orignal table Answers.

<img src="/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417192503003.png" alt="image-20220417192503003" style="zoom:67%;" />

<figure>
  <figcaption align="center"><b>Answers Rows</b></figcaption>
</figure>

##### (5)

![image-20220417200151982](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417200151982.png)

<figure>
  <figcaption align="center"><b>Result 5</b></figcaption>
</figure>

Clarification: We can illustrate the results by hand. For example, topic `Computer Science`(topic_id 1) has topic `Algorithms`(5) and `Database`(4) under it. Also, `Database` has `NoSQL`(6) under it. Therefore, CS should sum up all the questions and answers under those sub-topics. Let's look at questions number. It equals to 4 + 1 + 1 = 6. So the solution is correct. Plus, this solution will not miss topic with zero question or answer as it uses left join.

<img src="/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417191800741.png" alt="image-20220417191800741" style="zoom:67%;" />

<figure>
  <figcaption align="center"><b>Topics</b></figcaption>
</figure>

<img src="/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417203907277.png" alt="image-20220417203907277" style="zoom:67%;" />

<figure>
  <figcaption align="center"><b>QuestionID-TopicID</b></figcaption>
</figure>

<img src="/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417203755757.png" alt="image-20220417203755757" style="zoom:67%;" />

<figure>
  <figcaption align="center"><b>AnswerID-TopicID</b></figcaption>
</figure>

##### (6)

![image-20220417202041499](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417202041499.png)

<figure>
  <figcaption align="center"><b>Result 6</b></figcaption>
</figure>

Clarification: Question 2 gets the highest score because it contains both of the keywords("indexes" and "MySQL") in both the title and the question body. Also, there is an answer of it containing keyword "indexes". Q8 ranks 2nd as it has both keywords in its title but only has one keyword in the question body and no answer containing any keyword. Q7 get the lowest score since only its question body contains one keyword. 



![image-20220417191832444](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417191832444.png)

<figure>
  <figcaption align="center"><b>Question Title</b></figcaption>
</figure>

![image-20220417191932293](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417191932293.png)

<figure>
  <figcaption align="center"><b>Question Body</b></figcaption>
</figure>

![image-20220417200233798](/Users/xiuwenli/Document/nyu/2022Spring/database/Proj1/CSGY_6083_Database/README.assets/image-20220417200233798.png)

<figure>
  <figcaption align="center"><b>Answers Body</b></figcaption>
</figure>

