package frontier.sklc.core.layer

import frontier.sklc.core.Cache

class TwoLayerCache<K : Any, V : Any>(
    private val first: Cache<K, V>,
    private val second: Cache<K, V>
) : Cache<K, V> {

    override fun count(): Int =
        second.count()

    override fun keys(): Set<K> =
        second.keys()

    override fun values(): Iterable<V> =
        second.values()

    override fun fetch(key: K): V? =
        first.fetch(key)
            ?: second.fetch(key)?.also {
                first.set(key, it)
            }

    override fun contains(key: K): Boolean =
        first.contains(key) || second.contains(key)

    override fun set(key: K, value: V): Boolean =
        first.set(key, value) and second.set(key, value)

    override fun remove(key: K): Boolean =
        first.remove(key) and second.remove(key)

    override fun clear() {
        first.clear()
        second.clear()
    }

    override fun toMap(): Map<K, V> =
        second.toMap()
}