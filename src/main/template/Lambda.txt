http://winterbe.com/posts/2014/03/16/java-8-tutorial/
http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

    @Test
    public void testLambda01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(System.out::println);
    }

    @Test
    public void testLambda02() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        /*int sum = 0;
        for(Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        System.out.println(sum);*/

        //
        int sum = list.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
        System.out.println(sum);
    }


    /**
     * 循环遍历和处理map
     */
    @Test
    public void testLambda03() {
        Map<String,String> maps = new HashMap<String,String>();
        maps.put("A","AAAAA");
        maps.put("B","BBBBB");
        maps.put("C","CCCCC");
        maps.put("D","DDDDD");
        maps.put("E","EEEEE");
        maps.forEach((k,v) -> {
            System.out.println(k+" -- "+ v);
        });
    }

    /**
     * 不同数据类型的Stream
     */
    @Test
    public void testLambda04() {
        IntStream.range(1,20).forEach(System.out::println);
    }

    @Test
    public void testLambda05() {
        Arrays.stream(new int[]{1,2,3,4,5}).map(x -> x * 2).forEach(System.out::println);
    }

    @Test
    public void testLambda06() {
        List<String> lists = new ArrayList<>();
        lists.add("AAA");
        lists.add("BBB");
        lists.add("CCC");
        lists.add("DDD");
        String str = lists.stream().map(a -> a.toLowerCase() + " ling").reduce((a,b) -> a+" ** " +b).get();
        System.out.println(str);
    }

    @Test
    public void testLambda07() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3
    }

    @Test
    public void testLambda08() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });
    }

    @Test
    public void testLambda09(){
        Stream.of("d2", "a2", "b2", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
            // map:      d2
            // anyMatch: D2
            // map:      a2
            // anyMatch: A2
    }

    
    @Test
    public void testLambda10(){
        Stream.of("d2", "a2", "b1", "b3", "c").map(s ->{
            System.out.println("map-->" +s);
            return s.startsWith("a");
        })
        .forEach(x -> System.out.println("forEach --> "+x));
    }

    /**
     * map会改变Stream中的数据，，，是输出的结果产生变化，
     * 而Filter只会筛选出数据，不会更改任何Stream中的数据
     */
    @Test
    public void testLambda11() {
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s-> s.startsWith("a")).forEach(s -> System.out.println("foreach --> "+s));
    }

    @Test
    public void testLambda12(){
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            System.out.println("map --> " + s);
            return s.toUpperCase();
        }).filter(m -> {
            System.out.println("filter --> " + m);
            return m.startsWith("A");
        }).forEach(n -> System.out.println("forEach --> "+n));
    }

    @Test
    public void testLambda13() {
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            System.out.println("filter --> " +s);
            return s.startsWith("a");
        }).map(s -> {
            System.out.println("map --> " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach --> "+s));
    }


    @Test
    public void testLambda14() {
        Stream.of("d2", "a2", "b1", "b3", "c").sorted().filter(s -> {
            int i = 0;
            System.out.println("filter --> " +s);
            return s.startsWith("a");
        }).map(s -> {
            System.out.println("map --> " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach --> "+s));
    }

    @Test
    public void testLambda15() {
        Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1,s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        }).filter(s -> {
            System.out.println("filter  --> " +s);
            return s.startsWith("a");
        }).map(s -> {
            System.out.println("map --> " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach --> "+s));
    }

    @Test
    public void testLambda16() {
        Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            System.out.println("filter --> " +s);
            return s.startsWith("a");
        }).sorted((s1,s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        }).map(s -> {
            System.out.println("map --> " + s);
            return s.toUpperCase();
        }).forEach(s -> System.out.println("forEach --> "+s));
    }

    /**
     * throw new IllegalStateException(MSG_STREAM_LINKED);
     */
    @Test
    public void testLambda17() {
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        stream.noneMatch(s -> true);   // exception
    }

    /***
     * reusing Stream.......
     */
    @Test
    public void testLambda18(){
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
        streamSupplier.get().forEach(s -> System.out.println(s));
    }

    @Test
    public void testLambda19() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        List<Person> filtered = initObj.stream()
                .filter(p -> p.getName().startsWith("D"))
                .collect(Collectors.toList());
        System.out.println(filtered);

    }

    /**
     * 将list转换成map
     */
    @Test
    public void testLambda20() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        Map<Integer,List<Person>> persons = initObj.stream().collect(Collectors.groupingBy(p -> p.getAge()));

        persons.forEach((age,person) -> System.out.println(age+" -- " + person));
    }

    @Test
    public void testLambda21() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        //19.0
        double averageAge= initObj.stream().collect(Collectors.averagingInt(p -> p.getAge()));
        System.out.println(averageAge);

        //IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
        IntSummaryStatistics ageSummary =
                initObj.stream()
                       .collect(Collectors.summarizingInt(p -> p.getAge()));

        System.out.println(ageSummary);
    }

    @Test
    public void testLambda22(){
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        initObj.stream().map(s -> s.getName()).forEach(s -> System.out.println(s));
    }

    @Test
    public void testLambda23() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
        String str = initObj.stream()
                .filter(s -> s.getAge()>18)
                .map(s -> s.getName())
                // X Peter AND Pamela Y
                //.collect(Collectors.joining(" AND "," X "," Y "));
                .collect(Collectors.joining());
        System.out.println(str);
    }

    /**
     * 转换成Map
     */
    @Test
    public void testLambda24() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
        Map<Integer,String> map = initObj.stream()
                .collect(Collectors.toMap(p -> p.getAge(),p -> p.getName(),(a,b) -> a + ","+b));
        map.forEach((a,b) -> System.out.println(a+","+b));
    }

    /**
     * MAX | PETER | PAMELA | DAVID
     */
    @Test
    public void testLambda25() {
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);

        String names = initObj.stream().collect(personNameCollector);
        System.out.println(names);
    }


     /**
     * 其作用相当于断点
     */
    @Test
    public void testLambd26(){
        Stream.of("one", "two", "three", "four")
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * 排除重复元素
     */
    @Test
    public void testLambd27(){
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("Pamela", 23),
                new Person("David", 12));


        List<String> lists = initObj.stream().map(s -> s.getName()).collect(Collectors.toList());

        lists.stream().distinct().forEach(s -> System.out.println(s));
    }

    /**
     * 最后要跟collect peek才会生效
     */
    @Test
    public void testLambd28(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

    }

    @Test
    public void testLambda29(){
        boolean b = Arrays.stream(new int[]{1,2,3,4,5,6,7}).filter(s -> s > 5).isParallel();
        System.out.print(b);
    }

    @Test
    public void testLambda30(){
      IntStream.range(0,10).mapToObj(s -> " obj"+s).forEach(s -> System.out.println(s));
    }

    @Test
    public void testLambda31(){
        IntStream.range(0,10).mapToObj(s -> " obj"+s).forEach(s -> System.out.println(s));
    }

    /**
     * FlatMap
     */
    @Test
    public void testLambda32(){
        List<Foo> foos = new ArrayList<>();
        //List<Bar> bars = new ArrayList<>();

        //init Foo
        IntStream.range(1,6).forEach(s -> foos.add(new Foo("FooName" +s)));

        //init Bar
        foos.forEach(f ->
                IntStream
                        .range(1, 3)
                        .forEach(i -> f.getBarList().add(new Bar("Bar" + i ))));
        foos.forEach(s -> System.out.println(s));
    }


    /**
     * FlatMap
     */
    @Test
    public void testLambda33(){
        List<Foo> foos = new ArrayList<>();
        //List<Bar> bars = new ArrayList<>();

        //init Foo
        IntStream.range(1,4).forEach(s -> foos.add(new Foo("FooName" +s)));

        //init Bar
        foos.forEach(foo -> {
            List<Bar> bars = new ArrayList<>();
            IntStream.range(8,12).forEach(i -> {
                bars.add(new Bar("barName"+i));
                foo.setBarList(bars);
            });
        });


        //foos.forEach(s -> System.out.println(s));
        foos.stream().flatMap(f -> f.getBarList().stream()).forEach(s -> System.out.println(s));
    }


    @Test
    public void testLambda34() {
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(8,12).mapToObj(i -> new Bar("Bar" + i + " <- "+ f.getFooName())).forEach(f.getBarList()::add))
                .flatMap(f -> f.getBarList().stream())
                .forEach(b -> System.out.println(b.getBarName() +" aaa"));
    }

    @Test
    public void testLambda35(){
        List<Foo> foos = new ArrayList<>();
        List<Bar> bars1 = new ArrayList<>();
        List<Bar> bars2 = new ArrayList<>();
        List<Bar> bars3 = new ArrayList<>();
        List<Bar> bars4 = new ArrayList<>();
        IntStream.range(0,5).forEach( s -> {
            foos.add(new Foo("FooName"+s));
        });
        IntStream.range(5,7).forEach(s -> {
            bars1.add(new Bar("BarName"+s));
        });
        IntStream.range(8,11).forEach(s -> {
            bars2.add(new Bar("BarName"+s));
        });
        IntStream.range(10,14).forEach(s -> {
            bars3.add(new Bar("BarName"+s));
        });
        IntStream.range(100,103).forEach(s -> {
            bars4.add(new Bar("BarName"+s));
        });
       foos.get(0).setBarList(bars1);
        foos.get(1).setBarList(bars2);
        foos.get(2).setBarList(bars3);
        foos.get(3).setBarList(bars4);
        foos.get(4).setBarList(null);

        foos.forEach(s -> System.out.println(s));
        System.out.println("------------------------------------------------");
        foos.stream().flatMap(s -> s.getBarList().stream()).forEach(m -> System.out.println(m));
    }
    /**
     * 为了防止报null指针异常，使用Optional.of 处理
     */
    @Test
    public void testLambda36(){
        Optional.of(new Foo("FOO"))
                .flatMap(o -> Optional.ofNullable(o.getBarList()))
                .ifPresent(System.out::println);
    }

    @Test
    public void testLambda37(){
        List<Person> initObj = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("Pamela", 109),
                new Person("David", 12));

        initObj.stream().reduce((p1,p2) -> p1.getAge() > p2.getAge() ? p1:p2).ifPresent(System.out::println);
    }

    @Test
    public void testLambda38(){
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }

    /**
     * 多线程平行执行
     */
    @Test
    public void testLambda39() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1","a1", "a2",
                "b1", "c2", "c1","a1", "a2", "b1", "c2", "c1","a1", "a2",
                "b1", "c2", "c1","a1", "a2", "b1", "c2", "c1","a1",
                "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }