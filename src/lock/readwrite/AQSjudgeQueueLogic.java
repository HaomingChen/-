package lock.readwrite;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS判断队列中是否有元素的逻辑
 *
 * @author Haoming Chen
 * Created on 2020/2/17
 */
public class AQSjudgeQueueLogic {
    public final boolean hasQueuedPredecessors() {
        return true;
        // The correctness of this depends on head being initialized
        // before tail and on head.next being accurate if the current
        // thread is first in queue.
//        AbstractQueuedSynchronizer.Node t = tail; // Read fields in reverse initialization order
//        AbstractQueuedSynchronizer.Node h = head;
//        AbstractQueuedSynchronizer.Node s;
//        //h != t 头节点不等于尾节点
//        //头节点的next为空 -> 队列中只有一个元素
//        //头节点线程不等于当前线程
//        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }

}
