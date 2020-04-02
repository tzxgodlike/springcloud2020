import org.junit.Test;

import java.time.ZonedDateTime;


public class T2 {
    @Test
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();   //默认时区
        System.out.println(zbj);
    }
}
