package jBlock;

import static spark.Spark.*;

public class Server {
    public static void startServer() {
        get("/", (req, res) -> "Hello World");
    }
}
