package interviewqs;

/**
 * 创建两个线程，其中一个输出1-52，另外一个输出A-Z。输出格式要求：12A 34B 56C 78D ...
 *
 * @author Haoming Chen
 * Created on 2020/1/7
 */
public class MultiThread_souhu02 {

    public static void main(String[] args) {
        Print pt = new Print();
        PrintNum printNum = new PrintNum(pt);
        PrintLetter printLetter = new PrintLetter(pt);
        new Thread(printNum).start();
        new Thread(printLetter).start();
    }

}

class Print {

    int count = 1;
    int num = 1;
    char letter = 'A';

    public synchronized void printNum() throws InterruptedException {
        while (count % 3 == 0) {
            wait();
        }
        System.out.print(num);
        num = num + 1;
        count++;
        notify();
    }

    public synchronized void printLetter() throws InterruptedException {
        while (count % 3 != 0) {
            wait();
        }
        System.out.print(letter + " ");
        letter = (char) (letter + 1);
        count++;
        notify();
    }
}

class PrintNum implements Runnable {

    Print print;

    public PrintNum(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 0; i < 52; i++) {
            try {
                print.printNum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintLetter implements Runnable {

    Print print;

    public PrintLetter(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 0; i < 26; i++) {
            try {
                print.printLetter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

