package bookmarks;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

// tag::code[]
//
// curl -X POST -vu android-bookmarks:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password&username=jlong&grant_type=password&scope=write&client_secret=123456&client_id=android-bookmarks"
// curl -v POST http://127.0.0.1:8080/security/bookmarks -H "Authorization: Bearer 29580284-2459-4428-9c60-25df2804225f"
//url --tomcat.keystore certRepo -u mlass:cordoba32 -X GET -H 'Content-Type: application/json' "http://localhost:8080/oauth/token"
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	// CORS
	@Bean
	FilterRegistrationBean corsFilter(@Value("${tagit.origin:http://localhost:9000}") String origin) {
		return new FilterRegistrationBean(new Filter() {
			public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
					throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				String method = request.getMethod();
				// this origin value could just as easily have come from a
				// database
				response.setHeader("Access-Control-Allow-Origin", origin);
				response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
				response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Headers",
						"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				if ("OPTIONS".equals(method)) {
					response.setStatus(HttpStatus.OK.value());
				} else {
					chain.doFilter(req, res);
				}
			}

			public void init(FilterConfig filterConfig) {
			}

			public void destroy() {
			}
		});
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
		return (evt) -> Arrays.asList("dlasso,mlasso".split(",")).forEach(a -> {
			Account account = accountRepository.save(new Account(a, "Colombia1"));
			bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + a, "A description"));
			bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + a, "A description"));
		});
	}

}
// end::code[]