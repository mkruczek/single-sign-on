package pl.kruczek.singlesignon.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kruczek.singlesignon.exception.exceptions.AddressNotFoundException;

@RestController
public class ErrorApiController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        throw new AddressNotFoundException("Address not found.");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}