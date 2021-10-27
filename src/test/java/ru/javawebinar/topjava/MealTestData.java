package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID3 = START_SEQ + 4;
    public static final int MEAL_ID4 = START_SEQ + 5;
    public static final int MEAL_ID5= START_SEQ + 9;
    public static final int MEAL_ID6= START_SEQ + 12;
    public static final int MEAL_ID7= START_SEQ + 13;
    public static final int MEAL_ID2 = START_SEQ +7;
    public static final int MEAL_ID = START_SEQ + 11;
    public static final int NOT_FOUND = START_SEQ - 20;

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
        return new Meal(null, LocalDateTime.of(2222, 02, 22, 22, 22, 22), "Новая еда", 600);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
