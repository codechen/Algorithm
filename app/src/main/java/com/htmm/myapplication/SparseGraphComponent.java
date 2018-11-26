package com.htmm.myapplication;

/**
 * Msg:稠密图--邻接矩阵表示
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class SparseGraphComponent {
    private SparseGraph sparseGraph;
    private boolean[] visited;
    private int[] id;
    private int ccount;

    public SparseGraphComponent(SparseGraph sparseGraph) {
        this.sparseGraph = sparseGraph;
        this.visited = new boolean[sparseGraph.V()];
        this.id = new int[sparseGraph.V()];
        this.ccount = 0;
        for (int i = 0; i < sparseGraph.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < sparseGraph.V(); i++)
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
    }

    /**
     * 深度优先遍历
     * @param v
     */
    private void dfs(int v) {
        visited[v] = true;
        id[v] = ccount;
        SparseGraph.Iterator sparseIterator = new SparseGraph.Iterator(sparseGraph, v);

        for (int i = sparseIterator.begin(); !sparseIterator.end(); i = sparseIterator.next())
            if (!visited[i])
                dfs(i);
    }

    /**
     * 获取联通分量数量
     * @return
     */
    public int count() {
        return ccount;
    }

    /**
     * 两个顶点是否联结
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w) {
        assert v >= 0 && v < sparseGraph.V();
        assert w >= 0 && w < sparseGraph.V();
        return id[v] == id[w];
    }
}
