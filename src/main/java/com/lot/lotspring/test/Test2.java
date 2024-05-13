package com.lot.lotspring.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test2 {


    private static String[] compare(String[] arr1, String[] arr2) {

        StringBuilder sameResultTemp = new StringBuilder();
        String sameResultStr;

        Set<String> testSet = new HashSet<>(Arrays.asList(arr1));

        for (String s : arr2) {
            if (!testSet.add(s)) {
                sameResultTemp.append(s).append(",");
            }
        }

        if (!sameResultTemp.isEmpty()) {
            sameResultStr = sameResultTemp.toString();
            sameResultStr = sameResultStr.substring(0, sameResultStr.length() - 1);
            return sameResultStr.split(",");
        } else {
            return new String[0];
        }
    }


    public static void main(String[] args) {

        String[] string1 = compare(new String[]{"Script", "Jwt", "Css"}, new String[]{"Script", "Jwt", "Html"});

        System.out.println(Arrays.toString(string1));

        String[] string2 = compare(new String[]{"Script", "World", "Java"}, new String[]{"Hellos", "Test", "Hello"});

        System.out.println(Arrays.toString(string2));
    }


}
