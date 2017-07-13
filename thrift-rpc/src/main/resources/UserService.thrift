namespace java rpc.sample.service

service UserService {
	User getUser(1:i32 id);
	list<User> getAll();
}

struct User {
	1: i32 id;
	2: string name;
	3: i32 age;
	4: string address;
	5: i64 createdAt;
}