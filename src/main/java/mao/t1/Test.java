package mao.t1;

import org.apache.logging.log4j.core.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Project name(项目名称)：java并发编程_ReentrantReadWriteLock
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/12
 * Time(创建时间)： 13:08
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 锁
     */
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    /**
     * 读
     */
    public static void read()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("尝试获取读锁");
                lock.readLock().lock();
                log.debug("获取到读锁");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    log.debug("释放读锁");
                    lock.readLock().unlock();
                }
            }
        }, "read:" + UUID.randomUUID().toString().substring(0, 6)).start();
    }

    /**
     * 写
     */
    public static void write()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("尝试获取写锁");
                lock.writeLock().lock();
                log.debug("获取到写锁");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    log.debug("释放写锁");
                    lock.writeLock().unlock();
                }
            }
        }, "write:" + UUID.randomUUID().toString().substring(0, 6)).start();
    }

    public static void main(String[] args)
    {
        write();
        write();
    }
}
