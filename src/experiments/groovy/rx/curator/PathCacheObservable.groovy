package rx.curator

import org.apache.curator.RetryPolicy
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener
import org.apache.curator.retry.RetryNTimes

import rx.Observable
import rx.Observer
import rx.Subscriber
import rx.observables.BlockingObservable

class PathCacheObservable {
    static void main(String[] arguments) {
        println "Starting PatchCacheObservable experiment"

        RetryPolicy retryPolicy = new RetryNTimes(3, 1000)
        CuratorFramework curator = CuratorFrameworkFactory.newClient('localhost:2181', retryPolicy)
        curator.start()
        PathChildrenCache cache = new PathChildrenCache(curator, '/brokers', true)
        println "Starting dataset: ${cache.currentData}"

        Observable observable = Observable.create { Subscriber subscriber ->
            cache.listenable.addListener({ CuratorFramework cf, PathChildrenCacheEvent ev ->
                println "heard ${ev} from ${cf}"

                /* If we've got our terminate event, properly close the cache and
                 * tell our subscriber, if it exists, to complete
                 */
                if (ev.type == PathChildrenCacheEvent.Type.INITIALIZED) {
                    cache.close()
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted()
                    }
                }
                else {
                    /* emit each event that isn't our terminator */
                    subscriber.onNext(ev)
                }
            } as PathChildrenCacheListener)

            /* Starting with a post-initialized event so we know when to terminate */
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT)
        }

        boolean waitFor = true

        observable.subscribe([
                onError: { Throwable e -> println "error: ${e}"; e.printStackTrace() },
                onCompleted: { waitFor = false; println "complete"; println "Final data: ${cache.currentData}" },
                onNext: { println "received: ${it}" }
        ] as Observer<PathChildrenCacheEvent>)

        /* since this is evented, we need something to sit around and wait for us */
        synchronized (this) {
            while (waitFor) { wait(500) }
        }
    }
}

