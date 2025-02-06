package com.treasuregame.treasure_hunt.DTO;

public  class RiddleDTO {
    private Integer id;
    private String name;
    private boolean solved;

    public RiddleDTO(Integer id, String name, boolean solved) {
        this.id = id;
        this.name = name;
        this.solved = solved;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSolved() {
        return solved;
    }
}