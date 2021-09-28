package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {

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

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(11, 0), LocalTime.of(21, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(11, 0), LocalTime.of(21, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> mealsTo = new ArrayList<>();

        boolean caloriesDay = true;


        Map<LocalDate, Integer> dateUserMealCalories = new HashMap<>();
        for (int i = 0; i < meals.size(); i++) {
            UserMeal userMeali = meals.get(i);
            int caloriesFromdateUserMealCalories = dateUserMealCalories.getOrDefault(userMeali.getDateTime().toLocalDate(),0);
            dateUserMealCalories.merge(userMeali.getDateTime().toLocalDate(), userMeali.getCalories(), (a,b) -> caloriesFromdateUserMealCalories + b);
        }

        for (int i = 0; i < meals.size(); i++) {
            UserMeal userMeali = meals.get(i);
            LocalTime localTimei = userMeali.getDateTime().toLocalTime();
            boolean exsessThis = TimeUtil.isBetweenHalfOpen(localTimei, startTime, endTime);
            if (exsessThis) {
                if (dateUserMealCalories.getOrDefault(userMeali.getDateTime().toLocalDate(),0)> caloriesPerDay) {
                    caloriesDay = false;
                }
                mealsTo.add(new UserMealWithExcess(userMeali.getDateTime(), userMeali.getDescription(), userMeali.getCalories(),caloriesDay));
            }
        }
        return mealsTo;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMeal> sortList = meals.stream()
                .filter(s -> TimeUtil.isBetweenHalfOpen(s.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());

        Map<LocalDate, Integer> dateUserMealCalories = meals.stream()
                .collect(Collectors.toMap(
                        s -> s.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        Integer::sum));

        List<UserMealWithExcess> resutList = sortList.stream()
                .map(a -> {
                    UserMealWithExcess bufer;
                    if (caloriesPerDay >= dateUserMealCalories.get(a.getDateTime().toLocalDate())) {
                        bufer = new UserMealWithExcess(a.getDateTime(), a.getDescription(), a.getCalories(), true);
                    } else {
                        bufer = new UserMealWithExcess(a.getDateTime(), a.getDescription(), a.getCalories(), false);
                    }
                    return bufer;
                })
                .collect(Collectors.toList());
        resutList.forEach(System.out::println);
        return resutList;
    }
}
