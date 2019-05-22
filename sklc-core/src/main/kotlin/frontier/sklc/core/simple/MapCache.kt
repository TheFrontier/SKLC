package frontier.sklc.core.simple

import frontier.sklc.core.Cache

class MapCache<K : Any, V : Any>(private val map: Map<K, V>) : Cache<K, V> {

    override fun count(): Int = map.size

    override fun keys(): Set<K> = map.keys

    override fun values(): Iterable<V> = map.values

    override fun contains(key: K): Boolean = key in map

    override fun fetch(key: K): V? = map[key]

    override fun set(key: K, value: V): Boolean = false

    override fun remove(key: K): Boolean = false

    override fun clear() = Unit

    override fun toMap(): Map<K, V> = map
}