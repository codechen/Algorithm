package com.htmm.myapplication;

/**
 * Msg:并查集
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class UnionFind2 {
    private int[] parent; // 父节点集
    private int[] size; // 树的节点数
    private int count;

    public UnionFind2(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        assert p >= 0 && p < count;
        while (p != parent[p])
            p = parent[p]; // 求父节点
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 根节点指向另一个树的根节点
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
