package abushakir.Test;

import abushakir.calander.Bahirehasab;

public class test {
    public static void main(String[] args) {
        Bahirehasab b = new Bahirehasab(2012);
        System.out.println(b.getNewewe().get("date"));
    }

}
