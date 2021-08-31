package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    private static int counter = 0;
    private static List<UserMeal> listDate = new ArrayList<>();
    private static boolean exs = true;
    private static LocalDate buferDate;

    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(6, 0), LocalTime.of(13, 0), 1950);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> mealsTo = new ArrayList<>();
        buferDate = meals.get(0).getDateTime().toLocalDate();

        for (int i = 0; i < meals.size(); i++) {
            UserMeal bufer = meals.get(i);
            LocalTime localTime = bufer.getDateTime().toLocalTime();
            boolean exsess = TimeUtil.isBetweenHalfOpen(localTime, startTime, endTime);
            if (exsess) {
                if (i == (meals.size() - 1)) {
                    addListDate(bufer);
                    clearListDate(caloriesPerDay, mealsTo, bufer);
                    break;
                }
                if (buferDate.equals(bufer.getDateTime().toLocalDate())) {
                    addListDate(bufer);
                }
                if (!buferDate.equals(bufer.getDateTime().toLocalDate())) {
                    clearListDate(caloriesPerDay, mealsTo, bufer);
                }


            }
            if (i == (meals.size() - 1)) {
                clearListDate(caloriesPerDay, mealsTo, bufer);
            }

        }

        return mealsTo;
    }

    private static void addListDate(UserMeal bufer) {
        counter = counter + bufer.getCalories();
        listDate.add(bufer);
    }

    private static void clearListDate(int caloriesPerDay, List<UserMealWithExcess> mealsTo, UserMeal bufer) {
        if (counter > caloriesPerDay) {
            exs = false;
        }

        listDate.forEach(userMeal -> mealsTo.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exs)));

        counter = 0;
        buferDate = bufer.getDateTime().toLocalDate();
        exs = true;
        listDate.clear();
        addListDate(bufer);
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
