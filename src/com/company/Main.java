package com.company;

import java.util.ArrayList;
import jgraphics.tool.DefaultDialogTraces;
import jgraphics.tracer.GraphicsComponentJChart2D;
import jgraphics.tracer.ModelingGraphicsComponent;
import jgraphics.tracer.NavigableGraphicsComponent;


public class Main {

    public static void main(String[] args) {
        ModelingGraphicsComponent tracer = new ModelingGraphicsComponent(new NavigableGraphicsComponent(new GraphicsComponentJChart2D(), 3));
        int n, s;
        double tci, tco, thi, tho, dt, T, t, t2; // h - hot, c - cold, i - in, o - out
        ArrayList<Double> test = new ArrayList<Double>(1000);
        dt = 0.1;
        T=15;
        tci = 36.0;
        tco = 454.0;
        thi = 481.0;
        tho = 156.0;
        t = tci;
        t2 = t;
        test.add(t);
        for(n=1; t<tco-0.1; n++) {
            t = t2 + (1 / T) * ((tco / tci) * tci - t2) * dt;
            t2 = t;
            test.add(t);
        }
        s = test.size();
        Double[] ctemp = new Double[s];
        Double[] htemp = new Double[s];

        // Основная часть

        t = tci;
        t2 = t;
        ctemp[0] = t;
        for (n = 1; n<=s-1; n++) {
            t = t2 + (1 / T) * ((tco / tci) * tci - t2) * dt;
            t2 = t;
            ctemp[n] = t;
        }
        t = thi;
        t2 = t;
        htemp[0] = t;
        for (n = 1; n<=s-1; n++) {
            t = t2 + (1 / T) * ((tho / thi) * thi - t2) * dt;
            t2 = t;
            htemp[n] = t;
        }
        System.out.println("Cold");
        for (int i = 0; i < s; i++) {
            System.out.println(ctemp[i]);}
        System.out.println("Hot");
        for (int i = 0; i < s; i++) {
            System.out.println(htemp[i]);}


        tracer.addTrace("cold", ctemp, 0.0, 0.1);
        tracer.addTrace("hot", htemp, 0.0, 0.1);
        DefaultDialogTraces dialog = new DefaultDialogTraces(null, "T=3");
        dialog.show(tracer.getTracesComponent());
    }
}
