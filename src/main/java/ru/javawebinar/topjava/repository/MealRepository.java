package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meals;

import java.util.Collection;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meals save(Meals meal);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Meals get(int id);

    // ORDERED dateTime desc
    Collection<Meals> getAll();
}
