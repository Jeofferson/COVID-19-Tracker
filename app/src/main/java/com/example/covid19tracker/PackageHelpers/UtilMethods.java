package com.example.covid19tracker.PackageHelpers;

import android.animation.ValueAnimator;
import android.widget.TextView;

import com.anychart.core.annotations.Line;
import com.example.covid19tracker.PackageSessionVariables.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UtilMethods {


    public static boolean isInteger(String str) {

        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;

    }


    public static LinkedHashMap<String, Integer> sortByValue(LinkedHashMap<String, Integer> linkedHashMap) {

        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(linkedHashMap.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                return a.getValue().compareTo(b.getValue());
            }
        });

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;

    }


    public static LinkedHashMap<String, Integer> reverseHashMap(LinkedHashMap<String, Integer> linkedHashMap) {

        LinkedHashMap<String, Integer> reversedMap = new LinkedHashMap<>();

        ListIterator<String> iter = new ArrayList<>(linkedHashMap.keySet()).listIterator(linkedHashMap.size());
        while (iter.hasPrevious()) {
            String key = iter.previous();
            reversedMap.put(key, linkedHashMap.get(key));
//            System.out.println(key);
        }

        return reversedMap;

    }


    public static void animateNumberWithComma(int value, final TextView textView) {

        ValueAnimator animator = ValueAnimator.ofInt(0, value);
        animator.setDuration(Constants.ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(Constants.FORMAT_WITH_COMMA.format(animation.getAnimatedValue()));
            }
        });
        animator.start();

    }


    public static void animateNumberWithPercent(double value, final TextView textView) {

        ValueAnimator animator = ValueAnimator.ofFloat(0, (float) value);
        animator.setDuration(Constants.ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(String.format("%.2f%%", animation.getAnimatedValue()));
            }
        });
        animator.start();

    }


    public static String millisecondToReadableDateFormat(long millisecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

        return simpleDateFormat.format(calendar.getTime());

    }


    public static String convertToIsoFormat(String iso2, String iso3) {

        String isoFormat = String.format("ISO 2: %s    |    ISO 3: %s", iso2, iso3);

        return isoFormat;

    }


}
