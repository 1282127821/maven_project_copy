final Object[] items;//这个是 这个队列的核心数组

public E take() throws InterruptedException {//队列拿数据
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)//如果items 的个数 为0 即为空队列，此时notEmpty阻塞
                notEmpty.await();
            return dequeue();//不为空队列的时候执行，从队列拿出元素(拿出后移除队列的那个元素)
        } finally {
            lock.unlock();
        }
    }
public void put(E e) throws InterruptedException {//放入数据到队列
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)//如果count大小等于items大小说明 队列满了，此时无法放入元素，就阻塞住
                notFull.await();
            enqueue(e);//放入队列
        } finally {
            lock.unlock();
        }
    }
    private void enqueue(E x) {//放入队列items （实际操作）
            // assert lock.getHoldCount() == 1;
            // assert items[putIndex] == null;
            final Object[] items = this.items;
            items[putIndex] = x;
            if (++putIndex == items.length)
                putIndex = 0;
            count++;//修改队列 数目+1
            notEmpty.signal();// 有数据了  唤醒 之前由于空队列 而阻塞的线程
        }
    private E dequeue() {//从队列items拿元素 （实际操作）
            // assert lock.getHoldCount() == 1;
            // assert items[takeIndex] != null;
            final Object[] items = this.items;
            @SuppressWarnings("unchecked")
            E x = (E) items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length)
                takeIndex = 0;
            count--;//修改队列 数目 -1
            if (itrs != null)
                itrs.elementDequeued();
            notFull.signal();//唤醒之前由于队列满了 阻塞的线程
            return x;
        }

