package ru.mic.service;

import ru.mic.model.Restaurant;
import ru.mic.to.RestaurantWithVotes;

import java.util.List;

public interface RestaurantService {

    List<RestaurantWithVotes> getAll();

}
