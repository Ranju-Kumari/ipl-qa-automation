package com.ipl.models;

public class TeamRow implements Comparable<TeamRow>{

    String name;
    int points;
    double netRunRate;

    public TeamRow(String name, int points, double netRunRate){
        this.name=name;
        this.points=points;
        this.netRunRate=netRunRate;
    }


    @Override
    public int compareTo(TeamRow team) {
        if(this.points!=team.points){
            return Integer.compare(this.points,team.points);//Sort Points Descending
        }
        return Double.compare(this.netRunRate,team.netRunRate); // Sort NRR Descending on tie
    }
}
