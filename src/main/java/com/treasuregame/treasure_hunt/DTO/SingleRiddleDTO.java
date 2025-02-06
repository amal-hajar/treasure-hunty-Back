package com.treasuregame.treasure_hunt.DTO;

public class SingleRiddleDTO {
    private final Integer id;
    private final String name;
    private final String text;
    private final String answer;
    private final boolean solved;
    private final Double latitude;
    private  final Double longitude;

    public SingleRiddleDTO(Integer id, String name, String text, String answer, boolean solved, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.answer = answer;
        this.solved = solved;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAnswer() {
        return answer;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}

