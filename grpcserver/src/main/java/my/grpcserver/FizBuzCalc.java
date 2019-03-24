package my.grpcserver;


import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FizBuzCalc {

    public String calc (int i) {
        System.out.println("server input:" + i);
        if (i % 15 == 0) {
            return "FizzBuz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buz";
        } else {
            return "" + i;
        }
    }

    public Flux<String> calcStream(Flux<Integer> nums) {
        return nums.map(this::calc);
    }

}
