import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

public class Main {
    private static final int PORT = 8080;
    private static final String[] DAYS_OF_WEEK = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        httpServer.createContext("/day", new DayHandler());
        httpServer.start();

    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Началась обработка /hello запроса от клиента.");
            String response = "Вы успешно попали на сервер";
            exchange.sendResponseHeaders(200, 0);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    static class DayHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Началась обработка /day запроса от клиента.");

            Random random = new Random();
            String nameOfRandomDay = DAYS_OF_WEEK[random.nextInt(DAYS_OF_WEEK.length)];
            exchange.sendResponseHeaders(200, 0);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(nameOfRandomDay.getBytes());
            }
        }
    }
}
