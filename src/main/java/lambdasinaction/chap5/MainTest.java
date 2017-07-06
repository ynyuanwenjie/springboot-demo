package lambdasinaction.chap5;

import org.springframework.data.domain.Sort;
import sun.swing.BakedArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by yuanwenjie on 2017/3/30.
 */
public class MainTest {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        List<Integer> numbers = new ArrayList<>();
        IntStream.range(1, 10).forEach(s -> numbers.add(s));
        int sum = numbers.stream().reduce(0, (a, b) -> (a + b));
        int sum2 = numbers.stream().reduce(0,Integer::sum);
        Optional<Integer> sum3 = numbers.stream().reduce((a, b) -> (a+b));
        System.out.println(sum);
        System.out.println(sum2);
        System.out.println(sum3);
        Optional<Integer> count = numbers.stream().reduce(Integer::max);
        System.out.println(count.get());

        Optional<Integer> value = numbers.stream().reduce((a,b) -> (a - b<0?a:b));
        System.out.println(value.get());


    }
}
