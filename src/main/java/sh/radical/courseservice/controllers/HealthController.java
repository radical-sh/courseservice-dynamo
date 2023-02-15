package sh.radical.courseservice.controllers;

import sh.radical.courseservice.entities.Health;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {

	@GetMapping
	public Health getHealth() {
		log.info("health api request received");
		return new Health();
	}
}
