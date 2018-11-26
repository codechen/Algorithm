package com.htmm.myapplication;

/**
 * Msg:稠密图--邻接矩阵表示
 * Update:  2018-7-4
 * Version: 1.0
 * Created by chenchao on 2018-7-4 14:39.
 */
public class DenseGraphComponent {
    private DenseGraph denseGraph;
    private boolean[] visited;
    private int[] id;
    private int ccount;

    public DenseGraphComponent(DenseGraph denseGraph) {
        this.denseGraph = denseGraph;
        this.visited = new boolean[denseGraph.V()];
        this.id = new int[denseGraph.V()];
        this.ccount = 0;
        for (int i = 0; i < denseGraph.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < denseGraph.V(); i++)
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
        DenseGraph.Iterator denseIterator = new DenseGraph.Iterator(denseGraph, v);

        for (int i = denseIterator.begin(); !denseIterator.end(); i = denseIterator.next())
            if (!visited[i]) {
                dfs(i);
            }
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
        assert v >= 0 && v < denseGraph.V();
        assert w >= 0 && w < denseGraph.V();
        return id[v] == id[w];
    }
}
