package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
	SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	return builder.build();
    }

// Auth token: eyJhbGciOiJIUzI1NiIsImtpZCI6InNlY3JldCIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIzeUY1VE9TemRsSTQ1UTF4c3B4emVvR0JlOWZOeG05bSIsImVtYWlsIjoib2xpdmVyLnZlaXRzQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJleHAiOjE1MzAwNDA4NTQsImlhdCI6MTUyOTYwODg1NCwiaXNzIjoiaHR0cHM6Ly9kY29zLmF1dGgwLmNvbS8iLCJzdWIiOiJnb29nbGUtb2F1dGgyfDExNjI1MzMxNzc0ODE4NzQ5MDc3NCIsInVpZCI6Im9saXZlci52ZWl0c0BnbWFpbC5jb20ifQ.eKN38w5yL8ZDlbS682qRwcUeIvy1SS4H_oRzxwJk66c

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
	return args -> {
		String url = "http://94.130.187.229/service/marathon/v2/apps";
		//String url = "http://gturnquist-quoters.cfapps.io/api/random";

		restTemplate.setInterceptors(Collections.singletonList(new AuthClientHttpRequestInterceptor()));
                
                String body = restTemplate.getForObject(
                                url, String.class);
		log.info(body);
		
		Quote quote = restTemplate.getForObject(
				url, Quote.class);
		log.info(quote.toString());
	};
    }
}
