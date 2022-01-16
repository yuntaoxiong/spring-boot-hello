package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

    private String myName = "";

    @RequestMapping("/")
    public String home() {
        return "Hello this is Yuntao Project messages!";
    }

    @RequestMapping("/good")
    public String good() {
        return "Change to new API good";
    }

    @RequestMapping("/name/{name}")
    public String getName(@PathVariable("name") String name) {
        return this.myName + " - your name" + name;
    }

    @PostMapping("/name")
    @ResponseStatus(HttpStatus.CREATED)
    public String createName(@RequestBody String name) {
        String mName = "my name: " + name;
        this.myName = mName;
        return mName;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
