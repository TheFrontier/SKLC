package frontier.sklc.core

import frontier.sklc.core.layer.TwoLayerCache
import frontier.sklc.core.layer.TwoLayerSuspendingCache
import frontier.sklc.core.simple.SuspendedCache

interface Cache<K : Any, V : Any> {

    fun count(): Int

    fun keys(): Set<K>

    fun values(): Iterable<V>

    fun fetch(key: K): V?

    fun contains(key: K): Boolean

    fun set(key: K, value: V): Boolean

    fun remove(key: K): Boolean

    fun clear()

    fun toMap(): Map<K, V>

    infix fun withLayer(cache: Cache<K, V>): Cache<K, V> =
        TwoLayerCache(this, cache)

    infix fun withSuspendingLayer(cache: SuspendingCache<K, V>): SuspendingCache<K, V> =
        TwoLayerSuspendingCache(this.toSuspending(), cache)
}

fun <K : Any, V : Any> Cache<K, V>.toSuspending(): SuspendingCache<K, V> =
    SuspendedCache(this)