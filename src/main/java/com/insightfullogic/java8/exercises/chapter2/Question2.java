package com.insightfullogic.java8.exercises.chapter2;

import com.insightfullogic.java8.exercises.Exercises;

import javax.swing.text.DateFormatter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import static java.lang.ThreadLocal.withInitial;

public class Question2 {

//    public static ThreadLocal<DateFormatter> formatter
//            = Exercises.replaceThisWithSolution();

	//我的答案
    public static ThreadLocal<DateFormatter> formatter
            = ThreadLocal.withInitial(()-> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));

}
