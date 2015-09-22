package se.callista.microservises.support.discovery;




import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;

@Test
public class ApplicationTests {

	@Test
	public void TestSomething() {

	}


}






/*
public class ApplicationTests {
	
	@Value("${local.server.port}")
	private int port = 0;

	@Test
	public void catalogLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = new TestRestTemplate("user", "password").getForEntity("http://localhost:" + port + "/eureka/apps", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void adminLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = new TestRestTemplate("user", "password").getForEntity("http://localhost:" + port + "/env", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
*/