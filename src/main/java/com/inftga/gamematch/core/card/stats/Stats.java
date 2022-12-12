package com.inftga.gamematch.core.card.stats;

public class Stats {


    private int cost;
    private int life;
    private int maxLife;
    private int armor;
    private int magicArmor;
    private int demage;
    private int magicDemage;

    public Stats(int maxlife, int armor, int magicArmor, int demage, int magicDemage, int cost) {
        this.maxLife = maxlife;
        this.life = maxlife;
        this.armor = armor;
        this.magicArmor = magicArmor;
        this.demage = demage;
        this.magicDemage = magicDemage;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getArmor() {
        return armor;
    }

    public int getMagicArmor() {
        return magicArmor;
    }

    public int getDemage() {
        return demage;
    }

    public int getMagicDemage() {
        return magicDemage;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setMagicArmor(int magicArmor) {
        this.magicArmor = magicArmor;
    }

    public void setDemage(int demage) {
        this.demage = demage;
    }

    public void setMagicDemage(int magicDemage) {
        this.magicDemage = magicDemage;
    }

    public Stats clone(){
        Stats attributes = new Stats(maxLife,armor,magicArmor,demage,magicDemage,cost);
        return attributes;
    }
}
