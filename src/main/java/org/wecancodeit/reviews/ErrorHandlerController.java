package org.wecancodeit.reviews;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class ErrorHandlerController implements ErrorController {

	@RequestMapping("/error")
	public String handleError() {
		// do something like logging
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
