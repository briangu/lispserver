lispserver
==========

LispServer is a demo application showing how to use [starlisp] in a server setting.  The nice thing about using starlisp in a server setting is that it's easy and fast to create a new runtime environment on a per-request basis, avoiding any cross-thread contention on the lisp interpreter.  It would also support runtime caching.

usage
=====

compile code:

    git submodule init
    git submodule update --recursive
    mvn clean install

run the server:

    java -jar server/target/org.ops5.starlisp.lispserver.server-0.0.1-jar-with-dependencies.jar

perform (+ 1 1) on the lisp server:

    curl -XPOST -d"sexp=(+ 1 1)" http://localhost:8080/hello
