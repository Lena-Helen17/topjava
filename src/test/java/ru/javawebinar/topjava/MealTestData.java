package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID3 = 100004;
    public static final int MEAL_ID4 = 100005;
    public static final int MEAL_ID5= 100009;
    public static final int MEAL_ID6= 100012;
    public static final int MEAL_ID7= 100013;
    public static final int MEAL_ID2 = 100007;
    public static final int MEAL_ID = 100011;
    public static final int NOT_FOUND = 10;

    public static final Meal meal =  new Meal(MEAL_ID, LocalDateTime.of(2021, 10, 25, 20, 00, 00), "Ужин", 600);
    public static final Meal meal2 =  new Meal(MEAL_ID2, LocalDateTime.of(2021, 10, 23, 13, 32, 00), "Обед", 730);
    public static final Meal meal7 =  new Meal(MEAL_ID7, LocalDateTime.of(2021, 10, 24, 13, 32, 00), "Обед", 930);
    public static final Meal meal3 =  new Meal(MEAL_ID3, LocalDateTime.of(2021, 10, 23, 10, 28, 8),"Завтрак", 500);
    public static final Meal meal4 =  new Meal(MEAL_ID4, LocalDateTime.of(2021, 10, 24, 10, 28, 8),"Завтрак", 500);
    public static final Meal meal5 =  new Meal(MEAL_ID5, LocalDateTime.of(2021, 10, 25, 13, 32, 00),"Обед", 730);
    public static final Meal meal6 =  new Meal(MEAL_ID6, LocalDateTime.of(2021, 10, 24, 20, 00, 00), "Ужин", 900);

    public static Meal getUpdated() {
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.of(2023, 10, 23, 13, 32, 00));
        updated.setDescription("Поздний ужин");
        updated.setCalories(1530);
        return updated;
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "Новая еда", 600);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }
}
