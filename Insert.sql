insert into User (username, email, password, city, state, country, profile)
values ("Jaccob James", "corinna8@euneeedn.com", '123456', 'New York City', "NY",
	"United States", "This person is lazy.");
insert into User (username, email, password, city, state, country, profile)
values ("Alice Walker", "alice@yahoo.com", 'abcdef', 'Redwood City', "CA",
	"United States", "This person loves maths.");
insert into User (username, email, password, city, state, country, profile)
values ("Wade Cole", "wade@comcast.net", '123456', 'Huston', "TX",
	"United States", "This person loves computer science.");
insert into User (username, email, password, city, state, country, profile)
values ("Sheryl Lindsey", "sheryl@euneeedn.com", '123456', 'Boston', "MA",
	"United States", "This person loves arts");
	
	
insert into Topic(topic_name) values ('Computer Science');
insert into Topic(topic_name) values ('Maths');
insert into Topic(topic_name) values ('Finance');
insert into Topic(topic_name, parent_id) values ('Database', 1);
insert into Topic(topic_name, parent_id) values ('Algorithms', 1);
insert into Topic(topic_name, parent_id) values ('NoSQL', 4);
insert into Topic(topic_name, parent_id) values ('Linear Algebra', 2);
insert into Topic(topic_name, parent_id) values ('Statistics', 2);

insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 5, NOW(), 'Shift 2D Grid', 
'Given a 2D grid of size m x n and an integer k. You need to shift the grid k times. Return the 2D grid after applying shift operation k times.');
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 4, NOW(), 'How can I rebuild indexes and update stats in MySQL?', 
"I have experience with MS SQL server where it is possible and useful to update statistic and rebuild indexes. I can't find such option in MySQL innoDB, is there such option?");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 1, NOW(), 'How to get started in computer science', 
"I'm a incoming colledge student majoring computer science. How should I prepare for my undegrad study?");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 7, NOW(), 'What does transpose matrix mean?', 
"As the title");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 3, NOW(), 'Difference between payment due date and next closing date', 
"It's easy to confuse your statement closing date with your payment due date. Can anyone give clear definition to these two topics?");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 6, NOW(), 'Mongodb vs Cassandra', 
"If you are searching for a NoSQL database, you probably came across Cassandra and MongoDB. Still, these two popular NoSQL choices have much less in common than expected.");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 4, NOW(), 'How to improve the performance of Redis',
"Redis is a in-memory KV database. Is there any kind of methods to improve its performance, like we build indexes in relational databases?");
insert into Questions(uid, topic_id, date, title, ques_body)
values (1, 4, NOW(), 'How many types of indexes in MySQL',
"And is foreign key a kind of indexes too?");



insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('3', '1', NOW(), 
"A good first step might be to draw a picture (a rough sketch on a whiteboard would be fine) of each of the 3 cases to be certain that you understand them. 
Then, k times, we need to create a new 2D array and follow the given rules to move the values. 
If we're using Java, we'll also need to then convert the output from a 2D Array to a 2D list."
);
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('3', '2', NOW(), 
"This is done with 'ANALYZE TABLE table_name;'");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('3', '3', NOW(), 
"In recent years, there has been a proliferation of other options. But as someone involved in tech education, I retained a healthy skepticism about how useful many classes, MOOCs (massive open online courses), and bootcamps might really be.
And I’m not the only one skeptical of bootcamps and MOOCs (for more, there is an interesting Quora thread).");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('3', '4', NOW(), 
"The transpose of a matrix is simply a flipped version of the original matrix. We can transpose a matrix by switching its rows with its columns. ");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('3', '5', NOW(), 
"Your payment due date is the deadline by which you need to pay the credit card issuer for the billing cycle if you want to avoid paying interest.");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('2', '4', NOW(), 
"Flipping a matrix over its diagonal. The rows and columns get swapped. Example: the value in the 1st row and 3rd column ends up in the 3rd row and 1st column.");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('2', '3', NOW(), 
"Step 1: Learn to code · Step 2: Learn the ins and outs of programming · Step 3: Choose a specialization · Step 4: Keep learning.");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('4', '3', NOW(),
"If you're ready to learn more, but not sure if you want to dive right into a degree, check out a few online intro classes. ");
insert into Answers(`uid`, `ques_id`, `date`, `ans_body`)
values ('2', '2', NOW(),
"drop / re-add indexes (obviously) dump / reload the table. ALTER TABLE and change using the same storage engine.REPAIR TABLE (only works for MyISAM, ARCHIVE, and CSV)");
