package deadlock;

/**
 * 银行转账
 *
 * @author 58212
 * @date 2019-11-07 22:49
 */
public class TransferMoney implements Runnable {

    public static void main(String[] args) {
        TransferMoney transferMoney = new TransferMoney(200, 0);
        TransferMoney transferMoney1 = new TransferMoney(200, 1);
        new Thread(transferMoney).start();
        new Thread(transferMoney1).start();
    }

    static Account a = new Account(500);
    static Account b = new Account(500);

    static Object from = new Object();
    static Object to = new Object();

    int amount;
    int flag;

    public TransferMoney(int amount, int flag) {
        this.amount = amount;
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 0) {
            synchronized (from) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    transfer(a, b, 200);
                    System.out.println("account a's balance" + a.balance);
                    System.out.println("account b's balance" + b.balance);
                }
            }
        } else if (flag == 1) {
            synchronized (to) {
                synchronized (from) {
                    transfer(b, a, 200);
                    System.out.println("account a's balance" + a.balance);
                    System.out.println("account b's balance" + b.balance);
                }
            }
        }
    }

    private void transfer(Account from, Account to, int amount) {
        if (from.balance < amount) {
            throw new IllegalArgumentException("余额不足");
        }
        from.balance = from.balance - amount;
        to.balance = to.balance + amount;
    }

    static class Account {

        public int balance;

        public Account(int balance) {
            this.balance = balance;
        }

    }

}
