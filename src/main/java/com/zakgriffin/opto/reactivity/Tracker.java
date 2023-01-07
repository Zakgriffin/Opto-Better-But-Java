package com.zakgriffin.opto.reactivity;

import java.util.ArrayList;
import java.util.List;

public class Tracker<T> {
    Signal signal;
    T value;

    public T get() {
        return value;
    }

    public void set(T newValue) {
        signal.update();
    }

    public void addListener(TrackChangeListener<T> changeListener) {
        changeListeners.add(new Signal(() -> {
            T oldValue = value;
            this.value = newValue;
            for(TrackChangeListener<T> changeListener : changeListeners) {
                changeListener.onChange(oldValue, newValue);
            }
            changeListener.onChange();
        }));
    }

    public void removeListener(TrackChangeListener<T> changeListener) {
        changeListeners.remove(changeListener);
    }

    public static void main(String[] args) {
//        Observable<String> F = new Observable<>();
//
//        class BType {
//            Observable<String> C;
//        }
//
//        class AType {
//            Observable<BType> B;
//        }
//
//
//        class PType {
//            Observable<String> X;
//            Observable<String> Y;
//
//            Observable<String> f() {
//                return F;
//            }
//        }
//        Observable<PType> P = new Observable<>();
//        Observable<AType> A = new Observable<>();
//
//        // P.X = f(A)
//
//
//
////        addChild(A, B);
////        addChild(B, C);
//
//        // P.Y = A.B.C
//        d(P, p -> d(p.Y, y -> d()));
//
//        Observable yChild = P.child(_P -> _P.Y);
//
//
//
//        Jumbo<BCDE> A_ = context.use(A);
//        Jumbo<CDE> B_ = context.child(A_, A__ -> A__.B);
//
//        Observable<String> x = bound(() -> {
//            context.mmm(() -> f(A_.v));
//        }, context);
//
//
//        bind();
//



        // signals, point to other signals to update after
        // when signals update, should update all dependant signals only once

        // observables hold data, can be updated with new object
        // can have children, can listen to reassignment
        // can listen to child updates propagated all the way up

        // bindings can be added and removed

        // for now, no cyclic dependencies, attempting one will cause failure indication "red arrow"

        // A <- B   :   A is bound to B
        // A <- (B + C)   :   A is bound to B + C
        // A.X <- (B + C)   :
        // A.X <- B.y   :


        // math_expression ⊂ any
        //
        // math_expression =
        //    left: any
        //    right: any
        //
        // reduce =
        //   for_all math_expression ([it] > (reduce[it]: (number or none)))
        //
        // simplified: [e: math_expression] > (number or none) =
        //   if both {x.left x.right} (∈ math_expression)
        //     reduce[simplified[x.left], simplified[x.right]
        //   else
        //     None


        // root_expression

        // tree: [node: math_expression, pos: point] > =
        //   circle = circle(point)
        //   if node.left ∈ math_expression
        //       left_tree = tree[node.left, +[pos, (-20, 20)]
        //       line[circle.center, left_tree.circle]
        //   if node.right ∈ math_expression
        //       right_tree = tree[node.right, +[pos, (20, 20)]
        //       line[circle.center, right_tree.circle]
    }
}