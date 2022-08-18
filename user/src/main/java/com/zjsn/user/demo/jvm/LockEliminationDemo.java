package com.zjsn.user.demo.jvm;

public class LockEliminationDemo {
    /**
     * 源代码是这样子的.
     * 我们也知道，由于String是一个不可变的类，对字符串的连接操作总是通过生成新的String对象来 进行的，因此Javac编译器会对String连接做自动优化@link{com.zjsn.user.demo.jvm.LockEliminationDemo#concatStringJavac(java.lang.String, java.lang.String, java.lang.String)}
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public String concatString(String s1, String s2, String s3) {
        return s1 + s2 + s3;
    }

    /**
     *  因为StringBuffer::append()这个方法里面都是有一个同步块,锁就是stringBuffer这个对象,
     *  虚拟机监视stringBuffer的时候,发现它的作用域始终在 concatStringJavac这个方法里面,其他线程是无法访问到这个里面的.
     *  所以他有锁,但是可以被安全的消除掉.
     */
    public String concatStringJavac(String s1, String s2, String s3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s1);
        stringBuffer.append(s2);
        stringBuffer.append(s3);
        return stringBuffer.toString();
    }

    /**
     *  针对 concatStringJavac 这个方法里面append 三次 就会锁三次,但是
     *  如果虚拟机探测到有这样一串零碎的操作 都对同一个对象加锁，将会把加锁同步的范围扩展（粗化）到整个操作序列的外部，
     *  就是扩展到第一个append()操作之前直至最后一个append()操作之后，这样只需要加锁一次就可以了.
     */
}
