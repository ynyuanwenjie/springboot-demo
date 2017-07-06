package lambdasinaction.chap7;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by yuanwenjie on 2017/5/14.
 */
public class Testmain {


    public  long parallelSum(int n) {
        return Stream.iterate(1L,i -> i+1).limit(n).parallel().reduce(0L,Long::sum);
    }

    @Test
    public void test01(){
        System.out.println(parallelSum(120));
    }

    @Test
    public void test02(){
        long result =LongStream.rangeClosed(1,3).reduce(0L,Long::sum);
        long result2 =LongStream.range(1,3).reduce(0L,Long::sum);
        System.out.println(result);
        System.out.println(result2);

    }
}
