package lambdasinaction.chap1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * Created by yuanwenjie on 2017/3/26.
 */
public class TestMain {

   /* @Bean
    CommandLineRunner runner (FilteringApples.Apple apple) {
        return args -> {
            appleList.add(new FilteringApples.Apple(80,"green"));
            appleList.add(new FilteringApples.Apple(155,"green"));
            appleList.add(new FilteringApples.Apple(120,"red"));
        };
    }*/

    public static void main(String[] args) {
        //testFunction01();
        //testFunction02();
        //testFunction03();
       // testFunction04();
        //testFunction05();
        System.out.println("中文");
    }

    public static void testFunction01() {
        FunctionInterfaceTest functionInterfaceTest = new FunctionInterfaceTest() {
            @Override
            public Object test(Object o) {
                System.out.println("oooooooooooo" + o);

                return o;
            }
        };
        FunctionInterfaceTest interfaceTest = (String) -> {return new FilteringApples.Apple(22,"yellow");};
        System.out.println(interfaceTest.test(new FilteringApples.Apple()));
    }

    public static void testFunction02(){
        //[4, 6, 12]
        List<Integer> l1 = Function.map(Arrays.asList("hello", "lambda", "this is awosome!"), (String s) -> s.length() - 1);
        List<Integer> l2 = Function.map(Arrays.asList("hello", "lambda", "this is awosome!"), (String s) -> s.length());
        System.out.println(l1);
        System.out.println(l2);
    }

    public static void testFunction03(){
        IntPredicate numbers = (int i) -> i % 2 == 0;
        System.out.println(numbers.test(102));

    }

    public static void testFunction04(){
        final int num = 2;
        Runnable run = () -> System.out.println(num);
        run.run();

       /* List<String> list = Arrays.asList("QX","WWW","CCCC","X");
        list.stream().forEach(a -> {
            int i=0;
            if(num % 2 == 0) {
                i++;
            }
            System.out.println(i+",  "+a+",  "+a.length()+","+num);
        });*/

    }

    public static void testFunction05() {
        List<FilteringApples.Apple> inventory = Arrays.asList(new FilteringApples.Apple(80, "green"),
                new FilteringApples.Apple(155, "green"),
                new FilteringApples.Apple(120, "red"));
        System.out.println(inventory);
        inventory.sort(Comparator.comparing(FilteringApples.Apple::getWeight));
        System.out.println(inventory);
     }
}