package deadlock;

import java.awt.*;

/**
 * 描述: 转账时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Object lock = new Object();

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(a, b, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                    return;
                }
                from.balance -= amount;
                to.balance = to.balance + amount;
                System.out.println("成功转账" + amount + "元");
            }
        }
        //当两个账户相互转账的情况发生时, 保持锁获取的一致性
        //破坏了死锁发生的必要条件中的循环等待条件, 线程获取锁的顺序一致
        //扩展知识: Object的哈希码是根据Object在堆中的内存地址经过处理后的结果，
        //因为类在堆中的地址都不同，所以哈希码都不同
        //String -> 只要字符串在堆空间中的地址相同则哈希码也相同
        //Integer\int 只要int的值相同则他们的哈希值也相同
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else {
            //当两个类的HashCode一致时, 先抢锁
            synchronized (lock) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    static class Account {
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }
}