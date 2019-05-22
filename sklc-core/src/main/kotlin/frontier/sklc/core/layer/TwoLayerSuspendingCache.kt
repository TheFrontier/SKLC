package frontier.sklc.core.layer

import frontier.sklc.core.SuspendingCache

class TwoLayerSuspendingCache<K : Any, V : Any>(
    private val first: SuspendingCache<K, V>,
    private val second: SuspendingCache<K, V>
) : SuspendingCache<K, V> {

    override suspend fun count(): Int =
        second.count()

    override suspend fun keys(): Set<K> =
        second.keys()

    override suspend fun values(): Sequence<V> =
        second.values()

    override suspend fun fetch(key: K): V? =
        first.fetch(key)
            ?: second.fetch(key)?.also {
                first.set(key, it)
            }

    override suspend fun contains(key: K): Boolean = first.contains(key) || second.contains(key)

    override suspend fun set(key: K, value: V): Boolean = first.set(key, value) and second.set(key, value)

    override suspend fun remove(key: K): Boolean = first.remove(key) and second.remove(key)

    override suspend fun clear() {
        first.clear()
        second.clear()
    }
}