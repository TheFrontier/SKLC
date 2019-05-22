package frontier.sklc.core.simple

import frontier.sklc.core.Cache
import frontier.sklc.core.SuspendingCache

class SuspendedCache<K : Any, V : Any>(val cache: Cache<K, V>) : SuspendingCache<K, V> {

    override suspend fun count(): Int = cache.count()

    override suspend fun keys(): Set<K> = cache.keys()

    override suspend fun values(): Sequence<V> = cache.values().asSequence()

    override suspend fun contains(key: K): Boolean = cache.contains(key)

    override suspend fun fetch(key: K): V? = cache.fetch(key)

    override suspend fun set(key: K, value: V): Boolean = cache.set(key, value)

    override suspend fun remove(key: K): Boolean = cache.remove(key)

    override suspend fun clear() = cache.clear()
}