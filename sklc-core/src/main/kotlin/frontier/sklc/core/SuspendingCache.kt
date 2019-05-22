package frontier.sklc.core

import frontier.sklc.core.layer.TwoLayerSuspendingCache

interface SuspendingCache<K : Any, V : Any> {

    suspend fun count(): Int

    suspend fun keys(): Set<K>

    suspend fun values(): Sequence<V>

    suspend fun fetch(key: K): V?

    suspend fun contains(key: K): Boolean

    suspend fun set(key: K, value: V): Boolean

    suspend fun remove(key: K): Boolean

    suspend fun clear()

    infix fun withLayer(cache: SuspendingCache<K, V>): SuspendingCache<K, V> = TwoLayerSuspendingCache(this, cache)
}