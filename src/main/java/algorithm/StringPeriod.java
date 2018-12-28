package algorithm;
/*
* 1. 返回一个str是否是周期串
* 2. 找出str的最小周期
* */

public class StringPeriod {
     /* 1.find len of the longest proper prefix of ‘str’,
       * Let the length of the longest proper prefix suffix be ‘len’.
       * time :o(n) -- KMP
       */
    private static int[] prefix;   //如何设置更好？？？？

    private static void computeLPSArray (String s) {
        // len of previous longest prefix suffix??
        int len = 0;
        prefix[0] = 0; // prefix[0] is always 0

        int i = 1;
        //for i = 1 to s.len - 1,calculate lps[]
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len != 0) {  //This is tricky, exp: AAACAAAA and i = 7.
                    len = prefix[len - 1]; // also we do not increment i here
                } else {
                    prefix[0] = 0;
                    i++;
                }
            }
        }
    }
    // Returns true if str is repetition of one of its substrings else return false.
    public static boolean isRepeat(String s) {
        // Find length of string and create an array to store lps values used in KMP
        int n = s.length();
        prefix = new int[n];
        computeLPSArray(s);

        // Find length of longest suffix which is also prefix of str.
        int maxLen = prefix[n-1];

        // If there exist a suffix which is also prefix AND Length of the remaining substring
        // divides total length, then str[0..n-len-1] is the substring that repeats n/(n-len)
        // times (Readers can print substring and value of n/(n-len) for more clarity.
        return (maxLen > 0 && n %(n - maxLen) == 0)? true : false;
    }

    public static int getRepeat(String s) {
        if (isRepeat(s)) {
            int maxLen = prefix[s.length() - 1];
            return Math.min(s.length() - maxLen, maxLen);
        }
        return 0;
    }

    public static void main(String[] args) {
        String txt[] = {"ABCABC", "ABABAB", "ABCDABCD",
                "GEEKSFORGEEKS", "GEEKGEEK",
                "AAAACAAAAC", "ABCDABC"};
        int n = txt.length;
        for (int i = 0; i < n; i++) {
            if(isRepeat(txt[i]) == true)
                System.out.print("True");
            else
                System.out.print("False");

            System.out.println(" " + getRepeat(txt[i]));
        }
    }

}
