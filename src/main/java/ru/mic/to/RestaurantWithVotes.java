package ru.mic.to;

public class RestaurantWithVotes {

    private final Integer id;

    private final String name;

    private final String address;

    private final int votes;

    public RestaurantWithVotes(Integer id, String name, String address, int votes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getVotes() {
        return votes;
    }
}
