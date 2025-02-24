package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //Lombok으로 Getter, Setter 대체
public class HelloData {

    private String username;
    private int age;
}
