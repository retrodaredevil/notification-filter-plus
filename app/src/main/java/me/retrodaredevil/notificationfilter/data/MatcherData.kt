package me.retrodaredevil.notificationfilter.data

import me.retrodaredevil.notificationfilter.NotificationMatcher

interface MatcherData {
    val matcherType: MatcherType

    operator fun set(key: String, value: Any)
    operator fun get(key: String): Any?
    fun isFieldValid(key: String): Boolean

    /**
     * Indicates whether or not all the data is initialized and valid
     */
    val isValid: Boolean
    /**
     * @throws IllegalStateException if [isValid] is false
     */
    fun createMatcher(): NotificationMatcher
}