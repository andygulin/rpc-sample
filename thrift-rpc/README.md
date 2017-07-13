### 源码编译
	./configure --prefix=/opt/thrift --with-erlang=no --with-nodejs=no --with-lua=no --with-python=no --with-perl=no --with-php=no --with-php_extension=no 	--with-ruby=no --with-d=no --with-go=no --with-csharp=no
	make && make install

	ubuntu
	sudo apt-get install thrift-compiler 

### 生成
	thrift-0.10.0.exe -o . -gen java src/main/resources/HelloService.thrift
	thrift-0.10.0.exe -o . -gen java src/main/resources/UserService.thrift
	
### 启动Server
	rpc.sample.Bootstrap
	
### 调用
	rpc.sample.ThriftClient#hello
	rpc.sample.ThriftClient#user