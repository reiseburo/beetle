package rx.curator

import com.github.reiseburo.rx.curator.PathChildren
import org.apache.curator.RetryPolicy
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent
import org.apache.curator.retry.RetryNTimes

import rx.Observable

class PathCacheObservable {
    static void main(String[] arguments) {
        println "Starting PatchCacheObservable experiment"

        RetryPolicy retryPolicy = new RetryNTimes(3, 1000)
        CuratorFramework curator = CuratorFrameworkFactory.newClient('localhost:2181', retryPolicy)
        curator.start()

        boolean waitFor = true

        PathChildren.with(curator).watch('/brokers')
        .flatMap({ PathChildrenCacheEvent ev ->
            if (ev.type == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                return Observable.from(ev.data)
            }
        })
        .subscribe({
            println "Rec: ${it}"
            waitFor = false
        })

        /* since this is evented, we need something to sit around and wait for us */
        synchronized (this) {
            while (waitFor) { wait(500) }
        }
    }
}

