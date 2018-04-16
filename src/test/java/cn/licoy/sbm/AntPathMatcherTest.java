package cn.licoy.sbm;


import org.springframework.util.AntPathMatcher;

/**
 * @author Licoy
 * @version 2018/4/16/11:52
 */
public class AntPathMatcherTest {

    public static void main(String[] args) {
        String s = "/user/**/name";
        String s2 = "/user/78789789/4445/7897897/name?name=444";

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        System.out.println(antPathMatcher.match(s,s2));
    }


}
