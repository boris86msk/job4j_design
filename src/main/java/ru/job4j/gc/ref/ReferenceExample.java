package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceExample {
    public static void main(String[] args) {
        SoftReference<PrintSR> soft = new SoftReference<>(new PrintSR());
        WeakReference<PrintWR> weak = new WeakReference<>(new PrintWR());

        PrintSR strong = soft.get();
        if (strong != null) {
            strong.print();
        } else {
            System.out.println("Soft reference is NULL");
        }

        PrintWR strong2 = weak.get();
        if (strong2 != null) {
            strong2.print();
        } else {
            System.out.println("Weak reference is NULL");
        }

    }
    static class PrintSR {
        public void print() {
            System.out.println(" * *   * * * ");
            System.out.println("*   *  *    *");
            System.out.println("*      *    *");
            System.out.println(" * *   * * * ");
            System.out.println("    *  *   * ");
            System.out.println("*   *  *    *");
            System.out.println(" * *   *    *");
        }
    }
    static class PrintWR {
        public void print() {
            System.out.println("*       *  * * * ");
            System.out.println("*       *  *    *");
            System.out.println("*       *  *    *");
            System.out.println("*       *  * * * ");
            System.out.println("*   *   *  *   * ");
            System.out.println("*   *   *  *    *");
            System.out.println(" **   **   *    *");
        }
    }
}
