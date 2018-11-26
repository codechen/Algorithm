package com.htmm.myapplication;

/**
 * Msg:并查集
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class UnionFind4 {
    private int[] parent; // 父节点集
    private int[] rank; // 树的层级
    private int count;

    public UnionFind4(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找根节点
     *
     * @param p
     * @return
     */
    public int find(int p) {
        assert p >= 0 && p < count;

        // 路径压缩方法一
//        while (p != parent[p]) {
//            parent[p] = parent[parent[p]]; // 路径压缩
//            p = parent[p];
//        }
//
//        return p;

        // 路径压缩方法二
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];


//        路径压缩中对rank好像并没有维护？

//        事实上，这正是我们将这个变量叫做rank而不是叫诸如depth或者height的原因。因为这个rank只是我们做的一个标志当前节点排名的一个数字，当我们引入了路径压缩以后，
//        维护这个深度的真实值相对困难一些，而且实践告诉我们，我们其实不需要真正维持这个值是真实的深度值，我们依然可以以这个rank值作为后续union过程的参考。因为根据我们的路径压缩的过程，
//        rank高的节点虽然被抬了上来，但是整体上，我们的并查集从任意一个叶子节点出发向根节点前进，依然是一个rank逐渐增高的过程。也就是说，这个rank值在经过路径压缩以后，
//        虽然不是真正的深度值，但仍然可以胜任，作为union时的参考。
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 根节点指向另一个树的根节点
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
