package frontier.sklc.core.simple

import frontier.sklc.core.Cache

class MutableMapCache<K : Any, V : Any>(map: Map<K, V>? = null) : Cache<K, V> {

    private val map = map?.toMutableMap() ?: linkedMapOf()

    override fun count(): Int = map.size

    override fun keys(): Set<K> = map.keys

    override fun values(): Iterable<V> = map.values

    override fun contains(key: K): Boolean = key in map

    override fun fetch(key: K): V? = map[key]

    override fun set(key: K, value: V): Boolean {
        map[key] = value
        return true
    }

    override fun remove(key: K): Boolean = map.remove(key) != null

    override fun clear() = map.clear()

    override fun toMap(): Map<K, V> = map
}