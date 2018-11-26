package com.htmm.myapplication;

/**
 * Msg:并查集
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class UnionFind {
    private int[] id;
    private int count;

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int find(int p) {
        assert p >= 0 && p < count;
        return id[p];
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId)
            return;

        for (int i = 0; i < count; i++)
            if (id[i] == pId)
                id[i] = qId;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
