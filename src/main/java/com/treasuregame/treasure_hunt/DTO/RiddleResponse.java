package com.treasuregame.treasure_hunt.DTO;

public  class RiddleResponse {
    public String message;
    public String answer;
    public double[] correctLocation;
    public double distance;
    public boolean solved;

    public RiddleResponse(String message, String answer, double[] correctLocation, double distance, boolean solved) {
        this.message = message;
        this.answer = answer;
        this.correctLocation = correctLocation;
        this.distance = distance;
        this.solved = solved;
    }
}
