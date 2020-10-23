package com.zsw;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;

/**
 * @author ZhangShaowei on 2020/10/22 15:37
 */
public class VertxApiApp {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(10));

        HttpServer httpServer = vertx.createHttpServer();

        httpServer.requestHandler(request -> {
            request.response().putHeader("Content-Type", "text/plain").write("Hello World").end();
        });

        httpServer.listen(8080, httpServerAsyncResult -> {

        });


    }

}
