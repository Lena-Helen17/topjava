package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID3 = 100002;
    public static final int MEAL_ID2 = 100003;
    public static final int MEAL_ID = 100005;
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final Meal meal =  new Meal(MEAL_ID, LocalDateTime.of(2021, 10, 25, 20, 00, 00), "Ужин", 600);

    public static final List<Meal> mealsList = Arrays.asList(
            meal,
            new Meal(MEAL_ID2, LocalDateTime.of(2021, 10, 23, 13, 32, 00), "Обед", 730),
            new Meal(MEAL_ID3, LocalDateTime.of(2021, 10, 23, 10, 28, 8),"Завтрак", 500)
    );

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

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("datetime").isEqualTo(expected);
    }
}
