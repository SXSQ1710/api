package com.SXSQ.project.sxgateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @title: Test
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/9 22:06
 **/

public class Test {
    public static void main(String[] args) {
//        String s1 = "ab";
//        String s2 = "eidboaoo";
//        System.out.println(checkInclusion(s1, s2));

        int[] nums ={1,2,3};
        int k =3;
        System.out.println(subarraySum(nums,k));
    }

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        for(int i = 0; i < s1.length(); i++){
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
        }

        int left = 0; int right = 0; int valid = 0;
        while(right < s2.length()){
            char c = s2.charAt(right);
            right++;

            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (Objects.equals(window.get(c), need.get(c))) valid++;
            }

            while((right - left) > s1.length()-1){
                char d = s2.charAt(left);
                left++;

                if (valid == need.size()) return true;
                if (need.containsKey(d)) {
                    if (Objects.equals(window.get(d), need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }

            }
        }
        return false;
    }

    public static int subarraySum(int[] nums, int k) {
        int left = 0; int right = 0;int temp = 0;
        int count = 0;
        while(right < nums.length || left < nums.length){
            if(temp < k ){
                temp += nums[right];
                right++;
            } else if(temp > k ){
                temp -= nums[left];
                left++;
            } else {
                count++;
                temp -= nums[left];
                left++;
            }

        }

        return count;
    }
}
