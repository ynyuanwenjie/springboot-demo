package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanwenjie on 2017/3/26.
 */
@FunctionalInterface
public interface Function<T, R> {

    R apply(T t);


    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s :list) {
            result.add(f.apply(s));
        }
        return result;
    }
}
