package test;

/**
 * try、catch、finally 考察，请指出下面程序的运行结果
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        /**
         * 执行结果：31
         */
        System.out.println(test());

        /**
         * 执行结果：3。
         * 这题有点先将，但也不难，try 返回前先执行 finally，结果 finally 里不按套路出牌，直接 return 了，
         * 自然也就走不到 try 里面的 return 了。
         * finally 里面使用 return 仅存在于面试题中，实际开发中千万不要这么用。
         */
        System.out.println(test1());

        /**
         * 执行结果：2。
         * 这边估计有不少同学会以为结果应该是 3，
         * 因为我们知道在 return 前会执行 finally，而 i 在 finally 中被修改为 3 了，那最终返回 i 不是应该为 3 吗？
         * 确实很容易这么想，我最初也是这么想的，当初的自己还是太年轻了啊。
         * 这边的根本原因是，在执行 finally 之前，JVM 会先将 i 的结果暂存起来，然后 finally 执行完毕后，会返回之前暂存的结果，而不是返回 i，
         * 所以即使这边 i 已经被修改为 3，最终返回的还是之前暂存起来的结果 2。
         * 这边其实根据字节码可以很容易看出来，在进入 finally 之前，JVM 会使用 iload、istore 两个指令，将结果暂存，
         * 在最终返回时在通过 iload、ireturn 指令返回暂存的结果。
         */
        System.out.println(test2());
    }
    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            System.out.print("3");
        }
    }
    public static int test1() {
        try {
            return 2;
        } finally {
            return 3;
        }
    }
    public static int test2() {
        int i = 0;
        try {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }
}
