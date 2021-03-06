package com.github.davidmoten.rtree;

import static com.github.davidmoten.rtree.Utilities.entries1000;

import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import rx.Subscriber;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

@State(Scope.Benchmark)
public class BenchmarksRTree {

    private final List<Entry<Object, Point>> entries = GreekEarthquakes.entriesList();

    private final List<Entry<Object, Rectangle>> some = entries1000();

    private final RTree<Object, Point> defaultTreeM4 = RTree.maxChildren(4)
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> defaultTreeM10 = RTree.maxChildren(10)
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> starTreeM4 = RTree.maxChildren(4).star()
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> starTreeM10 = RTree.maxChildren(10).star()
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> defaultTreeM32 = RTree.maxChildren(32)
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> starTreeM32 = RTree.maxChildren(32).star()
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> defaultTreeM128 = RTree.maxChildren(128)
            .<Object, Point> create().add(entries);

    private final RTree<Object, Point> starTreeM128 = RTree.maxChildren(128).star()
            .<Object, Point> create().add(entries);

    private final RTree<Object, Rectangle> smallDefaultTreeM4 = RTree.maxChildren(4)
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallDefaultTreeM10 = RTree.maxChildren(10)
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallStarTreeM4 = RTree.maxChildren(4).star()
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallStarTreeM10 = RTree.maxChildren(10).star()
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallDefaultTreeM32 = RTree.maxChildren(32)
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallStarTreeM32 = RTree.maxChildren(32).star()
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallDefaultTreeM128 = RTree.maxChildren(128)
            .<Object, Rectangle> create().add(some);

    private final RTree<Object, Rectangle> smallStarTreeM128 = RTree.maxChildren(128).star()
            .<Object, Rectangle> create().add(some);

    @Benchmark
    public void defaultRTreeInsertOneEntryIntoGreekDataEntriesMaxChildren004() {
        insertPoint(defaultTreeM4);
    }

    @Benchmark
    public void defaultRTreeSearchOfGreekDataPointsMaxChildren004() {
        searchGreek(defaultTreeM4);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryIntoGreekDataEntriesMaxChildren010() {
        insertPoint(defaultTreeM10);
    }

    @Benchmark
    public void defaultRTreeSearchOfGreekDataPointsMaxChildren010() {
        searchGreek(defaultTreeM10);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryIntoGreekDataEntriesMaxChildren004() {
        insertPoint(starTreeM4);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryIntoGreekDataEntriesMaxChildren010() {
        insertPoint(starTreeM10);
    }

    @Benchmark
    public void rStarTreeSearchOfGreekDataPointsMaxChildren004() {
        searchGreek(starTreeM4);
    }

    @Benchmark
    public void rStarTreeSearchOfGreekDataPointsMaxChildren010() {
        searchGreek(starTreeM10);
    }

    @Benchmark
    public void rStarTreeSearchOfGreekDataPointsMaxChildren010WithBackpressure() {
        searchGreekWithBackpressure(starTreeM10);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryIntoGreekDataEntriesMaxChildren032() {
        insertPoint(defaultTreeM32);
    }

    @Benchmark
    public void defaultRTreeSearchOfGreekDataPointsMaxChildren032() {
        searchGreek(defaultTreeM32);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryIntoGreekDataEntriesMaxChildren032() {
        insertPoint(starTreeM32);
    }

    @Benchmark
    public void rStarTreeSearchOfGreekDataPointsMaxChildren032() {
        searchGreek(starTreeM32);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryIntoGreekDataEntriesMaxChildren128() {
        insertPoint(defaultTreeM128);
    }

    @Benchmark
    public void defaultRTreeSearchOfGreekDataPointsMaxChildren128() {
        searchGreek(defaultTreeM128);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryIntoGreekDataEntriesMaxChildren128() {
        insertPoint(starTreeM128);
    }

    @Benchmark
    public void rStarTreeSearchOfGreekDataPointsMaxChildren128() {
        searchGreek(starTreeM128);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryInto1000EntriesMaxChildren004() {
        insertRectangle(smallDefaultTreeM4);
    }

    @Benchmark
    public void defaultRTreeSearchOf1000PointsMaxChildren004() {
        search(smallDefaultTreeM4);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryInto1000EntriesMaxChildren010() {
        insertRectangle(smallDefaultTreeM10);
    }

    @Benchmark
    public void defaultRTreeSearchOf1000PointsMaxChildren010() {
        search(smallDefaultTreeM10);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryInto1000EntriesMaxChildren004() {
        insertRectangle(smallStarTreeM4);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryInto1000EntriesMaxChildren010() {
        insertRectangle(smallStarTreeM10);
    }

    @Benchmark
    public void rStarTreeSearchOf1000PointsMaxChildren004() {
        search(smallStarTreeM4);
    }

    @Benchmark
    public void rStarTreeSearchOf1000PointsMaxChildren010() {
        search(smallStarTreeM10);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryInto1000EntriesMaxChildren032() {
        insertRectangle(smallDefaultTreeM32);
    }

    @Benchmark
    public void defaultRTreeSearchOf1000PointsMaxChildren032() {
        search(smallDefaultTreeM32);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryInto1000EntriesMaxChildren032() {
        insertRectangle(smallStarTreeM32);
    }

    @Benchmark
    public void rStarTreeSearchOf1000PointsMaxChildren032() {
        search(smallStarTreeM32);
    }

    @Benchmark
    public void defaultRTreeInsertOneEntryInto1000EntriesMaxChildren128() {
        insertRectangle(smallDefaultTreeM128);
    }

    @Benchmark
    public void defaultRTreeSearchOf1000PointsMaxChildren128() {
        search(smallDefaultTreeM128);
    }

    @Benchmark
    public void rStarTreeInsertOneEntryInto1000EntriesMaxChildren128() {
        insertRectangle(smallStarTreeM128);
    }

    @Benchmark
    public void rStarTreeSearchOf1000PointsMaxChildren128() {
        search(smallStarTreeM128);
    }

    @Benchmark
    public void rStarTreeDeleteOneEveryOccurrenceFromGreekDataChildren010() {
        deleteAll(starTreeM10);
    }

    private void deleteAll(RTree<Object, Point> tree) {
        tree.delete(entries.get(1000), true);
    }

    private void search(RTree<Object, Rectangle> tree) {
        // returns 10 results
        tree.search(Geometries.rectangle(500, 500, 630, 630)).subscribe();
    }

    private void searchGreek(RTree<Object, Point> tree) {
        // should return 22 results
        tree.search(Geometries.rectangle(40, 27.0, 40.5, 27.5)).subscribe();
    }

    private void searchGreekWithBackpressure(RTree<Object, Point> tree) {
        // should return 22 results
        tree.search(Geometries.rectangle(40, 27.0, 40.5, 27.5)).subscribe(new Subscriber<Object>() {

            @Override
            public void onStart() {
                request(1);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable arg0) {

            }

            @Override
            public void onNext(Object arg0) {
                request(1);
            }
        });
    }

    private void insertRectangle(RTree<Object, Rectangle> tree) {
        tree.add(new Object(), RTreeTest.random());
    }

    private void insertPoint(RTree<Object, Point> tree) {
        tree.add(new Object(), Geometries.point(Math.random() * 1000, Math.random() * 1000));
    }

    public static void main(String[] args) {
        BenchmarksRTree b = new BenchmarksRTree();
        while (true)
            b.searchGreek(b.starTreeM10);
    }
}
