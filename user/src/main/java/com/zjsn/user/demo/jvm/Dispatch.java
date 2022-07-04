package com.zjsn.user.demo.jvm;

/**
 *  我们关注的首先是编译阶段中编译器的选择过程，也就是静态分派的过程。
 *  这时候选 择目标方法的依据有两点：一是静态类型是Father还是Son，二是方法参数是QQ还是360。
 *  这次选择结 果的最终产物是产生了两条invokevirtual指令，两条指令的参数分别为常量池中指向 Father::hardChoice(360)及Father::hardChoice(QQ)方法的符号引用。
 *  因为是根据两个宗量进行选择，所以 Java语言的静态分派属于多分派类型
 *
 *
 *  再看看运行阶段中虚拟机的选择，也就是动态分派的过程。
 *  在执行“son.hardChoice(new QQ())”这 行代码时，
 *  更准确地说，是在执行这行代码所对应的invokevirtual指令时，由于编译期已经决定目标方 法的签名必须为hardChoice(QQ)，
 *  虚拟机此时不会关心传递过来的参数“QQ”到底是“腾讯QQ”还是“奇 瑞QQ”，因为这时候参数的静态类型、实际类型都对方法的选择不会构成任何影响，
 *  唯一可以影响虚 拟机选择的因素只有该方法的接受者的实际类型是Father还是Son。因为只有一个宗量作为选择依据，
 *  所以Java语言的动态分派属于单分派类型
 */
public class Dispatch {
    static class QQ {};
    static class _360{};
    public static class Father {
        public void hardChoose(QQ arg) {
            System.out.println("father choose qq");
        }
        public void hardChoose(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoose(QQ arg) {
            System.out.println("son choose qq");
        }
        public void hardChoose(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
         Father father = new Father();
         Father guy = new Son();
         father.hardChoose(new _360());
         guy.hardChoose(new QQ());
    }
}
