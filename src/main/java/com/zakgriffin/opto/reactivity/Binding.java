package com.zakgriffin.opto.reactivity;

import com.zakgriffin.opto.objects.O;

import java.util.*;
import java.util.function.Supplier;

public class Binding {
    static ObservableMap<Observable<O>, Binding> obsToBindings = new ObservableMap<>();
    public static Observable<Binding> getObsBinding(Observable<O> obs) {
        return obsToBindings.computeIfAbsent(obs);
    }

    public static void addBinding(Observable<O> obs, Binding binding) {
        obsToBindings.put(obs, binding);
    }

//    private Binding(Observable<?> observable) {
//        observable.addListener((oldValue, newValue) -> {
//            for (var dependingOn : dependingOns) {
//                if (bindingsToUpdate.contains(dependingOn)) continue;
//
//                bindingsToUpdate.add(dependingOn);
//            }
//            if (!alreadyUpdating) updateAll();
//        });
//    }


//    public static <T> Binding createBinding(Observable<T> observable, Supplier<T> supplier, List<Observable<?>> dependencies) {
//        Binding binding = obsToBinding.computeIfAbsent(observable, Binding::new);
//        binding.update = () -> observable.set(supplier.get());
//
//        for (Observable<?> dependency : dependencies) {
//            Binding dependencyBinding = obsToBinding.computeIfAbsent(dependency, Binding::new);
//            dependencyBinding.dependingOns.add(binding);
//            dependencyBinding.propagateLevel();
//        }
//
////        for(var x : obsToBinding.values()) {
////            System.out.println(x);
////        }
//
//        return binding;
//    }

//    static Map<Observable<String>, String> all = new HashMap<>();
//    public static void main(String[] args) {
////        Observable<String> in = new Observable<>();
////        Observable<String> mid1 = new Observable<>();
////        Observable<String> mid2 = new Observable<>();
////        Observable<String> mid3 = new Observable<>();
////        Observable<String> out = new Observable<>();
////
////        createBinding(mid1, () -> in.get() + "!", List.of(in));
////        createBinding(mid2, () -> in.get() + "!", List.of(in));
////        createBinding(mid3, () -> in.get() + "!", List.of(in));
////        createBinding(out, () -> mid1.get() + mid2.get() + mid3.get(), List.of(mid1, mid2, mid3));
////
////        in.set("o");
////        System.out.println(out.get());
////        updateAll();
//
//        Observable<String> A = new Observable<>();
//        Observable<String> B = new Observable<>();
//        Observable<String> C = new Observable<>();
//        Observable<String> D = new Observable<>();
//        Observable<String> E = new Observable<>();
//        Observable<String> F = new Observable<>();
//        Observable<String> G = new Observable<>();
//        Observable<String> H = new Observable<>();
//        Observable<String> I = new Observable<>();
//        Observable<String> J = new Observable<>();
//        Observable<String> K = new Observable<>();
//        Observable<String> L = new Observable<>();
//        Observable<String> M = new Observable<>();
//        Observable<String> N = new Observable<>();
//
//        all.put(A, "A");
//        all.put(B, "B");
//        all.put(C, "C");
//        all.put(D, "D");
//        all.put(E, "E");
//        all.put(F, "F");
//        all.put(G, "G");
//        all.put(H, "H");
//        all.put(I, "I");
//        all.put(J, "J");
//        all.put(K, "K");
//        all.put(L, "L");
//        all.put(M, "M");
//        all.put(N, "N");
//
//        createBinding(C, A::get, List.of(A));
//        createBinding(D, () -> x(A.get()) + x(B.get()), List.of(A, B));
//        createBinding(E, () -> x(B.get()), List.of(B));
//        createBinding(F, () -> x(B.get()), List.of(B));
//        createBinding(G, () -> x(C.get()) + x(H.get()), List.of(C, H));
//        createBinding(H, () -> x(C.get()) + x(D.get()), List.of(C, D));
//        createBinding(I, () -> x(E.get()) + x(H.get()), List.of(E, H));
//        createBinding(K, () -> x(F.get()) + x(I.get()), List.of(F, I));
//        createBinding(J, () -> x(F.get()), List.of(F));
//        createBinding(L, () -> x(N.get()) + x(I.get()), List.of(N, I));
//        createBinding(M, () -> x(N.get()), List.of(N));
//        createBinding(N, () -> x(H.get()), List.of(H));
//
//        System.out.println("A level: " + obsToBinding.get(A).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("B level: " + obsToBinding.get(B).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("C level: " + obsToBinding.get(C).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("D level: " + obsToBinding.get(D).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("E level: " + obsToBinding.get(E).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("F level: " + obsToBinding.get(F).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("G level: " + obsToBinding.get(G).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("H level: " + obsToBinding.get(H).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("I level: " + obsToBinding.get(I).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("J level: " + obsToBinding.get(J).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("K level: " + obsToBinding.get(K).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("L level: " + obsToBinding.get(L).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("M level: " + obsToBinding.get(M).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//        System.out.println("N level: " + obsToBinding.get(N).dependingOns.stream().map(q -> all.get(q.observable)).toList());
//
//        batchUpdate(() -> {
//            B.set("B");
//            A.set("A");
//        });
//
//        System.out.println("A: " + A.get());
//        System.out.println("B: " + B.get());
//        System.out.println("C: " + C.get());
//        System.out.println("D: " + D.get());
//        System.out.println("E: " + E.get());
//        System.out.println("F: " + F.get());
//        System.out.println("G: " + G.get());
//        System.out.println("H: " + H.get());
//        System.out.println("I: " + I.get());
//        System.out.println("J: " + J.get());
//        System.out.println("K: " + K.get());
//        System.out.println("L: " + L.get());
//        System.out.println("M: " + M.get());
//        System.out.println("N: " + N.get());
//    }
//    private static String x(String z) {
//        return "(" + z + ")";
//    }

}
