package cn.licoy.wdog;


import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/16/11:52
 */
public class AntPathMatcherTest {

    public static void main(String[] args) {
        String s = "/user/**/name";
        String s2 = "/user/78789789/4445/7897897/name?name=444";

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        System.out.println(antPathMatcher.match(s, s2));
    }

    @Test
    public void a() {

        List<String[]> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add(0,new String[]{"a"+i,"a-v-"+i});
        }

        System.out.println(strings);

    }
}
