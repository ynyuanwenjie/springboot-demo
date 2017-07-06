package lambdasinaction.chap6;

import org.apache.el.stream.*;
import org.junit.Test;

import java.util.*;
import java.util.Optional;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

/**
 * Created by yuanwenjie on 2017/4/1.
 */
public class TestMain {

    public static void main(String[] args) {
        //System.out.println(menu);
        //test01();
        //test04();
        //test05();
        //test06();
        //test07();

    }

    public static void test01() {
        Map<GroupingTransactions.Currency, List<GroupingTransactions.Transaction>> currencyListMap = new HashMap<>();
        currencyListMap = GroupingTransactions.transactions.stream().collect(Collectors.groupingBy(e -> e.getCurrency()));
        ;
        System.out.println(currencyListMap);
    }

    public static void test02() {
        Map<GroupingTransactions.Currency, List<GroupingTransactions.Transaction>> currencyListMap = new HashMap<>();
        currencyListMap = GroupingTransactions.transactions.stream().collect(Collectors.groupingBy(GroupingTransactions.Transaction::getCurrency));
        System.out.println(currencyListMap);
    }

   /* public static void test03(){
        Map<boolean, List<GroupingTransactions.Transaction>> currencyListMap = new HashMap<>();
        currencyListMap = GroupingTransactions.transactions.stream().collect(Collectors.groupingBy(e -> e.));
        System.out.println(currencyListMap);
    }*/

    public static void test04() {
        //获取List的大小
        long sum = menu.stream().count();
        long total = menu.stream().collect(Collectors.counting());
        System.out.println(sum);
        System.out.println(total);

        //获取全部的卡路里

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);
        long totalCalories2 = menu.stream().collect(Collectors.summingLong(Dish::getCalories));
        System.out.println(totalCalories2);

        //获取最大最小卡路里对象
        Optional<Dish> dishOptional = menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(dishOptional.get());
        System.out.println(dishOptional.filter(s -> s.getCalories() == 120).isPresent());
    }


    public static void test05() {
        Map<Dish.Type, Dish> dishMap = menu.stream().collect
                (Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(dishMap);
    }

    public static void test06() {
        Map<Boolean, List<Dish>> listMap = menu.stream().collect(Collectors.groupingBy(Dish::isVegetarian));
        System.out.println(listMap);
    }

  /*  public static void test07(){
        Map<Boolean,Map<Dish.Type,List<Dish>>> typeListMap = menu.stream().collect(Collectors.groupingBy(Dish::isVegetarian),Collectors.collectingAndThen(Collectors.groupingBy(Dish::getType)),Optional::get);
        System.out.println(typeListMap);
    }*/


    @Test
    public void test08() {
        long count = menu.stream().count();
        long count2 = menu.size();
        System.out.println(count + "," + count2);

        //求总共的卡路里
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);
        //求平均卡路里
        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);

        //获取最大/最小卡路里
        Optional<Dish> optional = menu.stream().collect(minBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(optional.get());
    }

    /**
     * Joining Strings
     */
    @Test
    public void test09() {
        String shortName = menu.stream().map(s -> s.getName() + ",").collect(joining());
        String shortName1 = menu.stream().map(s -> s.getName()).collect(joining(","));
        String shortName2 = menu.stream().map(s -> s.getCalories() + ",").collect(joining());
        String shortName3 = menu.stream().map(s -> s.getCalories() + "").reduce((a, b) -> a + "," + b).get();
        String shortName4 = menu.stream().map(s -> s.getCalories() + "").collect(joining(","));
        System.out.println(shortName);
        System.out.println(shortName1);
        System.out.println(shortName2);
        System.out.println(shortName3);
        System.out.println(shortName4);
    }

    @Test
    public void test10() {
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        //safe
        int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(totalCalories2);

        int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories3);

        //获取最大的卡路里
        Optional<Dish> optional = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(optional.get());
    }

    /**
     * Grouping
     */
    @Test
    public void test11() {
        Map<Dish.Type, List<Dish>> listMap = menu.stream().collect(groupingBy(Dish::getType));
        listMap.forEach((k, v) -> System.out.println(k + "-->" + v));


        //嵌套map
        Map<Dish.Type, Map<Dish.CaloriesLevel, List<Dish>>> levelListMap = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return Dish.CaloriesLevel.DIET;
                            } else if (dish.getCalories() >= 400 && dish.getCalories() <= 700) {
                                return Dish.CaloriesLevel.NORMAL;
                            } else {
                                return Dish.CaloriesLevel.FAT;
                            }
                        })
                ));


        System.out.println(levelListMap);

        //自定义map,统计类型数量
        Map<Dish.Type,Long> longMap = menu.stream().collect(Collectors.groupingBy(Dish::getType,reducing(0L, e -> 1L, Long::sum)));
        Map<Dish.Type,Long> longMap2 = menu.stream().collect(Collectors.groupingBy(Dish::getType,counting()));
        System.out.println(longMap);
        System.out.println(longMap2);

        //获取类型最大的卡路里Map
        Map<Dish.Type,Dish> typeDishMap = menu.stream()
                .collect(groupingBy(Dish::getType,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));

        System.out.println(typeDishMap);
    }

    /**
     * Partitioning
     */
    @Test
    public void test12(){
        Map<Boolean,List<Dish>> maps = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(maps);
    }
}
