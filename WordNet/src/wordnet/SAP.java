package wordnet;

import utils.BreadthFirstPaths;
import utils.Digraph;

import java.util.Arrays;
import java.util.Collections;


public class SAP {
    private Digraph storage;

    public SAP(Digraph g) {
        System.out.println(g.vertex_cnt());
        storage = g;
    }

    public int length(int v, int w) {
        assertVerts(v, w);
        return length_btwn(v, w);
    }

    public int ancestor(int v, int w) {
        assertVerts(v, w);
        return ancestor0(v, w);
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        assertVerts(v);
        assertVerts(w);
        return length_btwn(v, w);
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        assertVerts(v);
        assertVerts(w);
        return ancestor0(v, w);
    }

    private void assertVerts(Integer... vertices) {
        assertVerts(Arrays.asList(vertices));
    }

    private void assertVerts(Iterable<Integer> vertices) {
        for (int x : vertices) {
            if (x < 0 || x >= storage.vertex_cnt()) {
                throw new IndexOutOfBoundsException(
                        String.format(
                                "Integer %d does not fall in the range of the digraph (0 to %d)",
                                x, storage.vertex_cnt()));
            }
        }
    }

    private int length_btwn(final int v, final int w) {
        return length_btwn(Collections.singletonList(v), Collections.singletonList(w));
    }

    private int length_btwn(Iterable<Integer> v, Iterable<Integer> w) {
        int res = -1;

        BreadthFirstPaths bfsV = new BreadthFirstPaths(storage,
                v);
        BreadthFirstPaths bfsW = new BreadthFirstPaths(storage,
                w);

        for (int i = 0; i < storage.vertex_cnt(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dist = bfsV.distTo(i)+bfsW.distTo(i);
                if (res < 0 || dist < res) {
                    res = dist;
                }
            }
        }
        return res;
    }

    private int ancestor0(int v, int w) {
        return ancestor0(Collections.singletonList(v), Collections.singletonList(w));
    }

    private int ancestor0(Iterable<Integer> v, Iterable<Integer> w) {
        int res  = -1;
        int min_dst = -1;

        BreadthFirstPaths bfsV = new BreadthFirstPaths(storage,
                v);
        BreadthFirstPaths bfsW = new BreadthFirstPaths(storage,
                w);

        for (int i = 0; i < storage.vertex_cnt(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dist = bfsV.distTo(i)+bfsW.distTo(i);
                if (min_dst < 0 || dist < min_dst) {
                    min_dst = dist;
                    res = i;
                }
            }
        }
        return res;
    }
}
