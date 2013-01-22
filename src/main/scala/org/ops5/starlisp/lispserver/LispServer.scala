package org.ops5.starlisp.lispserver

import org.starlisp.core._
import io.viper.common.{Response, NestServer}
import io.viper.core.server.router.RouteResponse

object REPL extends NestServer(8080) {

  // curl "http://localhost:8080/hello"
  get("/hello") { args =>
    LispServer.eval("(+ 1 1)")
  }

  // curl -d"sexp=(+ 2 2)" http://localhost:8080/hello
  post("/hello") { args =>
    LispServer.eval(args.get("sexp"))
  }
}

object LispServer {
  def eval(sexp: String): RouteResponse  = {
    val runtime = Runtime.createAndBootstrap
    val stream = runtime.inputStreamFromString(sexp)
    val lispObject = runtime.eval(runtime.read(stream))
    val outputStream = new StringOutputStream()
    runtime.prin1(lispObject, outputStream)
    Response(outputStream.toString)
  }
}