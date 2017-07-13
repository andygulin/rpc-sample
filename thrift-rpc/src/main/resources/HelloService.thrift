namespace java rpc.sample.service

service HelloService{
	string hello(),
	string sayHello(1:string name)
}