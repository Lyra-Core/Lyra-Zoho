package com.lyracore.zoho.utilities

class InMemoryCache<K, V>(private val maxSize: Int) {
    private val cache = object : LinkedHashMap<K, V>(16, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            return size > maxSize
        }
    }

    @Synchronized
    fun put(key: K, value: V) {
        cache[key] = value
    }

    @Synchronized
    fun get(key: K): V? = cache[key]

    @Synchronized
    fun remove(key: K): V? = cache.remove(key)

    @Synchronized
    fun clear() = cache.clear()
}