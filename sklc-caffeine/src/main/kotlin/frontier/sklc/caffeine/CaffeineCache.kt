package frontier.sklc.caffeine

import frontier.sklc.core.Cache
import com.github.benmanes.caffeine.cache.Cache as CCache

class CaffeineCache<K : Any, V : Any>(private val caffeine: CCache<K, V>) : Cache<K, V> {

    override fun count(): Int =
        caffeine.estimatedSize().toInt()

    override fun keys(): Set<K> =
        caffeine.asMap().keys

    override fun values(): Iterable<V> =
        caffeine.asMap().values

    override fun contains(key: K): Boolean =
        caffeine.getIfPresent(key) != null

    override fun fetch(key: K): V? =
        caffeine.getIfPresent(key)

    override fun set(key: K, value: V): Boolean {
        caffeine.put(key, value)
        return true
    }

    override fun remove(key: K): Boolean {
        caffeine.invalidate(key)
        return true
    }

    override fun clear() =
        caffeine.invalidateAll()

    override fun toMap(): Map<K, V> =
        caffeine.asMap()
}

fun <K : Any, V : Any> CCache<K, V>.toCache(): Cache<K, V> =
    CaffeineCache(this)