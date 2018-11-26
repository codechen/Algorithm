package com.htmm.myapplication;

import java.util.Random;

public class GraphMain {

    public static void main(String[] args) {
        int n = 30;
        int m = 50;

        System.out.print("\n[DenseGraph]:\n");

        DenseGraph denseGraph = new DenseGraph(n, false);
        for (int i = 0; i < m; i++) {
            int v = new Random().nextInt(n);
            int w = new Random().nextInt(n);
            denseGraph.addEdge(v, w);
        }

        // O(V^2) V:vetex
        for (int v = 0; v < n; v++) {
            System.out.print("\nVetex " + v + " : ");

            DenseGraph.Iterator denseIterator = new DenseGraph.Iterator(denseGraph, v);

            for (int w = denseIterator.begin(); !denseIterator.end(); w = denseIterator.next())
                System.out.print(" " + w);
        }

        System.out.print("\n\n[SparseGraph]:\n");


        // O(E) E:edge
        SparseGraph sparseGraph = new SparseGraph(n, false);
        for (int i = 0; i < m; i++) {
            int v = new Random().nextInt(n);
            int w = new Random().nextInt(n);
            sparseGraph.addEdge(v, w);
        }

        for (int v = 0; v < n; v++) {
            System.out.print("\nVetex " + v + " : ");

            SparseGraph.Iterator sparseIterator = new SparseGraph.Iterator(sparseGraph, v);

            for (int w = sparseIterator.begin(); !sparseIterator.end(); w = sparseIterator.next())
                System.out.print(" " + w);
        }
    }
}
